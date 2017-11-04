package com.testapp.testapp.model.utils;

/**
 * Created by troll on 04.11.2017.
 */

public class SizeChanger {

    public static int zoomOut(int oldSize, int scale){
        return Math.round(oldSize / scale);
    }

    public static int zoomIn(int oldSize, int scale){
        return Math.round(oldSize * scale);
    }
}
