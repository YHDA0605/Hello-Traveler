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
<link rel="stylesheet" href="resources/css/stylePost.css">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>

<script>
	$(document).ready(function(){
		$(".nologinWrite").click(function(){
			alert("로그인을 해 주세요");
		})
	})
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="contents">
		<div class="title">${COMMON.common_name}</div>
		<c:if test="${COMMON_CODE < 10003 || COMMON.common_code == 50003}">
			<div class="write">
				<a href="centerWrite?common_code=${COMMON.common_code }"><input class="boradBtn btn" type="button"
					value="글쓰기"></a>
			</div>
		</c:if>
		<div>
			<table class="board">
				<tr>
					<th style="text-align: center; width: 800px;">제목</th>
					<th>글쓴이</th>
					<th>등록일</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:forEach var="center" items="${LIST}">
					<c:if test="${center.re_step == 1}">
					<c:if test="${center.post_state eq '0'.charAt(0)}">
						<tr>
							<td><a
								href="centerContent?common_code=${center.common_code }&POST_CODE=${center.post_code}">${center.post_title}</a></td>
							<td>${center.mem_nick}</td>
							<td>${center.post_date}</td>
							<td>${center.post_hit}</td>
							<td>${center.count}</td>
						</tr>
					</c:if>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div style="display: block; text-align: center;">
		<c:if test="${PAGE.startPage != 1 }">
			<a href="center?cntPage=${PAGE.startPage - 1 }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">&lt;</a>
		</c:if>
		<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }" var="p">
			<c:choose>
				<c:when test="${p == PAGE.cntPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != PAGE.cntPage }">
					<a href="center?cntPage=${p }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGE.endPage != PAGE.lastPage}">
			<a href="center?cntPage=${PAGE.endPage+1 }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">&gt;</a>
		</c:if>
	</div>
	
			<div class="search_wrapper">
				<form action="center" method="post">
					<input type="hidden" name="common_code" value="${COMMON.common_code}">
					<select class="search_select" name="search_type" >
						<option value="search_title">제목</option>
						<option value="search_nick">글쓴이</option>
					</select>
					<input type="text" class="search_input" name="search_text" value="${TEXT}">
					<button type="submit" class="search_btn">검색</button>
				</form>
			</div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>

