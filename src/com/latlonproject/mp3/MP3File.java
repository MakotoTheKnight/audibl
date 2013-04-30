package com.latlonproject.mp3;

import com.latlonproject.FileContainer;
import com.latlonproject.Metadata;
import com.latlonproject.exception.InvalidFieldException;
import com.latlonproject.exception.UnmodifiableMetadataException;
import com.latlonproject.exception.UnreadableMetadataException;
import com.latlonproject.field.Field;
import com.latlonproject.field.enumeration.FieldType;

import java.net.URI;
import java.net.URISyntaxException;

public class MP3File implements FileContainer {

    URI fileLocation = null;

    public MP3File(String theFileLocation) throws URISyntaxException {
        fileLocation = new URI(theFileLocation);
    }

    public MP3File() {}


    @Override
    public Metadata read() throws UnreadableMetadataException {
        return null;
    }

    @Override
    public Metadata read(final Field inboundField) throws UnreadableMetadataException, InvalidFieldException {
        if(!(FieldType.MP3.equals(inboundField.getType()))) {
            throw new InvalidFieldException("Illegal field for MP3 file: " + inboundField.getType().toString());
        }

        return null;
    }

    @Override
    public void write(final Metadata inboundMetadata) throws UnmodifiableMetadataException, UnreadableMetadataException {

    }
}
