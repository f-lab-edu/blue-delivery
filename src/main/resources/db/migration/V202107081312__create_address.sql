-- 상세 주소 정보
create table address
(
    ADDRESS_ID                              bigint NOT NULL AUTO_INCREMENT COMMENT 'PK ID',
    detail                                  varchar(255) COMMENT '상세주소 (동-호수 등 입력받은 정보)',
    building_management_number              varchar(25) NOT NULL COMMENT 'FK 건물관리번호',
    USER_ID                                 bigint COMMENT 'FK USER ID',
    primary key (ADDRESS_ID)
) engine=InnoDB;

-- 주소DB 입력용 테이블
create table building_address
(
    building_management_number              varchar(25) NOT NULL COMMENT 'PK 건물관리번호',
    address_jurisdiction_eup_myon_dong_code varchar(10) COMMENT '주소관할읍면동코드',
    administrative_dong_code                varchar(10) COMMENT '행정동코드',
    administrative_dong_name                varchar(40) COMMENT '행정동명',
    building_main_number                    integer COMMENT '건물본번(도로명 xx-yy 의 xx에 해당)',
    building_name_for_si_gun_gu             varchar(40) COMMENT '시군구용건물명',
    building_name_history                   varchar(1000) COMMENT '건물변경이력',
    building_sub_number                     integer COMMENT '건물부번(도로명 xx-yy 의 yy에 해당)',
    city                                    varchar(40) COMMENT '시도명',
    city_name_eng                           varchar(40) COMMENT '시도명(영문)',
    classification_apartment_house          varchar(1) COMMENT '공동주택구분',
    classification_eup_myon_dong            varchar(1) COMMENT '읍면동구분',
    classification_of_building_use          varchar(100) COMMENT '건축물용도분류',
    detail_building_name                    varchar(100) COMMENT '상세건물명',
    detail_building_name_history            varchar(1000) COMMENT '상세건물명변경이력',
    eup_myon_dong_name                      varchar(40) COMMENT '읍면동명',
    eup_myon_dong_name_eng                  varchar(40) COMMENT '읍면동명(영문)',
    is_basement                             varchar(1) COMMENT '지하여부',
    is_resident                             varchar(1) COMMENT '거주여부',
    move_reason_code                        varchar(2) COMMENT '이동사유코드',
    number_of_basement_floor                integer COMMENT '지하층수',
    number_of_buildings                     integer COMMENT '건물수',
    number_of_ground_floor                  integer COMMENT '지상층수',
    postal_code                             varchar(5) COMMENT '우편번호',
    road_name                               varchar(80) COMMENT '도로명',
    road_name_code                          varchar(12) COMMENT '도로명코드',
    road_name_eng                           varchar(80) COMMENT '도로명(영문)',
    si_gun_gu                               varchar(40) COMMENT '시군구',
    si_gun_gu_name_eng                      varchar(40) COMMENT '시군구(영문)',
    x_pos_building                          decimal(15, 6) COMMENT '건물중심점_x좌표',
    x_pos_entrance                          decimal(15, 6) COMMENT '출입구_x좌표',
    y_pos_building                          decimal(15, 6) COMMENT '건물중심점_x좌표',
    y_pos_entrance                          decimal(15, 6) COMMENT '출입구_x좌표',
    primary key (building_management_number)
) engine=InnoDB;

create table building_info
(
    building_management_number              varchar(25) NOT NULL COMMENT 'PK 건물관리번호',
    building_name_for_si_gun_gu             varchar(40) COMMENT '시군구용건물명',
    building_main_number                    integer COMMENT '건물본번',
    building_sub_number                     integer COMMENT '건물부번',
    classification_apartment_house          varchar(1) COMMENT '공동주택구분',
    detail_building_name                    varchar(100) COMMENT '상세건물명',
    is_basement                             varchar(1) COMMENT '지하여부',
    number_of_basement_floor                integer COMMENT '지하층수',
    number_of_buildings                     integer COMMENT '건물수',
    number_of_ground_floor                  integer COMMENT '지상층수',
    postal_code                             varchar(5) COMMENT '우편번호',
    road_name                               varchar(80) COMMENT '도로명',
    road_name_code                          varchar(12) COMMENT '도로명코드',
    road_name_eng                           varchar(80) COMMENT '도로명(영문)',
    x_pos_building                          double precision COMMENT '건물중심점_x좌표',
    x_pos_entrance                          double precision COMMENT '출입구_x좌표',
    y_pos_building                          double precision COMMENT '건물중심점_x좌표',
    y_pos_entrance                          double precision COMMENT '출입구_x좌표',
    address_jurisdiction_eup_myon_dong_code varchar(10) COMMENT 'FK 주소관할읍면동코드',
    primary key (building_management_number)
) engine=InnoDB;

create table city_to_dong
(
    address_jurisdiction_eup_myon_dong_code varchar(10) COMMENT '주소관할읍면동코드',
    city                                    varchar(40) COMMENT '시도명',
    city_name_eng                           varchar(40) COMMENT '시도명(영문)',
    classification_eup_myon_dong            varchar(1) COMMENT '읍면동구분',
    eup_myon_dong_name                      varchar(40) COMMENT '읍면동명',
    eup_myon_dong_name_eng                  varchar(40) COMMENT '읍면동명(영문)',
    si_gun_gu                               varchar(40) COMMENT '시군구',
    si_gun_gu_name_eng                      varchar(40) COMMENT '시군구(영문)',
    primary key (address_jurisdiction_eup_myon_dong_code)
) engine=InnoDB;


alter table address
    add constraint FK_Building_Info
        foreign key (building_management_number)
            references building_info (building_management_number);

alter table building_info
    add constraint FK_City
        foreign key (address_jurisdiction_eup_myon_dong_code)
            references city_to_dong (address_jurisdiction_eup_myon_dong_code);

alter table address
    add constraint FK_User
        foreign key (user_id)
            references user (USER_ID);

alter table USER
    add constraint FK_MAIN_ADDRESS
        foreign key (MAIN_ADDRESS_ID)
            references ADDRESS (ADDRESS_ID);

alter table address
    add constraint UC_Address
        unique (USER_ID, BUILDING_MANAGEMENT_NUMBER, DETAIL);