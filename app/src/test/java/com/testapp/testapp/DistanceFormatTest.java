package com.testapp.testapp;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by troll on 05.11.2017.
 */

public class DistanceFormatTest {

    @Test
    public void kotlinFromMetersToKilometersUpperRoundTest(){
        String expected = "6.4";
        String actual = com.testapp.testapp.kotlin.model.utils.DistanceFormatter
                .Companion.fromMetersToKilometersFormat(6352);
        assertEquals(expected, actual);
    }

    @Test
    public void kotlinFromMetersToKilometersLowerRoundTest(){
        String expected = "6.3";
        String actual = com.testapp.testapp.kotlin.model.utils.DistanceFormatter
                .Companion.fromMetersToKilometersFormat(6348);
        assertEquals(expected, actual);
    }
}
