package com.ingg.apcmeeting.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document//(collection = "meetings")
public class Meeting {

    @Id ObjectId databaseId;
    private Integer id;

    private String title;

    private LocalDateTime start;

    private LocalDateTime end;

    private String description;
}
