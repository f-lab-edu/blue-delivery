INSERT INTO MENU_GROUP (ID, SHOP_ID, NAME)
VALUES (1, 1, '대표 메뉴');

INSERT INTO MENU (ID, NAME, PRICE, GROUP_ID, COMPOSITION, CONTENT, STATUS, IS_MAIN)
VALUES (1, '치킨 부리또', 3500, 1, '부리또 + 피클', '치킨이 들어간 부리또', 'DEFAULT', true);

INSERT INTO OPTION_GROUP (OPTION_GROUP_ID, MENU_ID, NAME, OPTION_REQUIRED, MINIMUM_OPTION, MAXIMUM_OPTION)
VALUES (1, 1, '맛 선택', true, 1, 1)
