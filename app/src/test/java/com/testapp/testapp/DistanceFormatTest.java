package com.testapp.testapp;

import com.testapp.testapp.model.utils.DistanceFormatter;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by troll on 05.11.2017.
 */

public class DistanceFormatTest {

    @Test
    public void fromMetersToKilometersUpperRoundTest(){
        String expected = "6.4";
        String actual = DistanceFormatter.fromMetersToKilometersFormat(6352);
        assertEquals(expected, actual);
    }

    @Test
    public void fromMetersToKilometersLowerRoundTest(){
        String expected = "6.3";
        String actual = DistanceFormatter.fromMetersToKilometersFormat(6348);
        assertEquals(expected, actual);
    }

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
