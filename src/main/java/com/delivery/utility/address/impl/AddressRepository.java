package com.delivery.utility.address.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.user.domain.User;
import com.delivery.utility.address.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser(Long id, User user);
}
