package com.latlonproject.audio.id3.metadata.enumeration;

public enum ID3Version {
    @Deprecated V2_2(2), // Deprecated specification
    V2_3(3),
    V2_4(4);

    private int versionNum;

    ID3Version(final int theVersionNum) {
        versionNum = theVersionNum;
    }

    public int getVersionNum() {
        return versionNum;
    }
}
