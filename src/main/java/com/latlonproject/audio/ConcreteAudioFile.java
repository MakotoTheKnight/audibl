package com.latlonproject.audio;

import com.latlonproject.audio.generic.GenericAudioFile;
import com.latlonproject.audio.metadata.Bitrate;
import com.latlonproject.audio.metadata.Field;
import com.latlonproject.audio.metadata.FieldValue;

import java.util.Map;

public interface ConcreteAudioFile extends GenericAudioFile {

    public Map<Field, FieldValue> getFieldValues();

    public FieldValue getFieldValue(Field theRequestedField);

    public Bitrate getBitrate();


}
