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
    <link rel="stylesheet" href="resources/css/admin.css">
    <link rel="stylesheet" href="resources/css/styleMyPage.css">
    
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('.search_select').val('${TYPE}').prop("selected",true);
		
		$(".delmyaccount").click(function(){
            $('.delmyaccountBox').removeClass('closedBox')
         });
		
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/mypage_button.jsp"%>
    <%@include file="/WEB-INF/views/common/header.jsp" %>
    <div class="adminTitle">관리자페이지</div>
    <div class="adminBody">
       <%@include file="/WEB-INF/views/common/adminNav.jsp" %>
       <div class="adminPost_body">
         <div class="contents">
		<div class="boardBox">
			<table style="table-layout: fixed" class="board">
				<tr>
					<th>회원 아이디</th>
					<th>회원 닉네임</th>
					<th>회원 전화번호</th>
					<th style="width: 300px">회원 이메일</th>
					<th>회원 권한</th>
					<th>탈퇴</th>
				</tr>
				<c:forEach var="MEM" items="${LIST}">
						<tr>
							<td>${MEM.mem_id }</td>
							<td>${MEM.mem_nick }</td>
							<td>${MEM.mem_phone }</td>
							<td>${MEM.mem_email }</td>
							<td>
								<form action="updateGrade" method="post">
									<input type="hidden" value="${MEM.mem_code}" name="mem_code">
									<c:if test="${MEM.common_code != 10001 }">
									<select class="grade${MEM.mem_code}" name="grade_type" >
										<option value="10001" hidden="hidden">운영자</option>
										<option value="10002">관리자</option>
										<option value="10003">일반회원</option>
										<option value="10004">여행사</option>
									</select>
									<input type="submit" value="변경">
									</c:if>
									<c:if test="${MEM.common_code eq 10001 }">
									<select class="grade${MEM.mem_code}" name="grade_type" disabled="disabled" >
										<option value="10001" selected>운영자</option>
										<option value="10002">관리자</option>
										<option value="10003">일반회원</option>
										<option value="10004">여행사</option>
									</select>
									</c:if>
								</form>
							</td>
							<td>
								<input class="delmyaccount" type="button" value="탈퇴처리">
							</td>
						</tr>
						<script>
						$(document).ready(function(){
							$('.grade${MEM.mem_code}').val('${MEM.common_code}').prop("selected",true);
							
						});
						</script>
				</c:forEach>
			</table>
		</div>
	<div style="display: block; text-align: center;">
		<c:if test="${PAGE.startPage != 1 }">
			<a href="adminMem?cntPage=${PAGE.startPage - 1 }&search_type=${TYPE}&search_text=${TEXT}">&lt;</a>
		</c:if>
		<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }" var="p">
			<c:choose>
				<c:when test="${p == PAGE.cntPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != PAGE.cntPage }">
					<a href="adminMem?cntPage=${p }&search_type=${TYPE}&search_text=${TEXT}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGE.endPage != PAGE.lastPage}">
			<a href="adminMem?cntPage=${PAGE.endPage+1 }&search_type=${TYPE}&search_text=${TEXT}">&gt;</a>
		</c:if>
	</div>
	
			<div class="search_wrapper">
				<form action="adminMem" method="post">
					<select class="search_select" name="search_type" >
						<option value="search_title">아이디</option>
						<option value="search_nick">닉네임</option>
					</select>
					<input type="text" class="search_input" name="search_text" value="${TEXT}">
					<button type="submit" class="search_btn">검색</button>
				</form>
			</div>
	</div>
         </div>
   </div>
   
   <%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>