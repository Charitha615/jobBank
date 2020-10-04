package com.example.jobbank;

import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    view_applications v;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void button_is_correct1(){
        boolean a = v.checkStatus("Initial");
        assertEquals(true, a);

    }

    @Test
    public void button_is_correct2(){
        boolean b = v.checkStatus("Rejected");
        assertEquals(false, b);

    }

}