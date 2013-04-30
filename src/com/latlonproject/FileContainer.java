package com.latlonproject;

import com.latlonproject.exception.InvalidFieldException;
import com.latlonproject.exception.UnmodifiableMetadataException;
import com.latlonproject.exception.UnreadableMetadataException;
import com.latlonproject.field.Field;

public interface FileContainer {

    /**
     * Read in all metadata on a particular file.
     * This implementation is file-type dependent, but should behave the same across all file types.
     * @return The metadata contained within the file.
     * @throws com.latlonproject.exception.UnreadableMetadataException if the data can't be parsed.
     */
    public Metadata read() throws UnreadableMetadataException;

    /**
     * Read in metadata only on one specific field type.
     *
     * @param inboundField the field type desired
     * @return the metadata associated with the specified field
     * @throws UnreadableMetadataException if the data can't be parsed.
     * @throws com.latlonproject.exception.InvalidFieldException if the type of field being applied doesn't match the file
     */
    public Metadata read(Field inboundField) throws UnreadableMetadataException, InvalidFieldException;

    /**
     * Write metadata back to a file.
     * @param inboundMetadata the metadata to be written
     * @throws com.latlonproject.exception.UnmodifiableMetadataException if the metadata container is read only
     * @throws UnreadableMetadataException if the metadata is invalid
     */
    public void write(Metadata inboundMetadata)
            throws UnmodifiableMetadataException, UnreadableMetadataException;

}
