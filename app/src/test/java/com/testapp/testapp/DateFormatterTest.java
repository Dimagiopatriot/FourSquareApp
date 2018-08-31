package com.testapp.testapp;

import com.testapp.testapp.kotlin.presenter.utils.DateFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by troll on 05.11.2017.
 */

public class DateFormatterTest {

    @Test
    public void testDateFormatter(){
        String expected = "20180830";
        String actual = DateFormatter.Companion.getCurrentDateInApiVersionFormat();
        assertEquals(expected, actual);
    }
}
