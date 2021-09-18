package com.bluedelivery.common.shedlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLockRepository extends JpaRepository<TaskLock, String> {
}
