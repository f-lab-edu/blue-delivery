package com.delivery.utility.address;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
