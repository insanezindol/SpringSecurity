# Spring Security 예제 프로젝트

Spring Security를 활용한 웹 애플리케이션 인증/인가 시스템 구현 예제입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Framework와 Spring Security를 이용하여 사용자 로그인, 권한 관리, 세션 관리 기능을 구현한 웹 애플리케이션입니다.

## 🛠️ 기술 스택

-   **Java**: 1.7
-   **Spring Framework**: 4.2.3.RELEASE
-   **Spring Security**: 4.2.3.RELEASE
-   **Maven**: 프로젝트 관리 도구
-   **JSP**: 뷰 템플릿
-   **Log4j**: 로깅

## 📁 프로젝트 구조

```
SpringSecurity/
├── src/main/
│   ├── java/com/test/myapp/
│   │   ├── LoginController.java    # 로그인 관련 컨트롤러
│   │   └── MainController.java     # 메인 페이지 컨트롤러
│   ├── resources/
│   │   └── log4j.xml              # 로깅 설정
│   └── webapp/
│       ├── resources/js/          # JavaScript 파일
│       └── WEB-INF/
│           ├── spring/
│           │   ├── security-context.xml    # Spring Security 설정
│           │   ├── root-context.xml        # 루트 컨텍스트 설정
│           │   └── appServlet/
│           │       └── servlet-context.xml # 서블릿 컨텍스트 설정
│           ├── views/                      # JSP 뷰 파일
│           │   ├── login.jsp              # 로그인 페이지
│           │   ├── main.jsp               # 메인 페이지
│           │   └── showUser.jsp           # 사용자 정보 페이지
│           └── web.xml                    # 웹 애플리케이션 설정
└── pom.xml                               # Maven 설정
```

## 🔐 주요 기능

### 1. 사용자 인증 (Authentication)

-   폼 기반 로그인
-   사용자 ID/비밀번호 검증
-   로그인 실패 처리

### 2. 권한 관리 (Authorization)

-   **ROLE_DEV**: 개발자 권한
-   **ROLE_ADMIN**: 관리자 권한
-   **ROLE_USER**: 일반 사용자 권한

### 3. URL 접근 제어

-   `/resources/**`: 전체 허용 (정적 리소스)
-   `/login/login.do`: 미로그인 사용자만 접근
-   `/dev/**`: 개발자 권한 필요
-   `/admin/**`: 관리자 또는 개발자 권한 필요
-   기타 경로: 로그인된 사용자만 접근

### 4. 세션 관리

-   로그인 성공 시 세션 생성
-   로그아웃 처리
-   세션 만료 처리

## 🚀 실행 방법

### 1. 사전 요구사항

-   JDK 1.7 이상
-   Maven 3.x
-   Apache Tomcat 또는 다른 서블릿 컨테이너

### 2. 프로젝트 빌드

```bash
mvn clean compile
mvn package
```

### 3. 웹 애플리케이션 배포

생성된 WAR 파일을 Tomcat의 webapps 디렉토리에 배포하거나, IDE에서 직접 실행합니다.

### 4. 접속

```
http://localhost:8080/myapp
```

## 📝 설정 파일 설명

### security-context.xml

Spring Security의 핵심 설정 파일로, 다음 내용을 포함합니다:

-   URL 접근 권한 설정
-   로그인/로그아웃 처리 설정
-   사용자 인증 방식 설정

### servlet-context.xml

Spring MVC 관련 설정을 포함합니다:

-   컴포넌트 스캔 설정
-   뷰 리졸버 설정
-   리소스 핸들링

## 🔍 사용 예제

### 로그인 페이지 접속

```
GET /login/login.do
```

### 메인 페이지 접속 (로그인 후)

```
GET /main/main.do
```

### 로그아웃

```
POST /logout
```

## ⚠️ 주의사항

-   이 프로젝트는 학습 목적의 예제입니다
-   실제 운영 환경에서 사용 시 추가적인 보안 설정이 필요합니다
-   사용자 정보는 현재 메모리에 하드코딩되어 있으며, 실제 구현 시 데이터베이스 연동이 필요합니다
