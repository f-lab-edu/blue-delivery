
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
       building_name_for_si_gun_gu,
       building_main_number,
       building_sub_number,
       classification_apartment_house,
       detail_building_name,
       is_basement,
       number_of_basement_floor,
       number_of_buildings,
       number_of_ground_floor,
       postal_code,
       road_name,
       road_name_code,
       road_name_eng,
       x_pos_building,
       x_pos_entrance,
       y_pos_building,
       y_pos_entrance,
       address_jurisdiction_eup_myon_dong_code
from building_address;