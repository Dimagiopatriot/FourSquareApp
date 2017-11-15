package com.testapp.testapp;

import com.testapp.testapp.model.utils.SizeChanger;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by troll on 05.11.2017.
 */

public class SizeChangerTest {


    @Test
    public void zoomInUpperRoundTest(){
        int expected = 5860;
        int actual = SizeChanger.zoomIn(586, Constants.UI.SCALE);
        assertEquals(expected, actual);
    }

    @Test
    public void zoomInLowerRoundTest(){
        int expected = 5940;
        int actual = SizeChanger.zoomIn(594, Constants.UI.SCALE);
        assertEquals(expected, actual);
    }

    @Test
    public void zoomOutUpperRoundTest(){
        int expected = 59;
        int actual = SizeChanger.zoomOut(589, Constants.UI.SCALE);
        assertEquals(expected, actual);
    }

    @Test
    public void zoomOutLowerRoundTest(){
        int expected = 59;
        int actual = SizeChanger.zoomOut(594, Constants.UI.SCALE);
        assertEquals(expected, actual);
    }

}
