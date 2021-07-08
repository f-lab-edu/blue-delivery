package com.delivery.utility.address;

import static java.util.Objects.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;

@Entity
public class BuildingInfo {
    @ManyToOne
    @JoinColumn(name = "Address_Jurisdiction_Eup_Myon_Dong_Code")
    private CityToDong cityToDong;
    @Id
    private String buildingManagementNumber; // 건물관리번호
    private String buildingNameForSiGunGu; // 시군구용건물명
    private String isBasement; // 지하여부
    private Integer buildingMainNumber; // 건물본번(도로명 xx-yy 의 xx에 해당)
    private Integer buildingSubNumber; // 건물부번(도로명 xx-yy 의 yy에 해당)
    private String postalCode; // 우편번호
    public Integer numberOfGroundFloor; // 지상층수
    public Integer numberOfBasementFloor; // 지하층수
    private String classificationApartmentHouse; // 공통주택구분;
    private Integer numberOfBuildings; // 건물수
    private String detailBuildingName; // 상세건물명
    private Double xPosBuilding; // 건물중심점_x좌표
    private Double yPosBuilding; // 건물중심점_y좌표
    private Double xPosEntrance; // 출입구_x좌표
    private Double yPosEntrance; // 출입구_y좌표
    private String roadName; // 도로명
    private String roadNameEng; // 도로명(영문)
    private String roadNameCode; // 도로명코드
    
    public BuildingInfo() {
    
    }
    
    public BuildingInfo(String buildingManagementNumber) {
        this.buildingManagementNumber = buildingManagementNumber;
    }
    
    @Builder
    public BuildingInfo(CityToDong cityToDong, String buildingManagementNumber, String buildingNameForSiGunGu,
                        String isBasement, Integer buildingMainNumber, Integer buildingSubNumber,
                        String postalCode, Integer numberOfGroundFloor, Integer numberOfBasementFloor,
                        String classificationApartmentHouse, Integer numberOfBuildings, String detailBuildingName,
                        Double xPosBuilding, Double yPosBuilding, Double xPosEntrance, Double yPosEntrance,
                        String roadName, String roadNameEng, String roadNameCode) {
        this.cityToDong = cityToDong;
        this.buildingManagementNumber = buildingManagementNumber;
        this.buildingNameForSiGunGu = buildingNameForSiGunGu;
        this.isBasement = isBasement;
        this.buildingMainNumber = buildingMainNumber;
        this.buildingSubNumber = buildingSubNumber;
        this.postalCode = postalCode;
        this.numberOfGroundFloor = numberOfGroundFloor;
        this.numberOfBasementFloor = numberOfBasementFloor;
        this.classificationApartmentHouse = classificationApartmentHouse;
        this.numberOfBuildings = numberOfBuildings;
        this.detailBuildingName = detailBuildingName;
        this.xPosBuilding = xPosBuilding;
        this.yPosBuilding = yPosBuilding;
        this.xPosEntrance = xPosEntrance;
        this.yPosEntrance = yPosEntrance;
        this.roadName = roadName;
        this.roadNameEng = roadNameEng;
        this.roadNameCode = roadNameCode;
    }
    
    public String getRoadNameAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(cityToDong.getCityAndSiGunGu()).append(" ").append(roadName).append(" ");
        if (nonNull(buildingMainNumber) && buildingMainNumber != 0) {
            sb.append(buildingMainNumber).append("-");
        }
        sb.append(buildingSubNumber).append(",");
        if (nonNull(detailBuildingName)) {
            sb.append(" ").append(detailBuildingName);
        }
        return sb.toString();
    }
    
    public String getBuildingDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(cityToDong.getEupMyonDong());
        if (nonNull(buildingNameForSiGunGu)) {
            sb.append(", ").append(buildingNameForSiGunGu);
        }
        return sb.toString();
    }
}
