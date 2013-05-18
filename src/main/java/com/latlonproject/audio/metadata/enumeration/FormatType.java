package com.latlonproject.audio.metadata.enumeration;

public enum FormatType {
    MP3(new byte[] {'I', 'D', '3'}),
    OPUS(new byte[]{}),
    VORBIS(new byte[] {'O', 'g', 'g', 'S'}),
    AIFF(new byte[]{}),
    WAV(new byte[]{}),
    FLAC(new byte[]{});

    private final byte[] identifier;

    FormatType(final byte[] theIdentifier) {
        identifier = theIdentifier;
    }

    public byte[] getIdentifier() {
        return identifier;
    }
}
