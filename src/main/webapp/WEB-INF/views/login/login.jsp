<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleLogin.css">
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container login">
      <div class="login_box">
        <div class="login_Script">
        	<div>방문해주셔서 감사합니다</div>
        	<img src="resources/images/login.JPG">
        	<div>함께 공유하는 Hello, Traveler!</div>
        </div>

        <!--   //!!!!!!!!!!!!!!!!2018 추가 ///////////////////////////////////////////////////////////////-->
        <!--client_id:REST API 키   -->  
        <div class="apiLoginBox">
			<div>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=9ab18d302f2ebedd3e83f4861b8f41bf&response_type=code&redirect_uri=http://localhost:8085/helloTraveler/kakaologin">
					<img alt="카카오로그인" src="resources/images/kakao_login.png">
				</a>
			</div>
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login" style="text-align:center">
				<a href="${url}">
					<img width="200"  alt="네이버로그인" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
				</a>
			</div>
		</div>
        <br />
        <a href="loginForm"><button class="login_btn_2">아이디로 로그인</button></a>
        <br />
        아직 계정이 없으시다면??
        <a href="regist"><button class="login_btn_3">회원가입</button></a>
      </div>
    </div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>