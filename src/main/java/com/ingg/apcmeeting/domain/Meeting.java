package com.ingg.apcmeeting.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Meeting {

    @Id
    private Integer id;

    private String title;
}
