package com.delivery.utility.address;

import static java.util.Objects.nonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;

@Entity
public class CityToDong {
    @Id
    private String addressJurisdictionEupMyonDongCode; // 주소관할읍면동코드
    private String city; // 시도명
    private String siGunGu; // 시군구명
    private String eupMyonDongName; // 읍면동명
    private String cityNameEng; // 시도명(영문)
    private String siGunGuNameEng; // 시군구명(영문)
    private String eupMyonDongNameEng; // 읍면동명(영문)
    private String classificationEupMyonDong; // 읍면동구분
    
    public CityToDong() {
    
    }
    
    @Builder
    public CityToDong(String addressJurisdictionEupMyonDongCode,
                      String city, String siGunGu, String eupMyonDongName, String cityNameEng, String siGunGuNameEng,
                      String eupMyonDongNameEng, String classificationEupMyonDong) {
        this.addressJurisdictionEupMyonDongCode = addressJurisdictionEupMyonDongCode;
        this.city = city;
        this.siGunGu = siGunGu;
        this.eupMyonDongName = eupMyonDongName;
        this.cityNameEng = cityNameEng;
        this.siGunGuNameEng = siGunGuNameEng;
        this.eupMyonDongNameEng = eupMyonDongNameEng;
        this.classificationEupMyonDong = classificationEupMyonDong;
    }
    
    public String getCityAndSiGunGu() {
        return new StringBuilder()
                .append(city).append(" ")
                .append(siGunGu)
                .toString();
    }
    
    public String getEupMyonDong() {
        if (nonNull(eupMyonDongName)) {
            return eupMyonDongName;
        }
        return "";
    }
}
