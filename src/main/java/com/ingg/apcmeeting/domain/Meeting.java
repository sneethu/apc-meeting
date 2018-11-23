package com.ingg.apcmeeting.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document//(collection = "meetings")
public class Meeting {

    @Id
    private Integer id;

    private String title;

    private LocalDateTime start;

    private LocalDateTime end;

    private String description;
}
