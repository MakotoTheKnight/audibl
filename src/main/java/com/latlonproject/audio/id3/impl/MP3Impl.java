package com.latlonproject.audio.id3.impl;

import com.latlonproject.audio.generic.InvalidMediaException;
import com.latlonproject.audio.id3.MP3;
import com.latlonproject.audio.id3.metadata.enumeration.ID3Version;
import com.latlonproject.audio.id3.metadata.field.MP3Field;
import com.latlonproject.audio.metadata.enumeration.FormatType;
import com.latlonproject.audio.metadata.field.Field;
import com.latlonproject.audio.metadata.field.FieldValue;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static java.nio.file.StandardOpenOption.READ;

public class MP3Impl implements MP3 {

    private Map<Field, Set<FieldValue>> fields;
    private Long fileSize;
    private FormatType formatType;
    private ID3Version id3Version;
    private Path filePath;
    private SeekableByteChannel fileByteChannel;
    private boolean isExperimental = false;
    private int tagSizeInBytes = 0;

    //TODO:  Abstract determining signature of file to factory?
    private final byte[] id3Signature = {'I', 'D', '3'};

    /**
     * Read in the file.  We're assuming that, at this level, the file exists.
     * @param theFileUri
     */

    @Override
    public void read(final URI theFileUri) {
        filePath = Paths.get(theFileUri);
        try {
            fileByteChannel = Files.newByteChannel(filePath, EnumSet.of(READ));
            readFileHeaders();
            readTags();
            fileByteChannel.close();
        } catch (IOException e) {
            throw new IllegalStateException("IOException occurred while establishing byte channel!\n" + e);
        }

    }

    /**
     * Read the tags of the file.
     */
    private void readTags() {
        ByteBuffer buffer = ByteBuffer.allocate(tagSizeInBytes);
        try {
            fileByteChannel.read(buffer);
            // Delegate reading of frame headers
            parseFrameHeaders(buffer);
        } catch (IOException e) {
            throw new IllegalStateException("Somehow failed to read buffer while reading tags.\n" + e);
        }
    }


    /**
     * For each element in the buffer that can be reasonably read as a tag, we need to do two things:
     *  - Determine what the tags are, if present, and
     *  - Place them into our tag map.
     * @param theBuffer the byte buffer to be passed through
     */
    private void parseFrameHeaders(final ByteBuffer theBuffer) {
        // first ten bytes are tag-related information.
        byte[] tag = new byte[4];
        byte[] size = new byte[4];
        byte[] flags = new byte[2];
        while(theBuffer.hasRemaining()) {
            theBuffer.get(tag);
            theBuffer.get(size);
            theBuffer.get(flags);
            Field field = new MP3Field();
            field.setFieldName(new String(tag));
        }

    }

    /**
     * Read the file headers in to the object.
     * These will determine what version of the ID3 spec is being used.
     *
     */
    private void readFileHeaders() {
        // According to the ID3 spec, the first ten bytes are the header.
        // The first three are always ID3.
        try {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            byte[] signature = new byte[3];
            byte[] version = new byte[2];
            byte[] flags = new byte[1];
            byte[] size = new byte[4];

            fileByteChannel.read(buffer);
            // After the file is read, reset the buffer's position.
            buffer.position(0);
            buffer.get(signature, 0, signature.length);
            checkID3Signature(signature);
            buffer.get(version);
            buffer.get(flags);
            buffer.get(size);
            determineFileVersion(version[0]);
            isExperimental = (flags[0] & 0x02) >> 1 == 1;
            processTagSize(size);
        } catch (IOException e) {
            System.out.println("Fail!" + e);
        }
    }

    private void checkID3Signature(final byte[] theSignature) {
        if(!Arrays.equals(theSignature, id3Signature)) {
            throw new InvalidMediaException("Not an ID3 file!");
        }
    }

    /**
     * The specification for the ID3 size tag in the header is that there are up to 28 bits worth of information encoded
     * in the space of 32.  What this means is that there is no MSB set on any of these frames.
     * Translating is relatively straightforward - 255 is represented as 1111 1111, but encoded as 0000 0001 0111 1111.
     * @param theSize The size field.
     */

    private void processTagSize(final byte[] theSize) {
        tagSizeInBytes = (theSize[0] & 0xFF) | ((theSize[1] & 0xFF) << 7)
                + ((theSize[2] & 0xFF) << 14) | ((theSize[3]) & 0xFF) << 21;
    }

    /**
     * Only concerned with the major versions (for now),
     * this method reads in the version value, compares them against the enumeration,
     * and sets the correct version.
     * @param theVersionValue the first byte value read in
     */
    private void determineFileVersion(final byte theVersionValue) {
        for(ID3Version version : ID3Version.values()) {
            if((theVersionValue ^ version.getVersionNum()) == 0) {
                id3Version = version;
                break;
            }
        }
    }

    @Override
    public ID3Version getVersion() {
        return id3Version;
    }


    @Override
    public Long getFileSize() {
        return fileSize;
    }

    @Override
    public Map<Field, Set<FieldValue>> getFieldMap() {
        return fields;
    }

    @Override
    public FormatType getFormatType() {
        return formatType;
    }

    @Override
    public Set<FieldValue> getField(final Field theField) {
        return fields.<Set<FieldValue>>get(theField);
    }

    @Override
    public URI getFilePathURI() {

        return filePath.toAbsolutePath().toUri();
    }
}
