ALTER TABLE MENU_GROUP CHANGE RESTAURANT_ID SHOP_ID INT;
ALTER TABLE BUSINESS_HOUR DROP COLUMN ID;
ALTER TABLE BUSINESS_HOUR
    ADD PRIMARY KEY (SHOP_ID, DAY_OF_WEEK);

CREATE TABLE CATEGORY
(
    ID        INT,
    NAME      VARCHAR(10) UNIQUE KEY,
    ENUM_NAME VARCHAR(20) UNIQUE KEY, -- mybatis로 enum 매핑시킬 때 필요함
    PRIMARY KEY (ID)
);

CREATE TABLE CATEGORY_SHOP
(
    CATEGORY_ID INT,
    SHOP_ID     INT,
    PRIMARY KEY (CATEGORY_ID, SHOP_ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID),
    FOREIGN KEY (SHOP_ID) REFERENCES SHOP (ID)
)
