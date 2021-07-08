package com.delivery.utility.address;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.delivery.utility.address.BuildingInfo.BuildingInfoBuilder;

public class AddressTest {
    CityToDong cityToDong = CityToDong.builder().city("서울특별시").siGunGu("종로구").eupMyonDongName("청운동").build();
    BuildingInfoBuilder buildingInfoBuilder;
    
    @BeforeEach
    void setup() {
        buildingInfoBuilder = BuildingInfo.builder().roadName("자하문로36길").buildingSubNumber(14);
    }
    
    @Test
    @DisplayName("상세건물명(동), 건물본번(도로명주소 xx-yy의 xx), 시군구용건물명(건물이름) 포함 출력")
    void printFullAddressTest() {
        BuildingInfo buildingInfo = buildingInfoBuilder.buildingMainNumber(16)
                .detailBuildingName("1동").buildingNameForSiGunGu("청운벽산빌리지").cityToDong(cityToDong).build();
        Address address = new Address(buildingInfo, "333호");
        String fullAddress = address.getFullRoadNameAddress();
        assertThat(fullAddress).isEqualTo("서울특별시 종로구 자하문로36길 16-14, 1동 333호(청운동, 청운벽산빌리지)");
    }
    
    @Test
    @DisplayName("상세건물명 제외 출력")
    void printWithoutBuildingNameForSiGunGu() {
        BuildingInfo buildingInfo = buildingInfoBuilder.buildingMainNumber(16)
                .buildingNameForSiGunGu("청운벽산빌리지").cityToDong(cityToDong).build();
        Address address = new Address(buildingInfo, "333호");
        String fullAddress = address.getFullRoadNameAddress();
        assertThat(fullAddress).isEqualTo("서울특별시 종로구 자하문로36길 16-14, 333호(청운동, 청운벽산빌리지)");
    }
    
    @Test
    @DisplayName("상세건물명, 시군구용건물명 제외 출력")
    void printWithoutBuildingDetailAndNameForSiGunGu() {
        BuildingInfo buildingInfo = buildingInfoBuilder.buildingMainNumber(16).cityToDong(cityToDong).build();
        Address address = new Address(buildingInfo, "333호");
        String fullAddress = address.getFullRoadNameAddress();
        assertThat(fullAddress).isEqualTo("서울특별시 종로구 자하문로36길 16-14, 333호(청운동)");
    }
    
    @Test
    @DisplayName("상세건물명, 시군구용건물명, 건물본번 제외 출력")
    void printWithoutBuildingDetailAndNameForSiGunGuAndMainNumber() {
        BuildingInfo buildingInfo = buildingInfoBuilder.cityToDong(cityToDong).build();
        Address address = new Address(buildingInfo, "333호");
        String fullAddress = address.getFullRoadNameAddress();
        assertThat(fullAddress).isEqualTo("서울특별시 종로구 자하문로36길 14, 333호(청운동)");
    }
}
