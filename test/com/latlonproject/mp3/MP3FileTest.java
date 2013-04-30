package com.latlonproject.mp3;

import com.latlonproject.Metadata;
import com.latlonproject.exception.InvalidFieldException;
import com.latlonproject.exception.UnreadableMetadataException;
import com.latlonproject.field.Field;
import com.latlonproject.mp3.field.MP3Field;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class MP3FileTest {

    @Test
    public void readData_readsDataProperly() throws URISyntaxException, UnreadableMetadataException {
        MP3File file = new MP3File("file://com/latlonproject/binary/03\\ -\\ minimal\\ -\\ circle.mp3");
        Metadata result = file.read();
        assertNotNull(result);

    }

    @Test
    public void read_dataContainsImage_readsImageProperly() {
        assertEquals(Boolean.TRUE, Boolean.FALSE);
    }

    @Test(expected = InvalidFieldException.class)
    public void read_invalidField_throwsException() throws Exception {
        MP3File file = new MP3File();
        Field field = new Field();
        file.read(field);
    }

    @Test(expected = UnreadableMetadataException.class)
    public void read_unreadableMetadata_throwsException() {
        throw new RuntimeException();

    }


}
