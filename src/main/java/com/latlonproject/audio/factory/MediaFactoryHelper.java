package com.latlonproject.audio.factory;

import com.latlonproject.audio.generic.Media;
import com.latlonproject.audio.generic.exception.InvalidMediaException;
import com.latlonproject.audio.id3.impl.MP3Impl;
import com.latlonproject.audio.metadata.annotation.Impl;
import com.latlonproject.audio.metadata.enumeration.FormatType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

@Impl
public class MediaFactoryHelper {

    public MediaFactoryHelper() {

    }

    public Media parseMediaType(Path theFilePath) {
        Media media = null;

        try {
            SeekableByteChannel byteChannel = Files.newByteChannel(theFilePath, EnumSet.of(StandardOpenOption.READ));
            ByteBuffer buffer = ByteBuffer.allocate(4);
            byteChannel.read(buffer);
            media = identifyMedia(buffer);
        } catch (IOException e) {
            throw new IllegalStateException("Path present, but unable to read file");
        }

        return media;
    }

    private Media identifyMedia(final ByteBuffer theBuffer) {
        Media media = null;
        for(FormatType type : FormatType.values()) {
            byte ref = theBuffer.get();
            byte[] identifier = type.getIdentifier();
            int index = 0;
            // Read the first byte.  If it matches one of them, then we can continue reading. Otherwise, we skip it.
            while(ref == identifier[index]) {
                // cool, let's continue with this identifier.
                ++index;
                ref = theBuffer.get();
                if(index == identifier.length) {
                    media = getMediaFromType(type);
                }
            }
            theBuffer.position(0);
        }

        return media;
    }

    private Media getMediaFromType(final FormatType theType) {
        switch(theType) {
            case MP3:
                return new MP3Impl();
            default:
                throw new InvalidMediaException("File type not supported.");
        }
    }


}
