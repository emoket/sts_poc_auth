# POC 1차 [ 인증 ]

PoC 시나리오 중 인증 부분 구현 소스

## 모듈

### 1. Auth Center

> JWT 토큰 서비스

`Spring Security`를 통해 username과 password를 인증하고, 토큰을 생성

* /login
* POST {username, password} 전송

### 2. Backend Service

> ADMIN, USER, GUEST 서비스

아래 URL로 매핑된 단순 서비스를 실행

* /admin-only
* /user-service
* /public-service

### 3. API-Gateway

> Netflix API Gateway Zuul

* `auth-center` 와 `back-end` 서비스 라우팅 정의
* JWT 토큰 검증
* 롤-베이스 인증 정의 (Spring Security)

허용 범위

| URL                     | 비고             |
| ----------------------- | ---------------- |
| /login                  | 모두 허용         |
| /backend/public-service | 모두 허용         |
| /backend/admin-only     | `ADMIN` 롤만 허용 |
| /backend/user-service   | `USER` 롤만 허용  |


## Run and Verify

### 1. Compile and package
```bash
mvn clean package
```

### 2. Start services
```bash
java -jar auth-center-1.0.0.jar
java -jar backend-service-1.0.0.jar
java -jar api-gateway-1.0.0.jar
```

### 3. Get tokens
```bash
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"emoket","password":"emoket"}' http://localhost:8080/login
```
You will see the token in response header for user `emoket`. Note that the status code `401` will be returned if you provide incorrect username or password. And similarly, get token for user `admin`:
```bash
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"admin"}' http://localhost:8080/login
```
The user `admin` is defined with two roles: `USER` and `ADMIN`, while `emoket` is only a `USER`.

### 4. Verify
The general command to verify if the auth works is as follows:
```bash
curl -i -H "Authorization: Bearer token-you-got-in-step-3" http://localhost:8080/backend/user-service
```
or without token:
```bash
curl -i http://localhost:8080/backend/user-service
```
You can change the token and the URL as need. To sum up, the following table represents all possible response status codes while sending requests to different URLs with different tokens:

|                                     | /backend/admin-only | /backend/user-service | /backend/public-service |
| ----------------------------------- | -------------- | ------------- | -------------- |
| `admin` token (role `USER` `ADMIN`) | 200            | 200           | 200            |
| `emoket` token (role `USER`)        | 403            | 200           | 200            |
| no token                            | 401            | 401           | 200            |

---

## [ 참조 링크 ]
* https://isme2n.github.io/devlog/2017/04/14/JWT/

## JWT 동작 흐름

![](https://t1.daumcdn.net/cfile/tistory/99AFF9335A255C1142)

1. 로그인 요청

2. 로그인 정보를 토대로 토큰 발행

3. 클라이언트에게 토큰 전달

4. 클라이언트는 토큰을 저장해두고 서버에 요청할 때마다 토큰을 함께 전달

5. 서버는 토큰을 검증

6. 검증 결과 응답
