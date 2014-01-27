package com.latlonproject.audio.id3.impl;

import com.latlonproject.audio.id3.MP3;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MP3ImplIntegrationTest {

    private MP3 testObject;

    @Before
    public void init() {
        testObject = new MP3Impl();
    }

    @Test
    public void read_readsProperly() {
        //given
        //when
        testObject.read("/home/makoto/audibl/src/test/resources/com/latlonproject/audio/id3/impl/03 - minimal - circle.mp3");

        //then
        assertThat(false, is(true));
    }

}
