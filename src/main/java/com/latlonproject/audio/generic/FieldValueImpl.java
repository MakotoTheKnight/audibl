package com.latlonproject.audio.generic;

import com.latlonproject.audio.metadata.field.FieldValue;

public class FieldValueImpl implements FieldValue {

    private String value;


    @Override
    public void setValue(final String theValue) {
        value = theValue;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
