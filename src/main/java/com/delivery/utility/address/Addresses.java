package com.delivery.utility.address;

import static com.delivery.response.ErrorCode.*;
import static java.util.Objects.isNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.delivery.exception.ApiException;
import com.delivery.user.domain.User;

@Embeddable
public class Addresses {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MAIN_ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address mainAddress; // 대표 주소
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();
    
    public boolean addAddress(Address address) {
        return addresses.add(address);
    }
    
    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }
    
    public boolean designateAsMainAddress(Address address) {
        if (addresses.contains(address)) {
            this.mainAddress = address;
            return true;
        }
        return false;
    }
    
    public Address getMainAddress() {
        if (isNull(mainAddress)) {
            throw new ApiException(ADDRESS_DOES_NOT_EXIST);
        }
        return mainAddress;
    }
    
    public List<Address> getAddresses() {
        return List.copyOf(addresses);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Addresses addresses1 = (Addresses) obj;
        return Objects.equals(mainAddress, addresses1.mainAddress) && Objects.equals(addresses, addresses1.addresses);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(mainAddress, addresses);
    }
}
