package com.latlonproject.audio.generic;

import com.latlonproject.audio.metadata.Field;
import com.latlonproject.audio.metadata.FieldValue;
import com.latlonproject.audio.metadata.Format;
import org.joda.time.Duration;


public interface GenericAudioFile {

    //TODO:  ReadableDuration?
    public Duration getDuration();

    public Integer getFileSize();

    public Format getFileFormat();

}
