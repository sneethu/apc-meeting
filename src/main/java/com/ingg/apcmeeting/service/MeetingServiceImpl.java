package com.ingg.apcmeeting.service;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Mono<Meeting> save(Meeting meeting){
        return meetingRepository.save(meeting);
    }

    public Flux<Meeting> findAll(){
        return meetingRepository.findAll();
    }
}
