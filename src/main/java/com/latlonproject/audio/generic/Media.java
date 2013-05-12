package com.latlonproject.audio.generic;


import com.latlonproject.audio.metadata.enumeration.FormatType;
import com.latlonproject.audio.metadata.field.Field;
import com.latlonproject.audio.metadata.field.FieldValue;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public interface Media {

    public Long getFileSize();

    public boolean isReadOnly();

    public Map<Field, Set<FieldValue>> getFieldMap();

    public FormatType getFormatType();

    public Set<FieldValue> getField(Field theField);

    public void read(URI theFileUri);

    public URI getFilePathURI();

}
