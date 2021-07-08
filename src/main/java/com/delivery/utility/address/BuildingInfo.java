package com.delivery.utility.address;

import javax.persistence.Column;
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
    @Column(length = 25)
    private String buildingManagementNumber; // 건물관리번호
    @Column(length = 1)
    private String isBasement; // 지하여부
    @Column(length = 5)
    private Integer buildingMainNumber; // 건물본번
    @Column(length = 5)
    private Integer buildingSubNumber; // 건물부번
    @Column(length = 5)
    private String postalCode; // 우편번호
    @Column(precision = 3)
    public int numberOfGroundFloor; // 지상층수
    @Column(precision = 3)
    public int numberOfBasementFloor; // 지하층수
    @Column(length = 1)
    private String classificationApartmentHouse; // 공통주택구분;
    @Column(length = 10)
    private String numberOfBuildings; // 건물수
    @Column(length = 100)
    private String detailBuildingName; // 상세건물명
    @Column(precision = 15, scale = 6)
    private Double xPosBuilding; // 건물중심점_x좌표
    @Column(precision = 15, scale = 6)
    private Double yPosBuilding; // 건물중심점_y좌표
    @Column(precision = 15, scale = 6)
    private Double xPosEntrance; // 출입구_x좌표
    @Column(precision = 15, scale = 6)
    private Double yPosEntrance; // 출입구_y좌표
    @Column(length = 80)
    private String roadNameEng; // 도로명(영문)
}
