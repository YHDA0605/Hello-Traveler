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
<script type="text/javascript" src="js/travelCoWrite.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="product_main">
		
		<div class="contents">
			
			<div class="articleBtn">
				
				
				<c:if test="${COMMON_CODE < 10003}"> <!-- 02-22 추가(양) -->
					<a href="notAllowTravel?common_code=${trav.common_code}&trav_code=${trav.trav_code}&cmd=not">
						<button class="notAllowedBtn">반려</button>
					</a>
					<a href="AllowTravel?common_code=${trav.common_code}&trav_code=${trav.trav_code}&cmd=allow">
						<button class="boradBtn">승인</button>
					</a>
					<a href="adminTravComsTravel?com_code=${trav.com_code}">
						<button type="button" class="boradBtn">목록</button>
					</a>
				</c:if>
				
				
				<c:if test="${session eq trav.mem_code }">
					<a
						href="travelEdit?common_code=${trav.common_code}&trav_code=${trav.trav_code}">
						<button type="button" class="boradBtn">수정</button>
					</a>
					<a
						href="travelDelete?common_code=${trav.common_code}&trav_code=${trav.trav_code}">
						<button type="button" class="boradBtn">삭제</button>
					</a>
					<a href="mypagetravel?memCode=${CODE}">
						<button type="button" class="boradBtn">목록</button>
					</a>
				</c:if>
			</div>
			
			<div class="articleInfo">
            	<div style="text-align: left;">${trav.com_name }</div>
            	<div style="text-align: center;"><fmt:formatDate value="${trav.trav_reg_date }" pattern="yyyy-MM-dd HH:mm"/></div>
            	<div style="text-align: right;">
            		상품상태 :
            		<c:if test="${trav.trav_state eq '1'.charAt(0)}">검수 전</c:if>
            		<c:if test="${trav.trav_state eq '2'.charAt(0)}">승인</c:if>
            		<c:if test="${trav.trav_state eq '3'.charAt(0)}">반려</c:if>
            	</div>
        	</div>
			
			<div class="contentBox">
				<div style="word-break:break-all;" class="trav_contentTitle">${trav.trav_name }</div>

				<div class="trav_contentInfo">
					<div class="travels_thumbnail">
						<img src="${trav.file_name }">
					</div>
					<div class="travels_info">
						<div>
							<span>국가/지역 : </span>${trav.common_name}
						</div>
						<div>
							<span>여행 일수 : </span>${trav.trav_nights}박
						</div>
						<div>
							<span>기준 가격 : </span>
							<fmt:formatNumber value="${trav.trav_price}" pattern="#,###" /> 원
						</div>
						<div>
							<span>상품 요약</span><br>
							<p>${trav.trav_desc }</p>
						</div>
					</div>
				</div>
				<div class="trav_content">
					<div>상세설명</div>
					<br>
					<p>${trav.trav_cont}</p>
				</div>
			</div>

			<div class="articleBtn fixed">
				<form action="saleWriteAction" method="post">
					<c:if test="${trav.trav_state eq '2'.charAt(0) && COMMON_CODE == 10004}">
					<input type="hidden" name="common_code" value="${trav.common_code}">
         			<input type="hidden" name="trav_code" value="${trav.trav_code}">
					<table class="tourproduct_box">
						<tr>
							<td><label>상품 가격</label></td>
							<td>
								<input name="sale_price" class="box" type="number" min="0" max="9999999"/>원
							</td>
						</tr>
						<tr>
							<td><label>최대 모집 인원</label></td>
							<td>
								<input name="max_num" class="box" type="number" min="1" max="999"/>명
							</td>
						</tr>
						
						<tr>
							<td><label>등록 기간</label></td>
							<td>
								<input name="start_date" type="date" class="start_date"/>~
								<input name="end_date" type="date" class="end_date"/>
							</td>
							
						</tr>
						<tr>
							<td>출발 요일</td>
							<td>
								<input type="checkbox" name="day" value="mon" class="day">월 
								<input type="checkbox" name="day" value="tue" class="day">화 
								<input type="checkbox" name="day" value="wed" class="day">수 
								<input type="checkbox" name="day" value="thu" class="day">목 
								<input type="checkbox" name="day" value="fri" class="day">금 
								<input type="checkbox" name="day" value="sat" class="day">토 
								<input type="checkbox" name="day" value="sun" class="day">일
							</td>
						</tr>
					</table>
					
					<button type="submit" class="boradBtn">판매등록</button>
					</c:if>
					
				</form>
				
			</div>
			
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>