# Moving-Out

## Application 환경정보
* Java 11 ver
* Spring boot 2.5.4
* maven
* Oracle

## 모듈 구성
* 아래와 같이 Multi 모듈로 구성하였습니다.
```
moving-out(root)
  │ 
  └── moving-out-common
  │    - 모듈간 공통적인 기능(ex. DB 저장) 
  |    - 공통적인 비즈니스 처리 로직
  └── moving-out-api
  │    - 실제 Front-End와 API연동 및 서비스에 필요한 비즈니스 로직
  └── moving-out-admin
       - App 모니터링 및 설정 변경에 필요한 APM 서버
```

* 추후 Spring-batch 도입이 필요한 경우 별도 모듈로 추가 예정


## Docker로 Oracle DB 실행하기

> #### 로컬에 Oracle Express 서버 환경이 설치가 되신 분이라면 이 단계를 건너 뛰어도 상관 없습니다.
> * 설치 가이드(생활코딩-Oracle) : https://opentutorials.org/course/3885/26280

___

1. 각자 로컬 OS 환경에 맞는 Docker를 설치합니다.
    * windows10에서 Docker 설치하기 가이드 : https://www.lainyzine.com/ko/article/a-complete-guide-to-how-to-install-docker-desktop-on-windows-10/

2. 해당 프로젝트의 docker 폴더에 Command 창을 열고 아래와 같은 명령어를 칩니다.
```
docker-compose up -d
```

![](./image/docker_01.jpg)

3. 아래와 같이 컨테이너 인스턴스가 올라가는 것을 확인해봅니다.
![](./image/docker_02.jpg)
   * 혹은 ```docker ps``` 명령어로 확인 가능합니다.
   * ![](./image/docker_03.jpg)

4. 예기치 않은 이유로 도커에 뜬 컨테이너를 초기화 시키고 싶을때 는 아래 명령어를 칩니다.(같은 디렉토리에서 실행하셔야 합니다.)
```
docker-compose down -v
```

## 인증 인가 기능 추가

### 프로그램 로딩 전 아래 쿼리 실행
* JWT 인증 기능 추가로 로컬 테스트시 프로그램 로딩후 아래 쿼리를 먼저 실행해주세요
  * 유저 권한(ROLE) 정보 초기화 입니다. 
```sql
INSERT INTO TB_AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO TB_AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');
commit;
```

### 아래와 같은 순서로
1. 가입 API
* POST / http://localhost:8080/api/signup

```json
{
	"userId":"test1",
	"password":"1234",
	"username":"jay",
	"address":null,
	"phoneNumber":"010000000"
}
```

2. 로그인 API
* POST / http://localhost:8080/api/authenticate
```
{
	"userId":"test1",
	"password":"1234"
}
```

* 응답으로 token을 받는다.

응답 예시
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2Mjk3MTMzMzZ9.0VXmNvaEZJQoOdWaJ_m1JxNkz5tv5vWZfXAUrbOqKewhpi71FqNJjgsbRd44XBbxAgrfmZCGSoAlhYRlxIdkHA"
}
```

3. 로그인 이후
* GET / http://localhost:8080/api/user
헤더에 Bearer 토큰으로 2.에서 응답받은 JWT 토큰을 요청에 넣어서 보낸다.