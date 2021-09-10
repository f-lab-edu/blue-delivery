package com.bluedelivery.common.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
@Entity
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outboxId;
    private Long aggregateId;
    private String aggregateType;
    private String payload;
    private String eventType;
    
    public Outbox() {
    
    }
    
    public Outbox(Long aggregateId, String aggregateType, Object obj, String eventType) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        try {
            this.payload = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("outbox serialization 실패 : " + e);
        }
        this.eventType = eventType;
    }
}
