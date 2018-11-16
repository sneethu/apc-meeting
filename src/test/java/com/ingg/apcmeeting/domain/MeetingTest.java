package com.ingg.apcmeeting.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeetingTest {

    private Meeting meeting;

    @Before
    public void setUp(){
        meeting = new Meeting();
    }

    @Test
    public void getId(){
       meeting.setId(123);
       assertEquals(Integer.valueOf(123), meeting.getId());
    }

}