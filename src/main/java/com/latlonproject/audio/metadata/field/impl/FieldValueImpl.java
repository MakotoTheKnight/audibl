package com.latlonproject.audio.metadata.field.impl;

import com.latlonproject.audio.metadata.annotation.Impl;
import com.latlonproject.audio.metadata.field.FieldValue;

@Impl
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
}
