package com.testapp.testapp;

import com.testapp.testapp.presenter.utils.DateFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by troll on 05.11.2017.
 */

public class DateFormatterTest {

    @Test
    public void testDateFormatter(){
        String expected = "20171105";
        String actual = DateFormatter.getCurrentDateInApiVersionFormat();
        assertEquals(expected, actual);
    }
}
