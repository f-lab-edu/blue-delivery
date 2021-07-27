package com.bluedelivery.common.address.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluedelivery.common.address.domain.BuildingInfo;

public interface BuildingInfoRepository extends JpaRepository<BuildingInfo, String> {
}
