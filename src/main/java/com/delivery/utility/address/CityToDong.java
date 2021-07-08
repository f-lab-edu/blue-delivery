package com.delivery.utility.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CityToDong {
    @Id
    @Column(length = 10)
    private String addressJurisdictionEupMyonDongCode; // 주소관할읍면동코드
    @Column(length = 40)
    private String city; // 시도명
    @Column(length = 40)
    private String siGunGu; // 시군구명
    @Column(length = 40)
    private String eupMyonDongName; // 읍면동명
    @Column(length = 40)
    private String cityNameEng; // 시도명(영문)
    @Column(length = 40)
    private String siGunGuNameEng; // 시군구명(영문)
    @Column(length = 40)
    private String eupMyonDongNameEng; // 읍면동명(영문)
    @Column(length = 1)
    private String classificationEupMyonDong; // 읍면동구분
}
