package com.testapp.testapp.model.utils;

/**
 * Created by troll on 04.11.2017.
 */

public class SizeChanger {

    public static int zoomOut(int oldSize){
        return Math.round(oldSize / Constants.SCALE);
    }
}