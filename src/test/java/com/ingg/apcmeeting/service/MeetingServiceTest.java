package com.ingg.apcmeeting.service;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import com.ingg.apcmeeting.service.MeetingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

public class MeetingServiceTest {

    @Mock
    MeetingRepository meetingRepository;

    MeetingServiceImpl meetingService;

    @Before
    public void setUp(){
        meetingService= new MeetingServiceImpl(meetingRepository);
    }

    @Test
    public void testSave(){
        final Meeting meeting = new Meeting();
        meeting.setId(12);

        final Mono<Meeting> savedMeeting = meetingService.save(meeting);
/*
        StepVerifier
                .create(savedMeeting)
                .expectNextMatches((Meeting saved) -> StringUtils.hasText(saved.getId()))
                .verifyComplete();
*/

    }

}