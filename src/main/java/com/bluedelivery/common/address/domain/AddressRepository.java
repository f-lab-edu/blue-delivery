package com.bluedelivery.common.address.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluedelivery.user.domain.User;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser(Long id, User user);
}
