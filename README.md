<div align="center">
<h2>[2024] 직원 출결 시스템 📝</h2>
  직원들의 출결을 관리하며 승진 여부를 결정하는 시스템입니다!<br> dotCode 회사는 직원의 근태를 가장 중요히 여기는 회사이기 때문에 근태가 좋지 않다면 정리해고 대상에 들어갈 수 있습니다!<br> 이 점을 주의해주세요!<br><br>
</div>

## Project Read me
### ** AMS : Attendance Management System **
### ** 근태 관리 시스템 동작 원리 **
1. 출결 현황 체크 후 인사고과시 출결점수 반영 후 승진시킬 수 있다.
2. 관리자와 직원은 메뉴가 다르게 출력된다.
3. 로그인하면 로그인한 사원의 근태정보 중 총 근무일이 최신화된다.
4. 출근했을 때, 출근시간(9:00)이 지나면 지각처리되면서 근태점수가 1 차감,<br>
   10:00시가 지나면 결근처리되며 근태점수가 5 차감된다.
6. 퇴근할 때, 18시가 지나지 않으면 퇴근할 수 없다.
7. 로그아웃할 때와 프로그램을 종료할 때, 자동으로 근무상태가 퇴근상태가 된다.
8. 모든 데이터는 프로그램이 종료될 때 까지 최신화 된 내용으로 확인할 수 있다.

### ** 관리자가 수행하는 일 **
1. 사원 개인정보 조회 및 등록/삭제 가능
2. 사원을 등록하면 근태기록까지 자동으로 생성
3. 사원을 해고하면 개인정보와 근태기록까지 자동으로 제거
4. 사원의 근태현황(총 근무일, 정시출근, 지각, 결근, 근태점수) 조회 및 수정 > 사원 생성 시 자동으로 생성되기 때문에<br>
따로 등록할 필요가없다.
5. 사원의 근태현황 정보 초기화가 가능하고, 초기화 후 당일 날짜를 데이터로 저장
6. 사원의 전체 부재 신청 현황을 조회 할 수 있고, 사유를 확인하여 부재 신청을 허가/불허 할 수 있다.<br> > 불허하게되면, 허가되지않은 사유도 저장한다.

### ** 직원이 수행하는 일 **
1. 출근
2. 퇴근
3. 내 개인정보 & 근태정보 열람 가능
4. 부재시 휴가/ 출장/ 외출 등 사유와 함께 신청하면 허가 대기 상태로 신청이 완료된다.

## 프로젝트 보완점
1. 점수에 따라 자동적으로 누적 되는 시스템으로 생성되었다면 더욱 활용성있는 프로그램이 되었을 것 같다.
2. 일반사원이 부재신청을 하고 본인의 부재신청 현황을 확인 할 수 있었으면 조금 더 편리했을 것 같다.


## Project Result
(프로젝트 결과물)

## Project Schedule
|날짜|스케쥴명|내용|
|------|---|---|
|24.02.16 ~ 19|첫번째 프로젝트 회의|출결 시스템 DB 회의 / 출결 시스템 DB 회의 / Github Organization 생성 및 Project Read me 작성 / Git branch를 활용한 Git-flow 형식으로 최대한 진행|
|24.02.20 ~ 21|두번째 프로젝트 회의|기초 데이터 생성 / miro를 활용한 usecase diagram 작성 / da#을 활용한 데이터 모델링 작성 / MySQL 데이터 베이스 쿼리 작성|
|24.02.22 ~ 23|세번째 프로젝트 회의|다이아그램 생성 / 데이터 모델링 수정 -> 데이터 베이스 쿼리 수정 / 클래스 다이어그램 작성|
|24.02.24 ~ 26|네번째 프로젝트 회의|구현 / 데이터 베이스를 기반으로 한 자바 구현|
|24.02.27 ~ 28|다섯번째 프로젝트 회의|최종 기능 테스트 및 오류 수정|

## Project Tech Stack 💡
### ✔️Back-end
<div align=left>
<img src="https://img.shields.io/badge/IntelliJ IDEA-6DB33F?style=for-the-badge&logo=IntelliJ IDEA&logoColor=green">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=green">
<br>
  
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

## Project Management Method
** 1-1. 큰 틀 잡기 **
- [전체 보기](https://docs.google.com/spreadsheets/d/1YogkHXzy_kJyizxFvUkbJNSwWlOVim1U43TBdAWUXFI/edit?usp=sharing)
  
![스프린트 추적](https://github.com/dotCodeTeam/dotCode/assets/134928447/4ab9f1c0-c943-403f-88fb-041f03417b8a)

** 1-2. Use Case Diagram **
![ams_usecase](https://github.com/dotCodeTeam/AMS/assets/90615404/bcc9eab0-091f-4a8e-887d-b9fc19d7ad06)

** 1-3. DataBase Modeling
- [단계별 보기](https://docs.google.com/spreadsheets/d/1C87q15x0XjstHbo-en2ivPs50yKtqrOh5AFT1MqlvHg/edit?usp=sharing)
 
#### <논리모델>
![ams_ver6_4](https://github.com/dotCodeTeam/AMS/assets/90615404/0a58e94a-add4-4d9c-bb8b-1375d17004fd)

#### <물리모델>
![ams_ver6_4_물리](https://github.com/dotCodeTeam/AMS/assets/90615404/8e4dce9a-4cda-469a-83d3-85ad5b6af414)

** 1-4. Class Diagram **
![KakaoTalk_20240228_190315277](https://github.com/dotCodeTeam/.github/assets/134928447/9f3f6eb6-d89a-469b-8c46-f16d8e35aabf)

** 2. 기타 내용 **

## Impression (느낀점)
- 조은성 :
- 윤수빈 :
- 임찬울 :
- 고영상 :
- 박진현 :

## Documents 
