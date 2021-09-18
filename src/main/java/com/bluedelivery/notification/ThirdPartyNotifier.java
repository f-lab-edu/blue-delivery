package com.bluedelivery.notification;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.domain.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ThirdPartyNotifier { // 가짜 서비스. 랜덤숫자 5가 나오면 알림 전송에 실패함
    public boolean request(NotificationForm form) {
        try {
            Thread.sleep(300L);
        } catch (InterruptedException ignored) {
        
        }
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int random = current.nextInt(10);
        if (random == 5) {
            log.error("3rd party form : fail = " + form);
            return false;
        }
        log.info("3rd party form : succeed = " + form);
        return true;
    }
    
    @Data
    @AllArgsConstructor
    public static class NotificationForm {
        private String token;
        private OrderDetails orderDetails;
    }
}
