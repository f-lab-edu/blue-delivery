package com.delivery.utility.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.user.domain.User;
import com.delivery.utility.address.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser(Long id, User user);
}
