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
    examine_applicants e;

    publish_a_vacancy p;

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

    @Test
    public void status_is_correct_interview(){
        String status = examine_applicants.setStatus(39);
        assertEquals("Interview", status);
    }

    @Test
    public void status_is_correct_pending(){
        String status = examine_applicants.setStatus(29);
        assertEquals("Pending", status);
    }

    @Test
    public void status_is_correct_rejected(){
        String status = examine_applicants.setStatus(9);
        assertEquals("Rejected", status);
    }


    //Ayeshka's Testing Part
    @Test
    public void YearsOfExp_NoOfDigits(){
        String Check_Years = publish_a_vacancy.validExp(115);
        assertEquals("Invalid",Check_Years);
    }

    @Test
    public void CheckAge_WhenPublishing(){
        String Age1 = publish_a_vacancy.CheckAge(80);
        assertEquals("Error",Age1);
    }


    //Charitha's Testing
    @Test
    public void AgeCheck(){
        String Check_age = user_add_cv.CheckAge(100, 100);
        assertEquals("Invalid", Check_age);
    }

}