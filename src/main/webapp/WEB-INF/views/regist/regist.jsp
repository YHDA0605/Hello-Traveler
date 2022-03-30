<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<div class="container regist">
		<h1 class="reg_title">회원가입</h1>
		<br>
		<div id="select">
			<a href="registNormal"><button>일반회원</button></a>
			<a href="registCompany"><button>여행사회원</button></a>
		</div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>