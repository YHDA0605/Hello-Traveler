<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleProduct.css" />
<link rel="stylesheet" href="resources/css/styleMyPage.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
	
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<%@include file="/WEB-INF/views/common/mypage_button.jsp" %>
	<div class="myPageBody">
		<%@include file="/WEB-INF/views/common/myPageNav.jsp"%>
		<div class="container my_product">
			<div class="my_product_main">
				<div class="my_product_header">
					<div>여행상품 목록</div>
					<a href="travelWrite"><input class="boardBtn2" type="button" value="글쓰기"></a>
				</div>
				<hr>
				<div class="my_product_main_contents">
						<c:if test="${not empty travList }">
								<div class="product_content">
									<c:forEach var="trav" items="${travList}">
										<div class="my_product_tour" OnClick="location.href ='travelContent?common_code=${trav.common_code}&trav_code=${trav.trav_code}'">

											<div class="product_thumbnail"><img src="${trav.file_name }"></div>
											<div class="product_info">
												<div class="product_name">${trav.trav_name}</div>
												<div class="product_price">
													<fmt:formatNumber value="${trav.trav_price}" pattern="#,###" />원
												</div>
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
	</div>


	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>