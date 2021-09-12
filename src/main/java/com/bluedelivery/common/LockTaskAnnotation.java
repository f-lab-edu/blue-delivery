package com.bluedelivery.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LockTaskAnnotation {
    
    private final TaskLockRepository taskLockRepository;
    
    public LockTaskAnnotation(TaskLockRepository taskLockRepository) {
        this.taskLockRepository = taskLockRepository;
    }
    
    @Around(value = "@annotation(lockTask)")
    public Object lockTaskAction(ProceedingJoinPoint joinPoint, LockTask lockTask) {
        Object proceed = null;
        try {
            String task = lockTask.task();
            long duration = lockTask.expireAfter();
            if (isAlreadyInProcess(task)) {
                return null;
            }
            TaskLock lock = new TaskLock(task, InetAddress.getLocalHost().getHostName(), Duration.ofMillis(duration));
            taskLockRepository.save(lock);
            proceed = joinPoint.proceed();
        } catch (UnknownHostException e) {
            throw new IllegalStateException("hostname 을 찾을 수 없음");
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
        return proceed;
    }
    
    
    private boolean isAlreadyInProcess(String taskName) {
        Optional<TaskLock> found = taskLockRepository.findById(taskName);
        if (found.isPresent()) {
            TaskLock lock = found.get();
            if (lock.isValid()) {
                return true;
            }
            taskLockRepository.delete(lock);
        }
        return false;
    }
}
