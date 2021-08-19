package com.bluedelivery.order.infra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface OrderNotificationRepository extends CrudRepository<OrderNotification, Long> {
}
