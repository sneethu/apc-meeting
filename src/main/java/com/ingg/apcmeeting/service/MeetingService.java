package com.ingg.apcmeeting.service;

import com.ingg.apcmeeting.domain.Meeting;
import com.ingg.apcmeeting.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService{

    private final MeetingRepository meetingRepository;
    private final NextSequenceService nextSequenceService;

    public MeetingService(@Autowired MeetingRepository meetingRepository,
                          @Autowired NextSequenceService nextSequenceService){
        this.meetingRepository = meetingRepository;
        this.nextSequenceService = nextSequenceService;
    }

    public Mono<Meeting> create(Meeting meeting){
        meeting.setId(nextSequenceService.getNextSequence("customSequences"));
        return meetingRepository.save(meeting);
    }

    public Flux<Meeting> findMeetings(LocalDateTime start, LocalDateTime end){
        return meetingRepository.findByStartAfterAndEndBefore(start, end);
    }

    public Flux<Meeting> findAll(){
        return meetingRepository.findAll();
    }

    public Mono<Meeting> delete(Meeting meetingToDelete){
        final Mono<Meeting> meetingFound = meetingRepository.findById(meetingToDelete.getId());
        return meetingFound.flatMap(meeting -> meetingRepository.deleteById(meeting.getId()).thenReturn(meeting));
    }

    public Flux<Meeting> saveAll(List<Meeting> meetings) {
        return meetingRepository.saveAll(meetings);
    }

/*    public Mono<Meeting> update(Meeting meetingToUpdate) {

        return meetingRepository
                .findById(meetingToUpdate.getId())
                .map(new Function<Meeting, Meeting>() {
                    @Override
                    public Meeting apply(Meeting meetingFound) {
                        return new Meeting(meetingFound.getId(), meetingToUpdate.getTitle());
                    }
                }).flatMap(meeting -> meetingRepository.save(meeting));
    }*/
}
