package com.latlonproject.audio.id3.impl;

import com.latlonproject.audio.id3.metadata.enumeration.ID3Version;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MP3ImplMockTest {

    @Mock
    private ByteBuffer byteBufferMock;

    @Mock
    private SeekableByteChannel fileByteChannelMock;

    @Spy
    @InjectMocks
    private MP3Impl testObject;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFromSynchSafeInteger_decodes255() {
        // given
        byte[] testData = new byte[]{0x00, 0x00, 0x01, 0x7F};

        //when
        int result = testObject.fromSynchSafeInteger(testData);

        //then
        assertTrue("Synchsafe integer decoding is not working appropriately!",
                255 == result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testToSynchSafeInteger_encodes255() {
        //given
        byte[] testData = new byte[]{0, 0, 0, -1};

        //when
        int result = testObject.toSynchSafeInteger(testData);

        //then
        assertTrue("Synchsafe integer encoding is not working appropriately!",
                383 == result);
    }

    @Test
    public void testGetVersion_getsVersion3() {
        //given
        byte version = 4;

        //when
        testObject.determineFileVersion(version);

        //then
        assertTrue("Version wasn't detected properly",
                ID3Version.V2_4.getVersionNum() == testObject.getVersion().getVersionNum());
    }

    @Test
    public void testReadTags_callsParseFrameHeaders() throws Exception {
        //given
        int tagSize = 100;

        //when
        when(fileByteChannelMock.read(byteBufferMock)).thenReturn(Integer.valueOf(100));
        testObject.readTags(tagSize);

        //then
        verifyNoMoreInteractions(byteBufferMock);

    }
}
