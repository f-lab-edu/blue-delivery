package com.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeliveryApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(DeliveryApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
