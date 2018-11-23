package com.ingg.apcmeeting.controller;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.Month;

@WebFluxTest
class MeetingRestControllerTest {

/*
    private final WebTestClient webTestClient;

    private final LocalDateTime startOfNovember2018 = LocalDateTime.of(2018, Month.NOVEMBER, 01, 00, 00);
    private final LocalDateTime endOfNovember2018 = LocalDateTime.of(2018, Month.NOVEMBER, 30, 00, 00);

    private final LocalDateTime fifthOfNovember2018_T10_00 = LocalDateTime.of(2018, Month.NOVEMBER, 05, 10, 00);
    private final LocalDateTime fifthOfNovember2018_T10_15 = LocalDateTime.of(2018, Month.NOVEMBER, 05, 10, 15);
    private final LocalDateTime twentyFifthOfNovember2018_T18_00 = LocalDateTime.of(2018, Month.NOVEMBER, 25, 18, 00);
    private final LocalDateTime twentyFifthOfNovember2018_T21_00 = LocalDateTime.of(2018, Month.NOVEMBER, 25, 21, 00);

    private final Meeting meeting1_nov = new Meeting(1, "Daily Scrum", fifthOfNovember2018_T10_00, fifthOfNovember2018_T10_15, "Daily Scrum");
    private final Meeting meeting2_nov = new Meeting(2, "Birthday Party", twentyFifthOfNovember2018_T18_00, twentyFifthOfNovember2018_T21_00, "Birthday Party");

    @MockBean
    private MeetingRepository repository;

    @Autowired
    public MeetingRestControllerTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    public void getMeetings(){

        Mockito
               .when(repository.findByStartAfterAndEndBefore(ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class)))
               .thenReturn(Flux.just(meeting1_nov, meeting2_nov));

        webTestClient.get()
                     .uri("/meeting?start=2019-01-01T23:00:00Z&end=2019-02-01T10:00:00Z")
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBodyList(Meeting.class)
                     .hasSize(2)
                     .contains(meeting1_nov, meeting2_nov);
    }
*/

    @Test
    public void createMeeting(){



    }

}
