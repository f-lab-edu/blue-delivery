package com.bluedelivery.notification;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification {
    
    @Id
    @GeneratedValue
    private Long notificationId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime expire;
    
    
    public Notification() {
    
    }
    
    public Notification(String content) {
        this.content = content;
        createdAt = LocalDateTime.now();
        expire = createdAt.plusMinutes(1);
    }
}
