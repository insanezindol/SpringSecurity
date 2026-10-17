# Spring Security 예제 프로젝트

Spring Security를 활용한 웹 애플리케이션 인증/인가 시스템 구현 예제입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot와 Spring Security를 이용하여 사용자 로그인, 권한 관리, 세션 관리 기능을 구현한 웹 애플리케이션입니다.

> 기존 Spring Framework 4.2 + XML 설정(`web.xml`, `*-context.xml`) 기반 프로젝트를 **Java 25 + Spring Boot 4** 기반 Java 설정으로 마이그레이션했습니다.

## 🛠️ 기술 스택

| 구분 | 버전 |
| --- | --- |
| **Java** | 25 |
| **Spring Boot** | 4.1.1 |
| **Spring Framework** | 7.0.x |
| **Spring Security** | 7.1.x |
| **Servlet 컨테이너** | Embedded Tomcat 11 (Jakarta Servlet 6.1) |
| **뷰** | JSP + JSTL 3.0 (Jakarta Tags), Spring Security Taglibs |
| **로깅** | SLF4J + Logback (Spring Boot 기본) |
| **빌드** | Maven 3.9.16 (Maven Wrapper 포함) |
| **테스트** | JUnit 5, Spring Security Test (MockMvc) |

## 📁 프로젝트 구조

```
SpringSecurity/
├── .mvn/wrapper/                          # Maven Wrapper 설정
├── mvnw, mvnw.cmd                         # Maven Wrapper 실행 스크립트
├── pom.xml                                # Maven 설정
└── src/
    ├── main/
    │   ├── java/com/test/myapp/
    │   │   ├── Application.java           # Spring Boot 메인 클래스 (WAR 배포 지원)
    │   │   ├── SecurityConfig.java        # Spring Security 설정 (기존 security-context.xml)
    │   │   ├── WebConfig.java             # Spring MVC 설정 (기존 servlet-context.xml)
    │   │   ├── LoginController.java       # 로그인 관련 컨트롤러
    │   │   └── MainController.java        # 메인 페이지 컨트롤러
    │   ├── resources/
    │   │   └── application.properties     # 뷰 리졸버, 로깅 설정
    │   └── webapp/
    │       ├── resources/js/              # JavaScript 파일 (jQuery)
    │       └── WEB-INF/views/             # JSP 뷰 파일
    │           ├── login.jsp              # 로그인 페이지
    │           ├── main.jsp               # 메인 페이지
    │           └── showUser.jsp           # 사용자 정보 페이지
    └── test/java/com/test/myapp/
        └── SecurityIntegrationTests.java  # 인증/인가 통합 테스트
```

## 🔐 주요 기능

### 1. 사용자 인증 (Authentication)

-   폼 기반 로그인 (`user_id` / `user_pwd` 파라미터)
-   사용자 ID/비밀번호 검증
-   로그인 실패 처리
-   로그인 상태 유지 (Remember-Me, 7일)

### 2. 권한 관리 (Authorization)

-   **ROLE_DEV**: 개발자 권한
-   **ROLE_ADMIN**: 관리자 권한
-   **ROLE_USER**: 일반 사용자 권한

### 3. URL 접근 제어

-   `/resources/**`, `/*.ico`: 전체 허용 (정적 리소스)
-   `/login/login.do`: 미로그인 사용자만 접근
-   `/dev/**`: 개발자 권한 필요
-   `/admin/**`: 관리자 또는 개발자 권한 필요
-   기타 경로: 로그인된 사용자(DEV/ADMIN/USER)만 접근

### 4. 세션 관리

-   로그인 성공 시 세션 생성
-   로그아웃 처리 (세션 무효화, `JSESSIONID`/`remember_me` 쿠키 삭제)
-   세션 만료 처리
-   중복 로그인 방지 (계정당 최대 1세션, 이전 세션 만료)

### 5. CSRF 보호

-   Spring Security 기본 CSRF 보호 활성화 (폼에 `_csrf` 토큰 포함)

## 👤 테스트 계정

| 아이디 | 비밀번호 | 권한 |
| --- | --- | --- |
| dev | 1234 | ROLE_DEV |
| admin | 1234 | ROLE_ADMIN |
| user | 1234 | ROLE_USER |
| anony | 1234 | ROLE_ANONYMOUS (로그인은 되지만 페이지 접근 불가) |

## 🚀 실행 방법

### 1. 사전 요구사항

-   JDK 25 이상
-   Maven은 별도 설치 불필요 (Maven Wrapper 사용)

`JAVA_HOME`이 JDK 25를 가리키도록 설정합니다.

```powershell
# Windows (PowerShell)
$env:JAVA_HOME = "C:\java\jdk-25.0.4.1"
```

```bash
# macOS / Linux
export JAVA_HOME=/path/to/jdk-25
```

### 2. 실행

```bash
# 개발 모드 실행
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```

### 3. 빌드 및 테스트

```bash
./mvnw clean package            # 테스트 실행 + target/myapp-1.0.0-BUILD-SNAPSHOT.war 생성
./mvnw test                     # 테스트만 실행
```

### 4. 배포

생성된 WAR 파일은 두 가지 방식으로 실행할 수 있습니다.

```bash
# 내장 Tomcat으로 단독 실행
java -jar target/myapp-1.0.0-BUILD-SNAPSHOT.war
```

-   또는 외부 **Tomcat 11** (Jakarta EE 11) 이상의 `webapps` 디렉토리에 `ROOT.war`로 배포합니다.
    (JSP의 링크가 `/login/...`, `/resources/...` 처럼 절대경로이므로 루트 컨텍스트로 배포해야 합니다.)

### 5. 접속

```
http://localhost:8080/login/login.do
```

## 📝 설정 파일 설명

### SecurityConfig.java

Spring Security의 핵심 설정으로, 다음 내용을 포함합니다:

-   URL 접근 권한 설정 (`authorizeHttpRequests`)
-   로그인/로그아웃 처리 설정 (`formLogin`, `logout`)
-   Remember-Me, 중복 로그인(세션) 제어 설정
-   사용자 인증 방식 설정 (In-Memory 사용자)

### WebConfig.java / application.properties

Spring MVC 관련 설정을 포함합니다:

-   리소스 핸들링 (`/resources/**`)
-   뷰 리졸버 설정 (`spring.mvc.view.prefix`, `spring.mvc.view.suffix`)
-   로깅 레벨 설정

## 🔍 사용 예제

### 로그인 페이지 접속

```
GET /login/login.do
```

### 로그인 처리 (Spring Security 처리)

```
POST /login/loginCheck.do   (user_id, user_pwd, remember_me, _csrf)
```

### 메인 페이지 접속 (로그인 후)

```
GET /main/main.do
```

### 로그아웃

```
POST /login/logout.do   (_csrf)
```

## ⚠️ 주의사항

-   이 프로젝트는 학습 목적의 예제입니다
-   실제 운영 환경에서 사용 시 추가적인 보안 설정이 필요합니다
-   사용자 정보는 현재 메모리에 하드코딩되어 있으며 비밀번호가 평문(`{noop}`)으로 저장되어 있습니다. 실제 구현 시 데이터베이스 연동 및 `BCryptPasswordEncoder` 등 안전한 암호화가 필요합니다
-   Remember-Me 키(`myapp-remember-me`)는 예제용 고정값입니다. 운영 환경에서는 외부 설정으로 분리하세요
