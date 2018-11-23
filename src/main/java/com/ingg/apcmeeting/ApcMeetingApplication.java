package com.ingg.apcmeeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan("com.ingg.apcmeeting")
public class ApcMeetingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApcMeetingApplication.class, args);
	}
}
