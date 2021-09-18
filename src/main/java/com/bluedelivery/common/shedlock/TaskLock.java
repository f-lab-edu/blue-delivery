package com.bluedelivery.common.shedlock;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskLock {
    
    @Id
    private String task;
    private LocalDateTime lockedTime;
    private LocalDateTime lockUntil;
    private String lockedBy;
    
    protected TaskLock() {
    }
    
    public TaskLock(String task, String lockedBy, Duration validTime) {
        this.task = task;
        this.lockedTime = LocalDateTime.now();
        this.lockedBy = lockedBy;
        this.lockUntil = lockedTime.plus(validTime);
    }
    
    public boolean isValid() {
        if (LocalDateTime.now().isAfter(lockUntil)) {
            return false;
        }
        return true;
    }
}
