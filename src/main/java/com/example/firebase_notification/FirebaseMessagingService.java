package com.example.firebase_notification;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseMessagingService {
    private final FirebaseMessaging firebaseMessaging;
    
    @Async
    public void sendNotice(String token){
        Notification notification=Notification.builder()
                                    .setTitle("Test notification")
                                    .setBody("Test notification's body")
                                    .setImage("https://static.vecteezy.com/system/resources/thumbnails/025/067/762/small/4k-beautiful-colorful-abstract-wallpaper-photo.jpg")
                                    .build();
        send(token, notification).thenAccept(t -> {
            System.out.println(t);
        });
    }
    public CompletableFuture<Integer> send(String token, Notification notification){
        try {
            Message message=Message.builder()
                                    .setToken(token)
                                    .setNotification(notification)
                                    .build();
            firebaseMessaging.sendAsync(message);
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            System.out.println();
            StringBuilder sb=new StringBuilder();
            sb.append(token)
            .append("\n")
            .append(e.getClass().getName())
            .append("\n")
            .append(e.getMessage());
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                sb.append(stackTrace);
                sb.append("\n");
            }
            log.error(sb.toString());
            return CompletableFuture.completedFuture(0);
        }
    }
}
