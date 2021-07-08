-- sample
-- 같은 경로에 있는 sample.txt (서울 일부)만 데이터베이스에 추가함

-- 주소 데이터를 DB에 추가
LOAD
DATA LOCAL INFILE './sample.txt'
INTO TABLE building_address
FIELDS TERMINATED BY '|'
OPTIONALLY ENCLOSED BY ''
LINES TERMINATED BY '\n'
(
        @Address_Jurisdiction_Eup_Myon_Dong_Code, @city, @si_gun_gu, @Eup_Myon_Dong_Name, @road_name_code,
        @road_name, @is_basement, @building_main_number, @building_sub_number, @postal_code,
        @building_management_number, @building_name_for_si_gun_gu, @Classification_Of_Building_Use,
        @administrative_dong_code, @administrative_dong_name,
        @number_Of_Ground_Floor, @number_Of_Basement_Floor, @Classification_Apartment_House,
        @Number_Of_Buildings, @detail_building_name,
        @Building_Name_History, @Detail_Building_Name_History, @Is_Resident, @X_Pos_Building, @Y_Pos_Building,
        @X_Pos_Entrance, @Y_Pos_Entrance,  @City_Name_Eng, @Si_Gun_Gu_Name_Eng, @Eup_Myon_Dong_Name_Eng,
        @Road_Name_Eng, @Classification_Eup_Myon_Dong, @move_reason_code
)
    SET Number_Of_Buildings=@Number_Of_Buildings, city=@city, si_gun_gu=@si_gun_gu,
        Address_Jurisdiction_Eup_Myon_Dong_Code=@Address_Jurisdiction_Eup_Myon_Dong_Code, Is_Resident=@Is_Resident,
        X_Pos_Building=@X_Pos_Building, Y_Pos_Building=@Y_Pos_Building, X_Pos_Entrance=@X_Pos_Entrance,
        road_name_code=@road_name_code, road_name=@road_name, is_basement=@is_basement,
        building_main_number=@building_main_number, building_sub_number=@building_sub_number,
        Y_Pos_Entrance=@Y_Pos_Entrance, detail_building_name=@detail_building_name,
        building_management_number=@building_management_number, Eup_Myon_Dong_Name_Eng=@Eup_Myon_Dong_Name_Eng,
        administrative_dong_code=@administrative_dong_code, administrative_dong_name=@administrative_dong_name,
        postal_code=@postal_code, Road_Name_Eng=@Road_Name_Eng, move_reason_code=@move_reason_code,
        City_Name_Eng=@City_Name_Eng, Si_Gun_Gu_Name_Eng=@Si_Gun_Gu_Name_Eng,
        building_name_for_si_gun_gu=@building_name_for_si_gun_gu, Classification_Apartment_House=@Classification_Apartment_House,
        Classification_Eup_Myon_Dong=@Classification_Eup_Myon_Dong, Classification_Of_Building_Use=@Classification_Of_Building_Use,
        Building_Name_History=@Building_Name_History,Detail_Building_Name_History=@Detail_Building_Name_History,
        number_Of_Ground_Floor=@number_Of_Ground_Floor, number_Of_Basement_Floor=@number_Of_Basement_Floor,
        Eup_Myon_Dong_Name=@Eup_Myon_Dong_Name;
;

-- 추가한 주소들 중 도/시군구/동 관련 부분은 분리
insert into city_to_dong
select address_jurisdiction_eup_myon_dong_code,
       city,
       city_name_eng,
       classification_eup_myon_dong,
       eup_myon_dong_name,
       eup_myon_dong_name_eng,
       si_gun_gu,
       si_gun_gu_name_eng
from (select distinct address_jurisdiction_eup_myon_dong_code,
                      city,
                      city_name_eng,
                      classification_eup_myon_dong,
                      eup_myon_dong_name,
                      eup_myon_dong_name_eng,
                      si_gun_gu,
                      si_gun_gu_name_eng
      from building_address
     ) DT;

-- 추가한 주소들 중 건물관련 정보만 분리
insert into building_info
select building_management_number,
       building_main_number,
       building_sub_number,
       classification_apartment_house,
       detail_building_name,
       is_basement,
       number_of_basement_floor,
       number_of_buildings,
       number_of_ground_floor,
       postal_code,
       road_name_eng,
       x_pos_building,
       x_pos_entrance,
       y_pos_building,
       y_pos_entrance,
       address_jurisdiction_eup_myon_dong_code
from building_address;