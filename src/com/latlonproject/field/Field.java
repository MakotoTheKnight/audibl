package com.latlonproject.field;

import com.latlonproject.field.enumeration.FieldType;

public class Field {

    private FieldType type = FieldType.UNSUPPORTED;


    public FieldType getType() {
        return type;
    }

    protected void setFieldType(FieldType inFieldType) {
        type = inFieldType;
    }

}
