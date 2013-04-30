package com.latlonproject.field;

import com.latlonproject.field.enumeration.FieldType;
import com.latlonproject.mp3.field.MP3Field;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MP3FieldTest {

    @Test
    public void retrieveField_returnsMP3Field() {

        MP3Field field = new MP3Field();

        assertEquals(FieldType.MP3, field.getType());
    }

}
