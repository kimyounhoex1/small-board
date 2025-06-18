# 🐯 Jungle Board - 게시판 프로젝트

> Spring Boot + React 기반의 간단한 커뮤니티 게시판  
> JWT 인증, 회원가입/로그인, 게시글 CRUD, 검색 기능 포함

---

## 🛠️ 사용 기술

### 🔹 Backend
- Java 17
- Spring Boot 3.x
- JdbcTemplate
- JWT (JJWT)
- MySQL

### 🔹 Frontend
- React 18
- Axios
- Tailwind CSS
- React Router

---

## 📁 프로젝트 구조 (Backend)

com.jungle.board
├── config # CORS, JWT 설정
├── controller # REST API 컨트롤러
├── dao # DB 접근 (JdbcTemplate)
├── domain # Entity 클래스 (Board, Member)
├── dto # (필요 시) 요청/응답 DTO
├── service # 비즈니스 로직
└── BoardApplication # main class

yaml
복사
편집

---

## ✅ 주요 기능

### 🔐 인증 / 사용자
- 회원가입
- 로그인 (JWT 토큰 발급: access + refresh)
- 닉네임 중복 검사

### 📋 게시판 기능
- 게시글 전체 조회
- 게시글 작성 / 수정 / 삭제
- 게시글 단건 조회
- 키워드 검색
- 작성자(memberId)별 조회
- (예정) 페이징 조회

---

## 📡 API 목록 (일부)

### 🔹 회원 관련

| 메소드 | URL | 설명 |
|--------|-----|------|
| POST   | `/api/member/join`   | 회원가입 |
| POST   | `/api/member/login`  | 로그인 (JWT 발급) |
| GET    | `/api/member/{id}`   | 단일 회원 조회 |

---

### 🔹 게시글 관련

| 메소드 | URL | 설명 |
|--------|-----|------|
| GET    | `/api/board`                      | 전체 게시글 조회 |
| POST   | `/api/board/write`                | 게시글 작성 |
| GET    | `/api/board/boardid/{id}`         | 게시글 단건 조회 |
| GET    | `/api/board/search?keyword=...`   | 게시글 검색 |
| PUT    | `/api/board/update/{id}`          | 게시글 수정 |
| DELETE | `/api/board/delete/{id}`          | 게시글 삭제 |

---

## 🌐 실행 방법

### 🔹 Backend (Spring Boot)
```bash
./gradlew bootRun
🔹 Frontend (React)
bash
복사
편집
cd frontend
npm install
npm run start
프론트는 http://localhost:3000
백엔드는 http://localhost:8080에서 동작

💡 향후 개선 계획
 JWT refresh 토큰 재발급 로직 추가

 게시글 페이징 처리

 댓글 기능

 로그인 상태 전역 관리 (Context API)

📌 참고
프론트와 백엔드는 CORS 설정을 통해 분리된 포트에서 통신합니다.

JWT는 로컬 스토리지에 저장되며, Axios 인터셉터를 통해 자동 첨부될 수 있도록 구성 예정입니다.