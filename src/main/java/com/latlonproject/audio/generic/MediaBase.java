package com.latlonproject.audio.generic;

import com.latlonproject.audio.metadata.enumeration.FormatType;
import com.latlonproject.audio.metadata.field.Field;
import com.latlonproject.audio.metadata.field.FieldValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URI;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class MediaBase implements Media {

    protected Long fileSize;
    protected boolean readOnly;
    protected Map<Field, FieldValue> fieldMap = new LinkedHashMap<>();
    protected FormatType formatType;
    protected Path filePath;


    @Override
    public Long getFileSize() {
        return fileSize;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public Map<Field, FieldValue> getFieldMap() {
        return fieldMap;
    }

    @Override
    public FormatType getFormatType() {
        return formatType;
    }

    @Override
    public FieldValue getField(Field field) {
        return fieldMap.get(field);
    }

    @Override
    public void read(String path) {
        throw new NotImplementedException();
    }

    @Override
    public URI getFilePathURI() {
        return filePath.toAbsolutePath().toUri();
    }
}
