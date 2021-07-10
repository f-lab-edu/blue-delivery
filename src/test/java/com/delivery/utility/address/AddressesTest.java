package com.delivery.utility.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.domain.User;
import com.delivery.utility.address.domain.Address;
import com.delivery.utility.address.domain.Addresses;
import com.delivery.utility.address.domain.BuildingInfo;

class AddressesTest {
    
    Addresses addresses = new Addresses();
    
    @Test
    @DisplayName("주소를 추가하면 주소의 개수가 1개 증가한다")
    void addAddress() {
        //given
        Address address = new Address();
        assertThat(addresses.getAddresses().size()).isEqualTo(0);
        
        //when
        addresses.addAddress(new User(), address);
        
        //then
        assertThat(addresses.getAddresses().size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("서로 다른 주소를 2개 추가하면 주소의 개수가 2가 된다.")
    void addAddress2() {
        //given
        BuildingInfo building1 = new BuildingInfo();
        BuildingInfo sameBuilding = new BuildingInfo();
        Address addr1 = new Address(building1, "101호");
        Address addr2 = new Address(sameBuilding, "102호");
        
        //when
        addresses.addAddress(new User(), addr1);
        addresses.addAddress(new User(), addr2);
        
        //then
        assertThat(addresses.getAddresses().size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("이미 존재하는 주소가 입력되면 무시한다.")
    void ifDuplicatedAddressAddedIgnore() {
        //given
        BuildingInfo building1 = new BuildingInfo();
        BuildingInfo sameBuilding = new BuildingInfo();
        Address addr1 = new Address(building1, "101호");
        Address addr2 = new Address(sameBuilding, "101호");
    
        //when
        addresses.addAddress(new User(), addr1);
        addresses.addAddress(new User(), addr2);
        
        //then
        assertThat(addresses.getAddresses().size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("넘겨받은 주소를 대표 주소로 설정한다.")
    void chooseAddressAndSetToCurrentAddress() {
        //given
        Address room101 = new Address(new BuildingInfo(), "101호");
        
        //when
        addresses.designateAsMainAddress(new User(), room101);
        
        //then
        assertThat(addresses.getMainAddress()).isEqualTo(room101);
    }
    
    @Test
    @DisplayName("대표 주소가 존재하지 않으면 예외가 발생한다.")
    void throwIfMainAddressDoesNotExistWhenGet() {
        //given
        //when
        ApiException exception = assertThrows(ApiException.class, () -> addresses.getMainAddress());
    
        //then
        assertThat(exception.getError()).isEqualTo(ErrorCode.ADDRESS_DOES_NOT_EXIST);
    }
    
    @Test
    @DisplayName("주소 목록에 없는 주소를 넘겨받으면 주소 목록에 추가된다.")
    void chooseAddress() {
        //given
        Address room101 = new Address(new BuildingInfo(), "101호");
        
        //when
        addresses.designateAsMainAddress(new User(), room101);
        
        //then
        assertThat(addresses.getAddresses().contains(room101)).isTrue();
    }
    
    @Test
    @DisplayName("주소 목록에 존재하는 주소를 삭제할 수 있다.")
    void removeAddress() {
        //given
        Address room101 = new Address(new BuildingInfo(), "101호");
        addresses.addAddress(new User(), room101);
        
        //when
        addresses.removeAddress(room101);
    
        //then
        boolean result = addresses.getAddresses().contains(room101);
        assertThat(result).isFalse();
    }
    
    @Test
    @DisplayName("주소 목록에서 존재하지 않는 주소를 삭제하려고 하면 예외를 던진다.")
    void ignoreRemoveIfAddressNotExists() {
        //given
        Address room101 = new Address(new BuildingInfo(), "101호");
        
        //when
        ApiException exception = assertThrows(ApiException.class, () -> addresses.removeAddress(room101));
        
        //then
        assertThat(exception.getError()).isEqualTo(ErrorCode.ADDRESS_DOES_NOT_EXIST);
    }
    
    @Test
    void addressesEqualityTest() {
        assertThat(new Addresses()).isEqualTo(new Addresses());
    }
    
    @Test
    void addressEqualityTest() {
        assertThat(new Address()).isEqualTo(new Address());
    }
    
    @Test
    void addressEqualityTest2() {
        assertThat(new Address(new BuildingInfo(), "101")).isEqualTo(new Address(new BuildingInfo(), "101"));
    }
}
