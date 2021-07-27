package com.bluedelivery.domain.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluedelivery.domain.user.User;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser(Long id, User user);
}
