# Blue Delivery

배달의민족 클론 코딩 프로젝트입니다. 

---

## 기능정의
**가게**  
[위키참조](https://github.com/f-lab-edu/blue-delivery/wiki/%EA%B8%B0%EB%8A%A5%EC%A0%95%EC%9D%98:-%EA%B0%80%EA%B2%8C")

**고객**

- 회원가입/로그인, 정보 수정, 탈퇴
- 가게/음식 검색하기
- 주문할 음식목록 만들기 (장바구니)
- 주문하기
- 결제하기
- 리뷰작성

**라이더**

- 배달 요청 수신하기
- 배달 완료 처리하기



## 사용 기술

- Java11
- Spring boot
- Gradle
- MyBatis
- MySQL
- Docker



## 브랜치 전략

### Github Flow

Github Flow는 main 브랜치를 가 곧 product가 되는 전략입니다.

<img src="https://hackernoon.com/hn-images/1*iHPPa72N11sBI_JSDEGxEA.png" alt="img" style="zoom:50%;" />

(master = main) 

새로운 작업 전에 작업 내용을 담은 브랜치를 생성하고 작업합니다.

- feature-로그인구현
- readme-edit 등

작업이 끝나면 원격 브랜치로 push 하고, Pull Request를 통해 피드백을 받습니다. 

리뷰가 끝난 커밋은 main 브랜치로 병합됩니다.



### (로컬) 빌드 자동화

`git hook` 을 이용해 로컬환경에서 빌드 자동화를 구성했습니다.

**적용방법** 

커맨드라인에서 `git config core.hookspath .githooks` 설정

- git --version 으로 버전 확인 후 2.9 아래면 업데이트 필요
- 이후 commit 이나 push 명령시 자동으로 트리거가 실행됌

`commit` 

- 테스트 수행 
- Linting (checkstyle 플러그인 + <a href="https://naver.github.io/hackday-conventions-java">네이버 캠퍼스 핵데이 Java 코딩 컨벤션</a>)
    - [indentation-tab] indent 를 tab -> space로 변경
    - [no-trailing-spaces] 적용 안함 

`push` 

- 원격 저장소의 브랜치가 main 인지 확인 (main이면 push 불가능)
- main 브랜치를 pull해서 최신 상태 유지

## DB 형상관리
[flyway](https://flywaydb.org/documentation/usage/gradle/)

**migration**
- ./gradlew flywayMigrate  
  
**clean**
- ./gradlew flywayClean
