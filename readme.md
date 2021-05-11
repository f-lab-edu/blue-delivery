# Blue Delivery

배달의민족 클론 코딩 프로젝트입니다. 

---

## 기능정의

**회원**

- 회원가입/로그인, 정보 관리, 탈퇴
- 가게/음식 검색하기
- 주문할 음식목록 만들기 (장바구니)
- 주문 하기
- 결제하기
- 리뷰작성

**가게**

- 가게 정보 관리
- 메뉴 등록
- 배달기사 호출
- 리뷰답글



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

