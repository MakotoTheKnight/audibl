package com.latlonproject.audio.id3;

import com.latlonproject.audio.generic.Media;
import com.latlonproject.audio.id3.metadata.enumeration.ID3Version;

public interface MP3 extends Media {

    public ID3Version getVersion();


}
