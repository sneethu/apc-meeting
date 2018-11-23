package com.ingg.apcmeeting.repository;

import com.ingg.apcmeeting.domain.Meeting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface MeetingRepository extends ReactiveMongoRepository<Meeting, Integer> {

    Flux<Meeting> findByStartAfterAndEndBefore(LocalDateTime start, LocalDateTime end);
}
