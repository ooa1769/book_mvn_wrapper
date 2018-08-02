# 카카오 API를 활용한 책 검색 서비스

## 구현목록
### 회원가입, 로그인
- 회원 가입
  - 유효성 검사
  - BCryptPasswordEncoder 암호화 적용
- 로그인
  - 아이디 패스워드 일치 유효성 검사
- 사용자 정보 가져오기
  - @LoginMember 애노테이션
  - HandlerMethodArgumentResolver구현체 등록하여 로그인 사용자 웹 객체를 컨트롤러의 인자로 받게 처리.

### 검색 기능(카카오/네이버)
- 메인화면 카카오/네이버 선택 후 검색
- 검색조건, 정렬조건에 따른 페이징 처리
- 추후 새로운 데이터소스 (네이버, 알라딘API 등등) 추가시에도 확장성 있게 구조 설계
  - ApiType에 따라서 코드성 데이터(검색조건, 정렬조건, 카테고리 등등)가 상이하기 때문에 EnumMapper클래스를 빈으로 등록해 확장에 용이하게 설계
  - 사용하는 쪽 SearchService -> ApiType에 따라서 BookSearchClient인터페이스를 바라보도록 구조 설계
  - 추후 새로운 Api타입 추가시 (네이버, 알라딘API 등등) BookSearchClient 구현체를 추가해서 사용하는 쪽인 SearchService에서는 확장에는 열려있고 변경에는 닫혀있도록 OCP원칙을 적용
- 비회원인 경우도 검색 가능 하도록 구현
- 회원인경우 검색어 조회시 검색 히스토리 저장

### 상세보기
- 목록조회 화면으로 이동시 이전 페이지에서 검색한 조건 그대로 유지

### 북마크
- Rest API
  - 추가 /api/bookmarks POST
  - 삭제 /api/bookmarks/{id} DELETE
  - 조회 /api/bookmarks GET
  - 등록여부 조회 /api/bookmarks/{isbn} GET
- 조회화면
  - 정렬(날짜, 제목), 페이징 처리
- 북마크 상세보기 화면

### 검색 내역
- 검색 내역 등록
  - 기존에 등록된 검색어를 조회한 경우 현재 날짜로 업데이트
- 검색 목록 조회
  - 정렬(날짜, 키워드), 페이징 처리

### 기능 위주의 테스트 코드 작성
- PagingInfo
- 조회된 검색 결과 Book클래스를 연관성 있는 데이터를 따로 클래스로 분리 처리
  - Isbn : Isbn 유효성 검증(10자리 혹은 13자리)
    - isbn의 유효성 검증 부분을 Isbn객체에게 위임
  - Price : 원가, 판매가격, 할인액
    - Price의 할인액을 구하는 로직을 Price에 위임
- 도출된 class객체에게 기능을 요청.
  - 최대한 get()한 메소드를 가져와서 비지니스 로직을 처리하지 않도록 함.
  - 1depth의 indent를 유지하려고 함.

### JPA 기반
- Member(1) - BookMark(N)
- Member(1) - SearchHistory(N)
- AbstractEntity클래스 상속(@MappedSuperclass)
  - AuditingEntityListener 를 통해 엔티티객체 등록시 시간정보 자동 등록.
- BooleanToYNConverter
  - boolean값 데이터 db저장시 Y/N값으로 컨버팅, 반대로 DB에서 값 조회시 Boolean값으로 컨버팅

### 기타
- Java8 사용
  - Stream API, Optional 활용
- LoggingAspect 도입
  - 메소드 전후 인자값 로깅
  - Controller단이나 Service단에 포인트컷 적용.
- 애플리케이션 구동시 import.sql파일의 초기 데이터 등록
- ExceptionHandler 처리


## 사용한 라이브러리 및 오픈소스
- Java8
- SpringBoot
- SpringSecurity
- JPA
- H2DB
- Thymeleaf
- Lombok
- Maven

## 설치 및 실행
```
git clone https://github.com/ooa1769/book-service.git
cd book-service
mvn spring-boot:run
```

- 테스트시 아래 계정 사용(서버 구동시 테스트용 계정 생성)
    - test1@test.com / 1234
    - test2@test.com / 1234
    
## 구현화면
- 책 검색 화면 <br>
![book_search](./images/book_search.png)
![book_search](./images/book_search2.png)
![book_search](./images/book_search3.png)
![book_search](./images/book_search4.png)

- 책 상세 정보 화면<br>
![book_view](./images/book_view.png)

- 북마크 화면<br>
![bookmark](./images/bookmark.png)

- 최근 검색 히스토리 화면<br>
![search_history](./images/search_history.png)

- 회원 가입 유효성 검증<br>
![member_register](./images/member_register.png)

- 로그인 아이디/패스워드 검증<br>
![member_login](./images/member_login.png)
