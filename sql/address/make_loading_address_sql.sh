#!/bin/sh

# 이 스크립트를 실행하면 juso.go.kr에서 받은 주소DB를 UTF8로 인코딩하고, 데이터베이스에 불러올 수 있는 쿼리를 생성한다.(실행위치의 query.txt 파일에 저장)
# 네비게이션용 DB (https://www.juso.go.kr/addrlink/addressBuildDevNew.do?menu=navi) 를 사용했다.
# 실제 데이터를 전부 다 로딩하면 시간이 오래걸리니, 간단하게 테스트해보려면 이 스크립트를 실행하지 말고 `1_sample_address_loading.sql` 을 사용하면 된다.

#############################################################################
######## '~/project/mysql/buildings' 경로는 실제 txt파일이 있는 경로로 수정해야 함######
#############################################################################


# 주어진 경로에 있는 match_build_로 시작하는 txt 데이터를 cp949 -> utf8 로 인코딩
find ~/project/mysql/buildings -type f -name 'match_build_*.txt' -exec sh -c 'iconv -f CP949 -t UTF-8 "$0" > "$0.tmp"' '{}' \; -exec mv -f '{}.tmp' '{}' \;

# 각 txt 파일을 불러오는 쿼리 생성
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

