
 ## :pushpin: 소개
>+ <b>이름</b> : 조연하
>+ <b>연락처</b> : 010.5138.1950
>+ <b>email</b> : <yeonhajo65@gmail.com>


 ## :pushpin: 프로젝트 
> ### <b>Hello,Traveler<b> (4인 팀 프로젝트)<br>
>개발기간 : 2021.12.01 ~ 2022.02.27 <br>
><br>
> 여행을 좋아하는 사람들을 위한 여행커뮤니티<br>
> 유저들과 여행에 관한 여러 정보와 소통을 주고 받고, 여행 친구를 모집할 수 있으며, 여행사 기업을 통해 여행 상품을 판매 합니다.<br>
> <br>
>:punch: <b>주요 사용기술</b> :punch:<br>
><br>
><img src="https://img.shields.io/badge/java8-007396?style=for-the-badge&logo=java&logoColor=white"></a>
><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"></a>
><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"></a>
><img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"></a>
><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"></a>
><img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=JSON&logoColor=white"></a>
><img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white"></a>
><img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white"></a>
><br>
><details>
><summary>ERD 보기</summary>
><img width="100%" src="https://user-images.githubusercontent.com/86191913/161224756-37aaf2e2-b97f-4be4-b4c9-75d3b97a9b32.jpeg"/>
>
></details>

## :pushpin: 맡은 파트
>
>+ 회원가입 폼 입력 제한조건 :heavy_check_mark: [코드확인](https://github.com/YHDA0605/Portfolio/blob/604d63884ef6e3312068a31fe026e144644c1a9c/src/main/webapp/resources/js/regist.js#L83)
>
>+ 회원탈퇴 :heavy_check_mark: [코드확인](https://github.com/YHDA0605/Portfolio/blob/9dc1823fb54c116b299e9556d76acb2863367934/src/main/java/com/teamHT/helloTraveler/HomeController.java#L256)
><details>
> &nbsp;  &nbsp; &nbsp;<summary>회원가입 코드 설명</summary>
>일반회원 회원가입 ui를 HTML 과 Css를 통한 구현과 자바스크립트,제이쿼리로 회원가입 폼 입력시 입력제한을 걸어 회원 테이블내 각 컬럼의 데이터의 크기와 조건에 맞게 입력받을 수 있도록 하였습니다. 
>그리고 회원 테이블 속성의 제약조건을 만족시키기 위해 Json 으로 클라이언트로부터 입력받은 값을 중복값 확인을 위해 만들어진 controller url과 연결하고, 비즈니스 로직을 통해 DB 와 연결하여 중복값을 확인 후, 결과 값을 가져와 중복인지를 확인 합니다.
></details>
><details>
> &nbsp;  &nbsp; &nbsp;<summary>회원탈퇴 코드 설명</summary>
>로그인한 회원의 마이페이지 네비게이션 메뉴에 회원탈퇴 버튼을 활성화 시켜 회원탈퇴 의사를 묻는 알림창을 띄운 뒤, 회원탈퇴 버튼을 클릭하면 mapping된 controller의 회원삭제를 실행시켜 회원삭제 서비스에서 DB와 연결하여 회원상태 속성을 탈퇴로 바꾸어 저장하고 돌아온 후 로그아웃 처리와 함께 탈퇴완료 알림 메시지를 띄우도록 하였습니다. 
></details>
><details>
> &nbsp;  &nbsp; &nbsp;<summary>:dvd:로그인,탈퇴 시연영상 보기</summary>
><img width="100%" src="https://user-images.githubusercontent.com/86191913/161915537-5e2ad99b-9730-41ee-ba5a-d2a72a1eb3a6.gif"/>
>
></details>
>
>+ Kakao 로그인 API  :heavy_check_mark: [코드확인](https://github.com/YHDA0605/Portfolio/blob/68cb76ff3250b539c1f522f3594d4ad9b99d9fb8/src/main/java/com/teamHT/helloTraveler/KakaoController.java#L1)
>+ 로그인 UI :heavy_check_mark: [코드확인](https://github.com/YeonHaJo/Portfolio/blob/b049f2dcdc5af9dfd937d24561edfedf8c5720af/src/main/webapp/WEB-INF/views/regist/registNormal.jsp#L1)
><details>
>&nbsp;  &nbsp; &nbsp;<summary>SNS 로그인 및 회원등록 코드 설명</summary>
>카카오톡 API를 사용하여 사용자의 정보를 받아와 Service로직 에서 등록여부를 확인하고, 등록이 확인되면 로그인을 성공시키고, 등록되지 않은 회원일 경우 DB에 회원등록을 위해 API로 받아 온 정보를 제외한 NOTNULL 로 제약조건이 걸린 속성에 들어갈 데이터를 입력 받기 위한 회원가입 폼을 따로 구현하여 SNS회원용 로그인 및 회원등록 로직을 만들었습니다. 
>
></details>
><details>
>&nbsp;  &nbsp; &nbsp;<summary>:dvd:카카오 로그인 시연영상 보기</summary>
><img width="100%" src="https://user-images.githubusercontent.com/86191913/161917101-fb464bfc-42ea-471e-a1df-bc979ac949a3.gif"/>
>
></details>
>
>+ 중복 코드 모듈화 : 마이페이지에 있는 네이게이션 바 의 코드가 각 페이지 마다 중복으로 있어야 하는 걸 확인 후, 독립된 하나의<br>
> jsp파일로 만들어 필요한 페이지에 <%@include%> 디렉티브를 통해 코드중복을 최소화 하였습니다.

## :pushpin: 기술적으로 어려웠던 점 & 해결방법
>+ 어려웠던 점 : sns으로 로그인을 하면 api 에서 자동적으로 고유한 회원번호10자리(id)가 주어져 이 id로 로그인을 하는 로직을 짜야하는데  기존 members 테이블에 있는 mem_id컬럼이 UNN 로 제약조건을 지정 해뒀기 때문에 무조건적으로 mem_id 가 들어가지 않으면 안되는 상황에 많은 고민을 해야했다.
><br>
>
>+ 해결방법 : sns 가입을 할때 회원 등록 service 에서 mem_id 컬럼에 값을 넣기 위해 임의로 "sns" 문자열을 넣고, 총 회원수를 알아내는 쿼리로 db에서 회원수를 카운트해 회원등록이 추가 될때마다 숫자가 하나씩 올라가는 방식으로 로직을 짜 "sns" 문자열과 합치므로서 유니크와 NOT NULL의 조건을 모두 충족시키는 sns가입 회원을 위한 mem_id 값을 만들었다.:heavy_check_mark: [코드확인](https://github.com/YeonHaJo/Portfolio/blob/eaf132348b76a228b2e30e9a81c704cf130c22f1/src/main/java/com/teamHT/helloTraveler/Svc/MembersServiceImpl.java#L229)
><br>
## :pushpin: 느낀점
>+ 프로젝트 계획단계에서 팀원들과 머리를 맞대고 치밀하게 짰다고 생각했으나 막상 진행을 해보니 중간에 컬럼이 추가가 되기도 하고, 컬럼의 데이터타입이 바뀌기도 했으며, 심지어 테이블이 추가가 되기도 했었습니다. 생각지 못한 부분에서 erd 수정을 여러번 하게 되면서 계획단계가 얼마나 중요한지에 대해 절실히 깨닫게 되었습니다.
>+ 수업을 진행하면서 교수님이 강조한 부분중에 하나가 '코드중복을 줄여라' 였습니다.실제로 파일이 몇십개가 되는 프로젝트를 해보니 몇십줄의 코드가 여러파일에 중복이 되는걸 보고 이것을 모듈화 함으로써 한줄의 코드로 중복을 줄이니 가독성이 높아지는걸 경험하게 되어 코드중복 제거의 필요성에 대해 더 와닿게 되었습니다.
>+ 아직 어리둥절한 상태에서 프로젝트를 시작하니 많이 막막하고 조원들에게 짐이 되지 않을까 걱정도 많았습니다. 하지만 조원들의 세심한 도움 덕분에 작은 것 부터 차근차근 진행 해보면서 시간이 지날수록 처음보다 프로젝트를 대하는 모습이 조금 더 능숙해지는 제 모습을 볼 수 있었으며, 이번 프로젝트를 통해 팀워크의 중요성과 함께 팀원들에게 아주 큰 고마움을 느꼈습니다.

