package com.latlonproject.audio.factory;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class MediaFactoryTest {

    @Test
    public void testFactory_instanceIsUnique() {
        //given

        //when
        MediaFactory factory = MediaFactory.getInstance();

        //then
        assertNotNull(factory);
        assertSame("Instances aren't unique", factory, MediaFactory.getInstance());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            assertEquals("UUID isn't the same, not unique", factory.getInstanceUUID(), MediaFactory.getInstance().getInstanceUUID());
        }
    }




}
