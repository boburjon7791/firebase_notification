package com.example.firebase_notification;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging()throws Exception {
        GoogleCredentials googleCredentials=GoogleCredentials.fromStream(
            new ClassPathResource("firebase-service-account.json").getInputStream()
        );
        FirebaseOptions firebaseOptions=FirebaseOptions.builder()
                            .setCredentials(googleCredentials).build();
        FirebaseApp app=FirebaseApp.initializeApp(firebaseOptions,UUID.randomUUID().toString());
        return FirebaseMessaging.getInstance(app);
    }
}
