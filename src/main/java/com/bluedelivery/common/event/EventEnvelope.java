package com.bluedelivery.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventEnvelope {
    private Long aggregateId;
    private String aggregateType;
    private Object event;
    private String eventType;
}
