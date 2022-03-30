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
<link rel="stylesheet" href="resources/css/styleMyPage.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
	
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<%@include file="/WEB-INF/views/common/mypage_button.jsp" %>
	<div class="container myPage">
		<div class="title">
			<h1>나의 예약 목록</h1>
		</div>
		<div class="myPageBody">
			<%@include file="/WEB-INF/views/common/myPageNav.jsp"%>

			<div class="myPageBodyCont">
				<div class="my_reservation_list">
					<c:if test="${not empty resvList }">
						<table class="resv_content" border="1">
							<tr class="my_resv_th">
								<td>
									예약번호
								</td>
								<td>
									상품 명
								</td>
								<td>
									예약인원
								</td>
								<td>
									결제 금액
								</td>
							</tr>
							<c:forEach var="resv" items="${resvList}">
								<tr class="my_resv">
									<td class="resv_code">
										${resv.common_code }${resv.sale_code }${resv.res_code }
									</td>
									<td class="trav_name">
										<a href="saleTravelContent?common_code=${resv.common_code}&trav_code=${resv.trav_code}">
											${resv.trav_name}
										</a>
									</td>
									<td class="resv_num">
										${resv.person_num }명
									</td>
									<td class="trav_price">
										<fmt:formatNumber value="${resv.sale_price}" pattern="#,###" />원
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<div class="pagingBox" style="display: block; text-align: center;">
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
	</div>


	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>