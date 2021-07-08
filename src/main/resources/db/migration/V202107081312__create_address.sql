create table address
(
    id                                      bigint not null,
    detail                                  varchar(255),
    building_management_number              varchar(25),
    address_jurisdiction_eup_myon_dong_code varchar(10),
    primary key (id)
) engine=InnoDB;

create table building_address
(
    building_management_number              varchar(25) NOT NULL COMMENT '건물관리번호',
    address_jurisdiction_eup_myon_dong_code varchar(10) COMMENT '주소관할읍면동코드',
    administrative_dong_code                varchar(10) COMMENT '행정동코드',
    administrative_dong_name                varchar(40) COMMENT '행정동명',
    building_main_number                    integer,
    building_name_for_si_gun_gu             varchar(40),
    building_name_history                   varchar(1000),
    building_sub_number                     integer,
    city                                    varchar(40),
    city_name_eng                           varchar(40),
    classification_apartment_house          varchar(1),
    classification_eup_myon_dong            varchar(1),
    classification_of_building_use          varchar(100),
    detail_building_name                    varchar(100),
    detail_building_name_history            varchar(1000),
    eup_myon_dong_name                      varchar(40),
    eup_myon_dong_name_eng                  varchar(40),
    is_basement                             varchar(1),
    is_resident                             varchar(1),
    move_reason_code                        varchar(2),
    number_of_basement_floor                integer,
    number_of_buildings                     varchar(10),
    number_of_ground_floor                  integer,
    postal_code                             varchar(5),
    road_name                               varchar(80),
    road_name_code                          varchar(12),
    road_name_eng                           varchar(80),
    si_gun_gu                               varchar(40),
    si_gun_gu_name_eng                      varchar(40),
    x_pos_building                          double precision,
    x_pos_entrance                          double precision,
    y_pos_building                          double precision,
    y_pos_entrance                          double precision,
    primary key (building_management_number)
) engine=InnoDB;

create table building_info
(
    building_management_number              varchar(25) not null,
    building_main_number                    integer,
    building_sub_number                     integer,
    classification_apartment_house          varchar(1),
    detail_building_name                    varchar(100),
    is_basement                             varchar(1),
    number_of_basement_floor                integer,
    number_of_buildings                     varchar(10),
    number_of_ground_floor                  integer,
    postal_code                             varchar(5),
    road_name_eng                           varchar(80),
    x_pos_building                          double precision,
    x_pos_entrance                          double precision,
    y_pos_building                          double precision,
    y_pos_entrance                          double precision,
    address_jurisdiction_eup_myon_dong_code varchar(10),
    primary key (building_management_number)
) engine=InnoDB;

create table city_to_dong
(
    address_jurisdiction_eup_myon_dong_code varchar(10) not null,
    city                                    varchar(40),
    city_name_eng                           varchar(40),
    classification_eup_myon_dong            varchar(1),
    eup_myon_dong_name                      varchar(40),
    eup_myon_dong_name_eng                  varchar(40),
    si_gun_gu                               varchar(40),
    si_gun_gu_name_eng                      varchar(40),
    primary key (address_jurisdiction_eup_myon_dong_code)
) engine=InnoDB;


alter table address
    add constraint FK_Building_Info
        foreign key (building_management_number)
            references building_info (building_management_number);

alter table address
    add constraint FK_City_Info
        foreign key (address_jurisdiction_eup_myon_dong_code)
            references city_to_dong (address_jurisdiction_eup_myon_dong_code);


alter table building_info
    add constraint FK_City
        foreign key (address_jurisdiction_eup_myon_dong_code)
            references city_to_dong (address_jurisdiction_eup_myon_dong_code);