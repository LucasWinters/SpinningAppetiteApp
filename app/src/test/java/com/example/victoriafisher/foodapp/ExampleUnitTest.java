package com.example.victoriafisher.foodapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ZipcodeTest() throws Exception {

        UserInput tori = new UserInput();
        tori.setZip(21702);
        assertEquals(21702,tori.getZip());

    }
}