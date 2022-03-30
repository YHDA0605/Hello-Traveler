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
		$(".close").click(function(){
			$(".nickName").hide();			
		});
		
		$(".nologinWrite").click(function(){
			alert("로그인을 해 주세요");
		})
		$('.search_select').val('${TYPE}').prop("selected",true);
		
	});
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="contents">
		<div class="title">${COMMON.common_name} 게시판</div>
		<c:if test="${session != null}">
			<div class="write">
				<a href="postWrite?common_code=${COMMON.common_code }"><input class="boradBtn btn" type="button"
					value="글쓰기"></a>
			</div>
		</c:if>
		<c:if test="${session == null}">
			<div class="write">
				<input class="boradBtn nologinWrite btn" type="button" value="글쓰기">
			</div>
		</c:if>
		<div class="boardBox">
			<table style="table-layout: fixed" class="board">
				<tr>
					<th style="width:100px;">NO</th>
					<th style="width:500px;">제목</th>
					<th>글쓴이</th>
					<th>등록일</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:forEach var="post" items="${LIST}">
					<c:if test="${post.re_step == 1}">
					<c:if test="${post.post_state eq '0'.charAt(0)}">
						<tr>
							<td style="word-break:break-all;">${post.post_code}</td>
							<td style="word-break:break-all;">
								<a href="postContent?common_code=${post.common_code }&POST_CODE=${post.post_code}">${post.post_title}</a>
							</td>
							<td>
								<div class="nickNameBox">
									<span class="post_nick${post.post_code }">${post.mem_nick}</span>
									<div class="nickName nickNameLayer${post.post_code }">
										<ul>
											<li class="search"><a href="post?common_code=${COMMON.common_code}&search_type=search_nick&
											search_text=${post.mem_nick}">이름으로 검색</a></li>
											<li class="inventory${post.post_code}">쪽지 보내기</li>
											<li class="close">닫기</li>
										</ul>
								</div>
								</div>
							</td>
							<td>${post.post_date}</td>
							<td>${post.post_hit}</td>
							<td>${post.count}</td>
						</tr>
					</c:if>
					</c:if>
					<script>
						
						
						$(document).ready(function(){
							$(".nickNameLayer${post.post_code}").hide();
							
							$(".post_nick${post.post_code }").click(function(){
								$(".nickName").hide();
								 var coment = $('.nickNameLayer${post.post_code}').css('display');
									if(coment == "none"){
										$(".nickNameLayer${post.post_code}").show();
									}else{
										$(".nickNameLayer${post.post_code}").hide();
									}
							});
							
							$('body').click(function(e){
								 if($(e.target).parents(".nickNameBox").length < 1){
									 $(".nickName").hide();
								 	}
								});
							
							$('.inventory${post.post_code}').click(function() {
								var url = "applyMessageForm?mem_code=${post.mem_code }&common_code=${post.common_code}&code=${post.post_code }";
								var popOption = "width = 450px, height=400px, top=200px, left=300px, scrollbars=yes";
								
								window.open(url, "message", popOption);
							});
				    		
				    		$(".noAction").click(function() {
								alert("로그인해주세요");
							});
				    		
						});
					</script>
				</c:forEach>
			</table>
		</div>
	<div style="display: block; text-align: center;">
		<c:if test="${PAGE.startPage != 1 }">
			<a href="post?cntPage=${PAGE.startPage - 1 }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">&lt;</a>
		</c:if>
		<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }" var="p">
			<c:choose>
				<c:when test="${p == PAGE.cntPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != PAGE.cntPage }">
					<a href="post?cntPage=${p }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGE.endPage != PAGE.lastPage}">
			<a href="post?cntPage=${PAGE.endPage+1 }&common_code=${COMMON.common_code}&search_type=${TYPE}&search_text=${TEXT}">&gt;</a>
		</c:if>
	</div>
	
			<div class="search_wrapper">
				<form action="post" method="post">
					<input type="hidden" name="common_code" value="${COMMON.common_code}">
					<select class="search_select" name="search_type" >
						<option value="search_title">제목</option>
						<option value="search_nick">글쓴이</option>
					</select>
					<input type="text" class="search_input" name="search_text" value="${TEXT}">
					<button type="submit" class="search_btn btn">검색</button>
				</form>
			</div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>

