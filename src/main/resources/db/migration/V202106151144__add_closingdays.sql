CREATE TABLE CLOSING_DAY
(
    ID           INT AUTO_INCREMENT,
    SHOP_ID      INT         NOT NULL,
    CLOSING_TYPE VARCHAR(20) NOT NULL, -- CYCLIC_REGULAR, WEEKLY_REGULAR, TEMPORARY, LEGAL_HOLIDAY
    CYCLE_TYPE   VARCHAR(20),          -- RegularClosingDay 전용
    DAY_OF_WEEK  VARCHAR(10),          -- RegularClosingDay 전용
    FROM_DATE    DATE,                 -- TemporaryClosingDay 전용
    TO_DATE      DATE,                 -- TemporaryClosingDay 전용
    PRIMARY KEY (ID),
    FOREIGN KEY (SHOP_ID) REFERENCES SHOP (ID)
);

-- 영업시간인 가게들을 찾고 휴무일에도 포함되는 가게를 찾아서 차집합인 가게만 영업중인 가게 후보
-- CYCLE_TYPE이 null(매주)인 경우는 비교대상과 요일까지 일치하면 무조건 휴무인데, 가장 흔한 조건일 것 같아서 추가함
CREATE INDEX idx_regular on CLOSING_DAY (DAY_OF_WEEK, CYCLE_TYPE);
CREATE INDEX idx_clsong_type on CLOSING_DAY (CLOSING_TYPE);
