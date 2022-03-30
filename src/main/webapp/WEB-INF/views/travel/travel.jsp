<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/styleProduct.css" />
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
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="contents">
		<div class="product_main_title">${COMMON.common_name} 여행상품 목록</div>
		<div class="product_main">
			<div class="product_main_contents">
				<c:if test="${not empty travList }">
					<div class="product_content">
						<c:forEach var="trav" items="${travList }">
							<div class="product_tour" OnClick="location.href ='saleTravelContent?common_code=${trav.common_code}&trav_code=${trav.trav_code}'">
								<div class="product_thumbnail"><img src="${trav.file_name }"></div>
								<div class="product_info">
									<div class="product_name">${trav.trav_name}</div>
									<div class="product_desc">${trav.trav_desc }</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>

		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a href="travel?cntPage=${paging.startPage - 1 }">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="p">
				<c:choose>
					<c:when test="${p == paging.cntPage }">
						<b>${p }</b>
					</c:when>
					<c:when test="${p != paging.cntPage }">
						<a href="travel?cntPage=${p }">${p }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="travel?cntPage=${paging.endPage+1 }">&gt;</a>
			</c:if>
		</div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
<!-- 
        <a href="recruitWrite">모집쓰기ㄱㄱ</a>
        <a href="travelWrite">상품등록 ㄱㄱ</a>
 -->