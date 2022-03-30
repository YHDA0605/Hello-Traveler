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
<link rel="stylesheet" href="resources/css/styleRegist.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<img class="exclamation_img" src="resources/images/exclamation.png">
		<div class="title">회원가입이 완료되었습니다.</div>
		<div class="subtitle">
			Hello,Traveler에 가입을 완료하였습니다.<br> 이제 게시판 글쓰기 기능, 모집하기 기능 및 참가가 가능합니다.
		</div>
		<a href="/helloTraveler"><input class="registChak_Btn" type="button"
			value="확인"></a>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>