# wanted-pre-onboarding-backend
## 🔥이경원  

## 🎮 애플리케이션 실행 방법  

- 🐳 docker-compose  
  - 최상위 폴더에서 다음 코드 입력  
  - `docker-compose build`  
  - `docker-compose up`

  
- 🔧 소스코드 실행  
  - SubjectApplication.java 실행  

## ☎️ Endpoint 호출 방법  
### 회원가입 (POST):  
- 엔드포인트: /api/register
- HTTP 메서드: POST
- 요청 본문: JSON 형식의 유저 데이터 (@RequestBody @Valid UserDTO user)
- 유효성 검사: @Valid 어노테이션을 사용하여 유효성 검사 수행  
- 응답: 회원가입 성공 시 "회원가입 완료!" 메시지와 200 OK 응답
  
### 로그인 (GET):
- 엔드포인트: /api/login
- HTTP 메서드: GET
- 요청 본문: JSON 형식의 유저 데이터 (@RequestBody @Valid UserDTO user)
- 유효성 검사: @Valid 어노테이션을 사용하여 유효성 검사 수행
- 응답: 로그인 성공 시 토큰이 담긴 응답 데이터와 200 OK 응답  

### 게시물 생성 (POST):
- 엔드포인트: /api/board/post
- HTTP 메서드: POST
- 요청 본문: JSON 형식의 요청 데이터 (req 매개변수)
- 필요한 인증 정보: 사용자 인증 토큰 (@AuthenticationPrincipal User user)
- 응답: 게시물 생성 성공 시 "게시 완료!" 메시지와 200 OK 응답  

### 게시물 조회 (GET):
- 엔드포인트: /api/boards
- HTTP 메서드: GET
- 요청 파라미터: 페이지 정보 (Pageable)
- 응답: 페이지에 해당하는 게시물 목록  

### 특정 게시물 조회 (GET):
- 엔드포인트: /api/board/{id}
- HTTP 메서드: GET
- 경로 변수: 게시물 ID (@PathVariable("id") Long id)
- 응답: 특정 ID의 게시물 정보  

### 게시물 수정 (POST):
- 엔드포인트: /api/board/{id}/update
- HTTP 메서드: POST
- 경로 변수: 게시물 ID (@PathVariable("id") Long target)
- 요청 본문: JSON 형식의 요청 데이터 (req 매개변수)
- 필요한 인증 정보: 사용자 인증 토큰 (@AuthenticationPrincipal User user)
- 응답: 게시물 수정 성공 시 "게시글 수정 완료!" 메시지와 200 OK 응답  

### 게시물 삭제 (POST):
- 엔드포인트: /api/board/{id}/delete
- HTTP 메서드: POST
- 경로 변수: 게시물 ID (@PathVariable("id") Long target)
- 필요한 인증 정보: 사용자 인증 토큰 (@AuthenticationPrincipal User user)
- 응답: 게시물 삭제 성공 시 "게시글 삭제 완료!" 메시지와 200 OK 응답


## 🗄️ 데이터베이스 테이블 구조  
![image](https://github.com/yeoooo/wanted-pre-onboarding-backend/assets/71688432/ef98374b-03f9-478f-aa91-4a75c94f4832)  

## 🎬 데모영상  
- [링크](https://drive.google.com/file/d/1emmCzePGIhJY9k7-5f_G_67kSBg5wyhp/view?usp=sharing)


https://github.com/yeoooo/wanted-pre-onboarding-backend/assets/71688432/e7232175-7516-4b0c-a726-ef285fa70577



## 💭 구현 방법 및 이유  
- 컨트롤러
  - 클라이언트가 정해지지 않았기 때문에, 별도의 API Response 객체를 만들기 보다는 기존의 ResponseEntity를 사용했습니다.
  - Endpoint의 경우 누구나 이해할만할 URI을 작성하고자 REST 규칙을 따라가고자 했습니다.
  - 예외처리를 수행하기 위해 @ExceptionHandler를 사용했으며, 별도의 Custom Excpetion은 작성하지 않았기 때문에 이를 메시지로 전달하고자 했습니다.
    
- 서비스
  - Controller 와의 결합도를 최대한 낮추고자 노력했습니다. 모든 인자를 Entity가 아닌 DTO로 받고자 의도했습니다.
  - 지연로딩, 세션 없음 이슈를 해결하고자 PostService에서 UserRepository를 사용하여 User의 세션을 유지했습니다.
    
- 인증
  - Spring Security과 auth0 라이브러리를 이용해 jwt 기반 인증을 구현했습니다.
    

## [📄 API 명세](https://documenter.getpostman.com/view/21304389/2s9Xy3sBBP)  

## 💭 배포  
- ### 시스템 구성
  
  <img src = https://github.com/yeoooo/wanted-pre-onboarding-backend/assets/71688432/5a6e8906-939e-4756-821e-dccaef4f66b4 style = "height : 600px; width : 600px;" align = "center"/>
  

- ### 주소  
  ec2-3-35-230-220.ap-northeast-2.compute.amazonaws.com








