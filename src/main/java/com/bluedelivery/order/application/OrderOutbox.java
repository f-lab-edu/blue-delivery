package com.bluedelivery.order.application;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
@Entity
public class OrderOutbox {
    @Id @Type(type = "uuid-char")
    private UUID outboxId;
    private String payload;
    
    public OrderOutbox() {
    
    }
    
    public OrderOutbox(Object obj) {
        this.outboxId = UUID.randomUUID();
        try {
            this.payload = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("outbox serialization 실패 : " + e);
        }
    }
}
