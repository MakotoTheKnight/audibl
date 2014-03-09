package com.latlonproject.audio.id3;

import com.latlonproject.audio.generic.Media;
import com.latlonproject.audio.id3.metadata.enumeration.ID3Version;

/**
 * Representation of an abstract MP3 media type.
 * It comes with the version of MP3 metadata we expect.
 */

public interface MP3 extends Media {

    /**
     * Retrieves the current ID3 version this media type contains
     * @return the version, as an enumeration
     */
    public ID3Version getVersion();


}
