package com.ingg.apcmeeting.repository;

import com.ingg.apcmeeting.domain.Meeting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MeetingRepository extends ReactiveMongoRepository<Meeting, Integer> {
}
