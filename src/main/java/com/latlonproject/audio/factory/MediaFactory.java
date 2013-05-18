package com.latlonproject.audio.factory;

import com.latlonproject.audio.generic.Media;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class MediaFactory {

    public static final UUID instanceUUID = UUID.randomUUID();
    public static final MediaFactory thisInstance = new MediaFactory();
    public static final MediaFactoryHelper mediaFactoryHelper = new MediaFactoryHelper();


    public static MediaFactory getInstance() {
        return thisInstance;
    }

    private MediaFactory() { }

    public UUID getInstanceUUID() {
        return instanceUUID;
    }

    public static Media findMedia(String theFileURI) {
        Path path = Paths.get("file:/" + theFileURI);
        return mediaFactoryHelper.parseMediaType(path);
    }




}

