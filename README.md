# 📝 TODO List 백엔드 프로젝트

## 📌 프로젝트 개요
- **목적**: 사용자별 TODO 리스트 관리 기능 제공
- **주요 기능**:
  - TODO 등록
  - TODO 목록 조회 (페이징)
  - TODO 상태 변경
  - TODO 삭제
- **DB**: Docker 기반 MySQL 8.4.4 사용
- **백엔드**: Spring Boot 3.4.4 + JPA

---

## ⚙️ 실행 환경
- Java 21 (Temurin 21)
- Spring Boot 3.4.4
- MySQL 8.4.4 (Docker 사용)
- Gradle 빌드 시스템

---

## 🚀 서버 실행 방법

1. **Docker로 MySQL 실행**
   ```bash
   docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 -d mysql:8
   ```
   또는 이미 실행된 경우:
   ```bash
   docker start mysql-container
   ```

2. **MySQL 접속 및 DB 생성**
   ```bash
   docker exec -it mysql-container mysql -u root -p
   ```
   ```sql
   CREATE DATABASE todolist_db;
   ```

3. **Spring Boot 서버 실행**
   ```bash
   ./gradlew bootRun
   ```

4. **서버 접속 주소**
   ```
   http://localhost:8080
   ```

---

## 📬 API 요청 예시 (Postman)

### 1. TODO 등록

- **URL**: `POST http://localhost:8080/todo`
- **Body (JSON)**:
  ```json
  {
    "title": "오늘 할 일",
    "subTasks": [
      { "content": "세부1" },
      { "content": "세부2" }
    ]
  }
  ```
- **성공 응답 예시**:
  ```json
  {
    "id": 1,
    "title": "오늘 할 일",
    "status": "진행 전"
  }
  ```

📸 **Postman 요청 및 응답 캡처 이미지 삽입**

---

## 🗂 프로젝트 구조

```
com.todolist
 ├── controller
 │    └── TodoController.java
 ├── dto
 │    ├── TodoCreateRequest.java
 │    ├── SubTaskRequest.java
 │    └── TodoStatusRequest.java
 ├── entity
 │    ├── Todo.java
 │    ├── SubTask.java
 │    ├── TodoStatus.java
 │    └── SubTaskStatus.java
 ├── repository
 │    └── TodoRepository.java
 ├── service
 │    └── TodoService.java
 └── TodolistApplication.java
```

---

## 🧪 테스트 코드

- `src/test/java/com/todolist/TodolistApplicationTests.java` 파일에 테스트 코드 작성
- 기능 테스트 항목:
  - TODO 생성
  - TODO 목록 조회
  - TODO 상태 변경
  - TODO 삭제
- 모든 테스트 `BUILD SUCCESSFUL` 확인

---

## ✅ 수행 증거 캡처 정리

- Docker MySQL 컨테이너 정상 실행 (`docker ps`)
- MySQL 접속 및 `todolist_db` 생성 확인 (`SHOW DATABASES;`)
- Spring Boot 서버 정상 부팅 (`Tomcat started on port 8080`)
- Postman API 요청 성공 (`201 Created` 응답)

---

## ✨ 비고
- 로그인 기능은 선택사항이므로 제외하고 필수 TODO 기능만 구현
- 예외처리 및 유효성 검증은 Controller, Service 단에서 처리

---
