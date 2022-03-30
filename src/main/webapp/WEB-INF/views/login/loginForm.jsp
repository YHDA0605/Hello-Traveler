<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/styleLogin.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <c:set var="fail" scope="request" value="${FAIL}"></c:set>
    <c:if test="${FAIL != null}">
        <script>
    		(function(){
    			alert("아이디나 비밀번호가 틀립니다.");
    		}());
    	</script>
    </c:if>
	<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
    <%@include file="/WEB-INF/views/common/header.jsp" %>

    <div class="container login">
      <div class="login_area">
      <form action="loginAction" method="post">
        <h1 class="login_title">로그인</h1>

        <input class="login_id login_info ime-disabled" name="ID" type="text" placeholder="Username" />
        <br />
        <input class="login_pw login_info ime-disabled" name="PW" type="password" placeholder="Password" />

        <div>
          <input type="submit" class="login_btn_2" value="로그인">
        </div>
        </form>
        <div class="login_btn_box">
        	<span>
        		<button class="login_btn_4 findId">아이디 찾기</button>|
        		<button class="login_btn_4 findPw">비밀번호 찾기</button>
        	</span>
        	<span>
        		<a href="regist"><button class="login_btn_5">회원가입</button></a>
        	</span>
        </div>
      </div>
	</div>
	
	<!-- 아이디 / 비밀번호 찾기 박스 -->
	<div class="findBox findInfoBox closedBox">
      	<div class="findInfoForm findForm">
      		<table>
      			<tr><td height="20px"><button class="btn closeBtn close" type="button">&#x2715;</button></td></tr>
      			<tr><td class="findIdFormTi"></td></tr>
      			<tr class="find_process">
      				<td>
      					<input class="email" type="email" name="email" placeholder="등록한 이메일을 입력해주세요">
      					<button type="button" class="btn sendBtn">인증요청</button><br>
      					<input type="text" class="emailChk" name="certifyValue" placeholder="인증번호를 입력해주세요">
      				</td>
      			</tr>
      			<tr class="showUserId">
      			</tr>
      			<tr class="changeUserPasswd">
      			</tr>
      		</table>
      		<button class="btn findBtn">확인</button>
		</div>
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>