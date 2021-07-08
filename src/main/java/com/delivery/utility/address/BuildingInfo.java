package com.delivery.utility.address;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BuildingInfo {
    @ManyToOne
    @JoinColumn(name = "Address_Jurisdiction_Eup_Myon_Dong_Code")
    private CityToDong cityToDong;
    @Id
    private String buildingManagementNumber; // 건물관리번호
    private String isBasement; // 지하여부
    private Integer buildingMainNumber; // 건물본번(도로명 xx-yy 의 xx에 해당)
    private Integer buildingSubNumber; // 건물부번(도로명 xx-yy 의 yy에 해당)
    private String postalCode; // 우편번호
    public Integer numberOfGroundFloor; // 지상층수
    public Integer numberOfBasementFloor; // 지하층수
    private String classificationApartmentHouse; // 공통주택구분;
    private String numberOfBuildings; // 건물수
    private String detailBuildingName; // 상세건물명
    private Double xPosBuilding; // 건물중심점_x좌표
    private Double yPosBuilding; // 건물중심점_y좌표
    private Double xPosEntrance; // 출입구_x좌표
    private Double yPosEntrance; // 출입구_y좌표
    private String roadName; // 도로명
    private String roadNameEng; // 도로명(영문)
    private String roadNameCode; // 도로명코드
}
