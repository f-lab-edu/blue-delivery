package com.bluedelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import co.elastic.apm.attach.ElasticApmAttacher;

@SpringBootApplication
@EnableCaching
public class DeliveryApplication {
    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(DeliveryApplication.class, args);
    }
}
