CREATE TABLE CLOSING_DAYS
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

-- 나중에 영업중인 가게를 찾아낼 때, 영업시간인 가게들을 찾고 휴무일에도 포함되는 가게를 찾아서 차집합인 가게만 영업중으로 판단하기 위해 인덱스 준비
CREATE INDEX idx_temporary on CLOSING_DAYS (from_date, to_date, shop_id);
CREATE INDEX idx_dayofweek on CLOSING_DAYS (CYCLE_TYPE, DAY_OF_WEEK, SHOP_ID); -- CYCLE_TYPE이 null(매주)인 경우는 비교대상과 요일까지 일치하면 무조건 휴무
