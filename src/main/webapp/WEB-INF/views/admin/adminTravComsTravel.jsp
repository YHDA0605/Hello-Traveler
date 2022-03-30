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
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>

</head>

<body>
    <%@include file="/WEB-INF/views/common/header.jsp" %>
    <div class="adminTitle">관리자페이지</div>
	<div class="adminBody">
		<%@include file="/WEB-INF/views/common/adminNav.jsp"%>
		<div class="adminTravCom_body">
			<div class="contents">
				<div class="boardBox">
					<table style="table-layout: fixed" class="board">
						<tr>
							<th>상품 코드</th>
							<th>상품 이름</th>
							<th>기준 가격</th>
							<th>여행 일수</th>
							<th>상품 상태</th>
						</tr>
						<c:forEach var="trav" items="${travList}">
							<tr>
								<td>${trav.common_code }${trav.trav_code }</td>
								<td>
									<a href="travelContent?common_code=${trav.common_code }&trav_code=${trav.trav_code}">
										${trav.trav_name }
									</a>
								</td>
								<td>${trav.trav_price }</td>
								<td>${trav.trav_nights }</td>
								<td>
									<c:if test="${trav.trav_state eq '1'.charAt(0) }">
										검토 전
									</c:if>
									<c:if test="${trav.trav_state eq '2'.charAt(0) }">
										승인
									</c:if>
									<c:if test="${trav.trav_state eq '3'.charAt(0) }">
										반려
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div style="display: block; text-align: center;">
					<c:if test="${PAGE.startPage != 1 }">
						<a
							href="adminPost?cntPage=${PAGE.startPage - 1 }&search_type=${TYPE}&search_text=${TEXT}">&lt;</a>
					</c:if>
					<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }"
						var="p">
						<c:choose>
							<c:when test="${p == PAGE.cntPage }">
								<b>${p }</b>
							</c:when>
							<c:when test="${p != PAGE.cntPage }">
								<a
									href="adminPost?cntPage=${p }&search_type=${TYPE}&search_text=${TEXT}">${p }</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${PAGE.endPage != PAGE.lastPage}">
						<a
							href="adminPost?cntPage=${PAGE.endPage+1 }&search_type=${TYPE}&search_text=${TEXT}">&gt;</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>