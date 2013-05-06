package com.latlonproject.audio.id3.metadata.field;

import com.latlonproject.audio.metadata.field.Field;

public class MP3Field implements Field {
    public MP3FieldName fieldName;

    private static enum MP3FieldName  {
        APIC("picture"),

        COMM("comments"),

        TALB("album"),
        TDAT("date"),
        TCOP("copyright"),
        TIT2("title"),
        TIT3("subtitle"),
        TLAN("language"),
        TLEN("length"),
        TPE1("artist"),
        TRCK("track"),
        TPUB("publisher"),
        TSIZ("size"),
        TYER("year"),

        UFID("unique_identifier"),
        USER("terms"),

        WCOP("copyright"),
        WPAY("payment"),
        WPUB("publisher_webpage"),
        WXXX("url");

        String commonFieldName;

        MP3FieldName(String theCommonFieldName) {
            commonFieldName = theCommonFieldName;
        }

        private String getCommonFieldName() {
            return commonFieldName;
        }
    }

    @Override
    public void setFieldName(final String theFieldName) {
        fieldName = MP3FieldName.valueOf(theFieldName);
    }

    @Override
    public String getFieldName() {
        return fieldName.name();
    }
}
