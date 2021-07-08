#!/bin/sh

# 현재 위치에 있는 txt 데이터를 cp949 -> utf8 로 인코딩
find . -type f -name '*.txt' -exec sh -c 'iconv -f CP949 -t UTF-8 "$0" > "$0.tmp"' '{}' \; -exec mv -f '{}.tmp' '{}' \;

# 각 txt 파일을 불러오는 쿼리 생성
# '~/project/mysql/buildings' 부분은 주소 데이터의 경로에 맞게 설정
for entry in `find . -name match_build_\*.txt`; do
filename=`basename $entry`
    echo "\
    LOAD DATA LOCAL INFILE '~/project/mysql/buildings/${filename}' \
    INTO TABLE building_address \
    FIELDS TERMINATED BY '|' \
    OPTIONALLY ENCLOSED BY '' \
    LINES TERMINATED BY '\\\n' \
    (   @Address_Jurisdiction_Eup_Myon_Dong_Code, @city, @si_gun_gu, @Eup_Myon_Dong_Name, @road_name_code,
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
        " >> query.txt;
done

