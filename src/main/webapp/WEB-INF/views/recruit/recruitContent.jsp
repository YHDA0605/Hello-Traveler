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
			
    		$('.applyBtn').click(function() {
				var url = "applyMessageForm?mem_code=${recu.mem_code }&common_code=${recu.common_code}&recu_code=${recu.recu_code }";
				var popOption = "width = 450px, height=400px, top=200px, left=300px, scrollbars=yes";
				
				window.open(url, "message", popOption);
			});
    		
    		$(".noAction").click(function() {
				alert("로그인해주세요");
			});
    		
		});
    </script>
</head>

<body>
    <%@include file="/WEB-INF/views/common/header.jsp" %>

    <div class="contents">
        
        <div class="articleBtn">
        
        <c:if test="${session eq recu.mem_code }">
            <a href="recruitEdit?common_code=${recu.common_code}&recu_code=${recu.recu_code }" >
            	<button type="button" class="btn">수정</button>
            </a>
            <a href="recruitDelete?common_code=${recu.common_code}&recu_code=${recu.recu_code }">
            	<button type="button" class="btn">삭제</button>
            </a>
        </c:if>
            <a href="recruit?common_code=${recu.common_code}"><button type="button" class="btn">목록</button></a>
        
        </div>
        
        <div class="articleInfo">
            <div style="text-align: left;">${recu.membersDTO.mem_nick }</div>
            <div style="text-align: center;"><fmt:formatDate value="${recu.recu_reg_date }" pattern="yyyy-MM-dd HH:mm"/></div>
            <div style="text-align: right;">신청수 : ${recu.recu_apply_n} &middot; 조회수 : ${recu.recu_hit }</div>
        </div>
        
        <div class="contentBox">
            
            <div style="word-break:break-all;" class="recru_contentTitle">${recu.recu_title }</div>
            
            <div class="recru_contentInfo">
            	
            	<fmt:parseDate value="${recu.recu_deadline}"   var="deadline"   pattern="yyyyMMdd" />
				<fmt:parseDate value="${recu.recu_start_date}" var="start_date" pattern="yyyyMMdd" />
				<fmt:parseDate value="${recu.recu_end_date}"   var="end_date"   pattern="yyyyMMdd" />
            	
            	<div>
            		<span>국가/지역 : </span>${recu.commonsDTO.common_name } &middot; 
            		<span>희망인원 : </span>${recu.recu_max_n }명 &middot; 
            		<span>기한 : </span><fmt:formatDate value="${deadline}" pattern="M월 d일" />
            	</div>
            	<div>
					<span>일정 : </span>
					<fmt:formatDate value="${start_date}" pattern="yy년 M월 d일" />~
					<fmt:formatDate value="${end_date}" pattern="yy년 M월 d일" />
				</div>
            </div>
            
            <div class="recru_content">
                ${recu.recu_cont }
            </div>
        </div>
		
        <div class="articleBtn">
        	<c:if test="${session eq recu.mem_code }">
            	<a href="recruitEnd?common_code=${recu.common_code}&recu_code=${recu.recu_code }" >
            		<button type="button" class="btn">모집종료</button>
            	</a>
        	</c:if>
        	<c:if test="${session == null}">
            	<input class="noAction btn" type="button" value="신청">
            </c:if>
            <c:if test="${session != null && session != recu.mem_code}">
            	<input class="applyBtn btn" type="button" value="신청">
            </c:if>
            <a href="recruit?common_code=${recu.common_code}"><button type="button" class="btn">목록</button></a>
        </div>
    </div>
        
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
    
    <div id="applyContainer">
    	
    </div>
</body>
</html>
