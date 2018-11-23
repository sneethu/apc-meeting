package com.ingg.apcmeeting.controller;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.service.MeetingService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class MeetingRestController {

    final Logger LOGGER = LoggerFactory.getLogger(MeetingRestController.class);

    MeetingService meetingService;

    @Autowired
    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/meeting")
    Publisher<Meeting> getMeetings(@RequestParam("start")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                  @RequestParam("end")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                  ServerHttpResponse response){
        LOGGER.info("startDate = {} and endDate = {}", start, end );
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return meetingService.findMeetings(start, end);
    }

}
