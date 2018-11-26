package com.ingg.apcmeeting.service;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@DataMongoTest
@Import(MeetingService.class)
public class MeetingServiceIT {

    public static final String DAILY_SCRUM = "Daily Scrum";
    public static final String BIRTHDAY_PARTY = "Birthday Party";
    public static final String LEAVING_DRINKS = "Leaving Drinks";

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    private MeetingService meetingService;

    private final LocalDateTime startOfNovember2018 = LocalDateTime.of(2018, Month.NOVEMBER, 01, 00, 00);
    private final LocalDateTime endOfNovember2018 = LocalDateTime.of(2018, Month.NOVEMBER, 30, 00, 00);
    private final LocalDateTime startOfDecember2018 = LocalDateTime.of(2018, Month.DECEMBER, 01, 00, 00);
    private final LocalDateTime endOfDecember2018 = LocalDateTime.of(2018, Month.DECEMBER, 31, 00, 00);

    private final LocalDateTime fifthOfNovember2018_T10_00 = LocalDateTime.of(2018, Month.NOVEMBER, 05, 10, 00);
    private final LocalDateTime fifthOfNovember2018_T10_15 = LocalDateTime.of(2018, Month.NOVEMBER, 05, 10, 15);
    private final LocalDateTime twentyFifthOfNovember2018_T18_00 = LocalDateTime.of(2018, Month.NOVEMBER, 25, 18, 00);
    private final LocalDateTime twentyFifthOfNovember2018_T21_00 = LocalDateTime.of(2018, Month.NOVEMBER, 25, 21, 00);
    private final LocalDateTime fifthOfDecember2018_T10_00 = LocalDateTime.of(2018, Month.DECEMBER, 05, 10, 00);
    private final LocalDateTime fifthOfDecember2018_T10_15 = LocalDateTime.of(2018, Month.DECEMBER, 05, 10, 15);

    //private final Meeting meeting1_nov = new Meeting(null, "Daily1 Scrum", fifthOfNovember2018_T10_00, fifthOfNovember2018_T10_15, "Daily Scrum");
    //private final Meeting meeting2_nov = new Meeting(null, "Birthday Party", twentyFifthOfNovember2018_T18_00, twentyFifthOfNovember2018_T21_00, "Birthday Party");
    //private final Meeting meeting1_dec = new Meeting(null, "Leaving Drinks", fifthOfDecember2018_T10_00, fifthOfDecember2018_T10_15, "Leaving Drinks");

   private final Meeting meeting1_nov = Meeting.builder()
        .title(DAILY_SCRUM)
        .start(fifthOfNovember2018_T10_00)
        .end(fifthOfNovember2018_T10_15)
        .description(DAILY_SCRUM).build();

    private final Meeting meeting2_nov = Meeting.builder()
            .title(BIRTHDAY_PARTY)
            .start(twentyFifthOfNovember2018_T18_00)
            .end(twentyFifthOfNovember2018_T21_00)
            .description(BIRTHDAY_PARTY).build();

    private final Meeting meeting1_dec = Meeting.builder()
            .title(LEAVING_DRINKS)
            .start(fifthOfDecember2018_T10_00)
            .end(fifthOfDecember2018_T10_15)
            .description(LEAVING_DRINKS).build();


    @BeforeEach
    public void setUp() {
        meetingService = new MeetingService(meetingRepository, nextSequenceService);
        meetingRepository.deleteAll().block();
    }

    @Test
    public void testCreate(){
        // when
        final Mono<Meeting> savedMeeting = meetingService.create(meeting1_nov);

        // then
        StepVerifier
                .create(savedMeeting)
                .expectNextMatches(meeting -> meeting.equals(meeting1_nov))
                .verifyComplete();
    }

    @Test
    public void findAllMeetingsBetweenStartAndEndDates(){
        // given
        saveFewMeetings();

        // when
        Flux<Meeting> composite = meetingService.findMeetings(startOfNovember2018, endOfNovember2018);

        // then
        List<Meeting> meetingList_nov = Arrays.asList(meeting1_nov, meeting2_nov);
        Predicate<Meeting> match = meeting -> meetingList_nov.contains(meeting);

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }

    private void saveFewMeetings() {
        List<Meeting> meetingList = Arrays.asList(meeting1_nov, meeting2_nov, meeting1_dec);

        Flux<Meeting> savedMeetings = meetingService.saveAll(meetingList);
        savedMeetings.blockLast();
    }

    @Ignore
    @Test
    public void testDelete(){
        // given
        Meeting meetingCreated = meetingService.create(meeting1_nov).block();

        // when
        final Mono<Meeting> deleted = meetingService.delete(meetingCreated);

        // then
        StepVerifier
                .create(deleted)
                .expectNext(meetingCreated)
                .verifyComplete();
    }

/*    @Test
    public void testUpdate(){

        Mono<Meeting> updated = meetingService.create(new Meeting(1, "Game play"))
        .flatMap(meeting -> {
            meeting.setTitle("Game analysis");
            return meetingService.update(meeting);
        });

        StepVerifier
                .create(updated)
                .expectNextMatches(meeting -> meeting.getTitle().equals("Game analysis"))
                .verifyComplete();
    }*/


    @Test
    public void testSaveAll() {

        // given
        List<Meeting> meetingList = Arrays.asList(meeting1_nov, meeting2_nov, meeting1_dec);

        // when
        Flux<Meeting> savedMeetings = meetingService.saveAll(meetingList);
        savedMeetings.blockLast();

        // then
        Predicate<Meeting> match = meeting -> meetingList.contains(meeting);
        Flux<Meeting> composite = meetingService.findAll();

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }
}

