package com.example.firebase_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class FirebaseNotificationApplication {
	private final FirebaseMessagingService firebaseMessagingService;
	public static void main(String[] args) {
		SpringApplication.run(FirebaseNotificationApplication.class, args);
	}
	@Scheduled(cron = "0,10,20,30,40,50 * * * * *")
	public void sendNotice(){
		firebaseMessagingService.sendNotice("specific firebase token of mobile device");
		System.out.println("send");
    }
}
