package com.ingg.apcmeeting.service;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

//@DataMongoTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingServiceIT {

    @Autowired
    MeetingRepository meetingRepository;

    MeetingServiceImpl meetingService;

    @Before
    public void setUp() {
        meetingService = new MeetingServiceImpl(meetingRepository);
    }

    @Test
    public void testSave() {
/*        final Meeting meeting = new Meeting();
        meeting.setId(12);

        final Mono<Meeting> savedMeeting = meetingService.save(meeting);

        final Flux<Meeting> all = meetingService.findAll();
*/
        Flux<Meeting> saved = meetingRepository.saveAll(Flux.just(new Meeting(1, "Meeting 1"), new Meeting(2, "Meeting 2"), new Meeting(3, "Meeting 3")));

        Flux<Meeting> composite = meetingService.findAll().thenMany(saved);

        Predicate<Meeting> match = meeting -> saved.any(saveItem -> saveItem.equals(meeting)).block();

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }
}
