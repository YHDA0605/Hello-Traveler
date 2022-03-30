<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/styleRecruit.css">
	<link rel="stylesheet" href="resources/css/style.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	
	<script>
    	$(document).ready(function() {
    		
    		$(".noAction").click(function() {
					alert("로그인해주세요");
			});
		});
    </script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>

    <div class="contents">
		<div class="title">${COMMON.common_name} 여행모집</div>

		<c:set var="session" scope="session" value="${CODE}"></c:set>
		<c:if test="${session == null}">
			<div class="write">
				<input class="btn noAction" type="button" value="글쓰기">
			</div>
		</c:if>
		<c:if test="${session != null}">
			<div class="write">
				<a href="recruitWrite?common_code=${COMMON.common_code}"><input class="btn writeBtn" type="button" value="글쓰기"></a>
			</div>
		</c:if>

		<div class="recruit_Box">
			<c:if test="${not empty recuList }">
				<!-- jstl로 반복문걸어야함 -->
				<c:forEach var="recu" items="${recuList }">
					<div class="recruit_contents"
						OnClick="location.href ='recruitContent?common_code=${recu.common_code}&recu_code=${recu.recu_code }'"
						style="cursor: pointer;">
						<div class="recruit_wrap">
							<div class="recruit_contents_top">
								<div>${recu.membersDTO.mem_nick }</div>
								<c:if test="${recu.recu_common_code eq '31001'}">
									<div class=recruiting>모집중</div>
								</c:if>
								<c:if test="${recu.recu_common_code eq '31002'}">
									<div class="recruitied">모집종료</div>
								</c:if>
							</div>
							<div class="recruit_contents_title">${recu.recu_title }</div>
							<div class="recruit_contents_hit">신청수 : ${recu.recu_apply_n } &middot;조회수 : ${recu.recu_hit }</div>
							<hr>
							<div class="recruit_contents_bottom">
								<span>${recu.commonsDTO.common_name }</span>
								<span>${recu.recu_max_n } 명</span>
								<fmt:parseDate value="${recu.recu_deadline}" var="deadline" pattern="yyyyMMdd"/>
								<fmt:parseDate value="${recu.recu_start_date}" var="start_date" pattern="yyyyMMdd"/>
								<fmt:parseDate value="${recu.recu_end_date}" var="end_date" pattern="yyyyMMdd"/>
								<span>기한 : <fmt:formatDate value="${deadline}" pattern="M월d일"/></span> 
								<span>
									<fmt:formatDate value="${start_date}" pattern="M월d일"/>~
									<fmt:formatDate value="${end_date}" pattern="M월d일"/>
								</span>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>

		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a href="recruit?cntPage=${paging.startPage - 1 }">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="p">
				<c:choose>
					<c:when test="${p == paging.cntPage }">
						<b>${p }</b>
					</c:when>
					<c:when test="${p != paging.cntPage }">
						<a href="recruit?cntPage=${p }">${p }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="recruit?cntPage=${paging.endPage+1 }">&gt;</a>
			</c:if>
		</div>
		
		<div class="search_wrapper">
			<form action="recruit" method="post">
				<input type="hidden" name="common_code" value="${COMMON.common_code}"> 
				<select class="search_select" name="search_type">
					<option value="search_title">제목</option>
					<option value="search_nick">글쓴이</option>
				</select> 
				<input type="text" class="search_input" name="search_text" value="${TEXT}">
				<button type="submit" class="search_btn btn">검색</button>
			</form>
		</div>
	</div>
        
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
