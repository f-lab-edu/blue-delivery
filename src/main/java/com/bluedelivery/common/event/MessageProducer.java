package com.bluedelivery.common.event;

import java.util.List;

public interface MessageProducer {
    List<Outbox> produce(String topic, List<Outbox> outboxes);
}
