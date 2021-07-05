CREATE TABLE SHOP
(
    ID   INT AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    INTRODUCE varchar(500),
    phone varchar(14),
    DELIVERY_AREA_GUIDE varchar(500),
    PRIMARY KEY (ID)
);

CREATE TABLE BUSINESS_HOUR
(
    ID          INT AUTO_INCREMENT,
    SHOP_ID     INT,
    OPEN        TIME,
    CLOSE       TIME,
    DAY_OF_WEEK VARCHAR(50),
    PRIMARY KEY (ID),
    FOREIGN KEY (SHOP_ID) REFERENCES SHOP (ID)
);

CREATE TABLE MENU
(
    ID  INT AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    PRICE INT,
    PRIMARY KEY (ID)
);

CREATE TABLE MENU_GROUP
(
    ID   INT AUTO_INCREMENT,
    RESTAURANT_ID INT NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    CONTENT VARCHAR(100),
    PRIMARY KEY (ID),
    FOREIGN KEY (RESTAURANT_ID) REFERENCES SHOP(ID)
);