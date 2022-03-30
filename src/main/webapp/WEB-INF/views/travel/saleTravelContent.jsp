<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- swiper api js&css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script> 
<!--    -->

<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/styleProduct.css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/travelCoWrite.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('.noResvBtn').click(function() {
			alert("로그인을 해주세요");
		});
		
		$('.resvBtn').click(function() {
			var person_num = $("input[name='person_num']").val();
			var crt_num = $(".active input[name='crt_num']").val();
			var max_num = $(".active input[name='max_num']").val();
			var real_num = max_num - crt_num - person_num;
			
			var sale_state = $(".active input[name='sale_state']").val();
			if(sale_state == 2){
				alert("예약할 수 없는 상품입니다.");
				return false;
			};
			
			if(real_num < 0){
				alert("예약 인원을 초과하였습니다.");
				return false;	
			}
			
		});
		
		// 실행되면 첫번째를 자동으로 선택된 상태로 만듬
		$('.swiper-slide').filter(':first').addClass("active");
		
		// 디폴트로 선택된 상품의 값들을 예약창에 넣어줌
		var common_code = $(".active input[name='common_code']").val();
		var trav_code   = $(".active input[name='trav_code']").val();
		var sale_code   = $(".active input[name='sale_code']").val();
		var sale_price   = $(".active input[name='sale_price']").val();
		var start_day   = $(".active input[name='start_day']").val();
		var end_day   = $(".active input[name='end_day']").val();
		
		
		
		$("input[name='res_sale_price']").val(sale_price);
		$("input[name='start_date']").val(start_day);
		$("input[name='end_date']").val(end_day);
		$("input[name='total_price']").val(sale_price);
		$(".reservationBox input[name='common_code']").val(common_code);
		$(".reservationBox input[name='trav_code']").val(trav_code);
		$(".reservationBox input[name='sale_code']").val(sale_code);
		
		$('.swiper-slide').click(function() {
			$('.swiper-slide').removeClass("active");
			$(this).addClass("active");
			common_code = $(".active input[name='common_code']").val();
			trav_code   = $(".active input[name='trav_code']").val();
			sale_code   = $(".active input[name='sale_code']").val();
			sale_price   = $(".active input[name='sale_price']").val();
			start_day   = $(".active input[name='start_day']").val();
			end_day   = $(".active input[name='end_day']").val();
			
			
			$("input[name='res_sale_price']").val(sale_price);
			$("input[name='person_num']").val(1);
			$("input[name='start_date']").val(start_day);
			$("input[name='end_date']").val(end_day);
			$("input[name='total_price']").val(sale_price);
			$(".reservationBox input[name='common_code']").val(common_code);
			$(".reservationBox input[name='trav_code']").val(trav_code);
			$(".reservationBox input[name='sale_code']").val(sale_code);
		});
		
		let $swiperPrev = document.querySelector('.swiper-button-prev');
		let $swiperNext = document.querySelector('.swiper-button-next');
		var swiper = new Swiper(".mySwiper", {
			slidesPerView : 5,// 한슬라이드에 보여지는 수
			/* spaceBetween  : 10, */
			/* slidesPerGroup : 1,//슬라이드 그룹화 */
			loop : false,//무한반복
			allowTouchMove : false,
			navigation : {
				nextEl : ".swiper-button-next",//다음버튼
				prevEl : ".swiper-button-prev",//이전버튼
			},
			on : {
				activeIndexChange : function() {
					if (this.realIndex == 0) {
						$swiperPrev.classList.add('swiper-button-disabled');
					} else {
						$swiperPrev.classList.remove('swiper-button-disabled');
					}
				}
			}
		});
		
		$(".reservationBox input[name='person_num']").change(function() {
			var sale_price = $("input[name='res_sale_price']").val();
			var person_num = $(".reservationBox input[name='person_num']").val();
			$("input[name='total_price']").val(sale_price*person_num);
		});
	});
</script>

</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="product_main">
		
		<div class="contents">
			
			<div class="articleBtn">
				<a href="mypagetravel?memCode=${CODE}"><button type="button"
						class="boradBtn">목록</button></a>
			</div>
			
			<div class="articleInfo">
            	<div style="text-align: left;">${trav.com_name }</div>
            	<div style="text-align: center;"><fmt:formatDate value="${trav.trav_reg_date }" pattern="yyyy-MM-dd HH:mm"/></div>
            	<div style="text-align: right;">
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
							<span>상품 요약</span><br>
							<p>${trav.trav_desc }</p>
						</div>
					</div>
				</div>



				<div class="sale-list-box">
					<div class="mySwiper">
						<div class="swiper-wrapper">
							<c:if test="${not empty saleList }">
								<c:forEach var="sale" items="${saleList }">
									<div class="swiper-slide" style="width: 180px;">
										<input type="hidden" name="common_code" value="${sale.common_code }"> 
										<input type="hidden" name="trav_code" value="${sale.trav_code }">
										<input type="hidden" name="sale_code" value="${sale.sale_code }"> 
										<input type="hidden" name="sale_price" value="${sale.sale_price }"> 
										<input type="hidden" name="sale_state" value="${sale.sale_state }">
										<input type="hidden" name="crt_num" value="${sale.crt_num }">
										<input type="hidden" name="max_num" value="${sale.max_num }">
										<input type="hidden" name="start_day" value="<fmt:formatDate value="${sale.start_day }" pattern="yyyy-MM-dd"/>">
										<input type="hidden" name="end_day" value="<fmt:formatDate value="${sale.end_day }" pattern="yyyy-MM-dd"/>">
										<div>
											<div class="key">출발일</div>
											:
											<div class="value">
												<fmt:formatDate value="${sale.start_day }" pattern="MM-dd" />
											</div>
										</div>
										<div>
											<div class="key">가격</div>
											:
											<div class="value">${sale.sale_price }</div>
										</div>
										<div>
											<div class="key">예약현황</div>
											:
											<div class="value">${sale.crt_num }/${sale.max_num }</div>
										</div>
										<div>
											<c:if test="${sale.sale_state eq 49}">
												<span style="color: #1187cf">예약이 가능합니다.</span>
											</c:if>
											<c:if test="${sale.sale_state eq 50}">
												<span style="color: red">예약이 불가능합니다.</span>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="swiper-button-prev swip-btn"></div>
						<!--이전버튼-->
						<div class="swiper-button-next swip-btn"></div>
						<!-- 다음버튼 -->
					</div>
				</div>

				<div class="trav_content">
					<div>상품 소개</div>
					<hr>
					<p>${trav.trav_cont}</p>
				</div>
			</div>

			<div class="articleBtn fixed reservationBox">
				<form action="reservationAction" method="post">
					<input type="hidden" name="mem_code" value="${session}">
					<input type="hidden" name="common_code" value="">
					<input type="hidden" name="trav_code" value="">
					<input type="hidden" name="sale_code" value="">
					<table class="tourproduct_box">
						<tr>
							<td><label>상품 가격</label></td>
							<td>
								<input name="res_sale_price" class="box" readonly="readonly"/>원
							</td>
						</tr>
						<tr>
							<td><label>예약 인원</label></td>
							<td>
								<input name="person_num" class="box" value="1" type="number" min="1" max="999"/>명
							</td>
						</tr>
						
						<tr>
							<td><label>여행 기간</label></td>
							<td>
								<input name="start_date" type="date" readonly="readonly"/>~
								<input name="end_date" type="date" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td><label>총 금액</label></td>
							<td>
								<input type="number" class="box" name="total_price"  readonly="readonly">원
							</td>
						</tr>
					</table>
					<c:if test="${session == null}">
						<button type="button" class="boradBtn noResvBtn">예약하기</button>
					</c:if>
					<c:if test="${session != null}">
						<button type="submit" class="boradBtn resvBtn">예약하기</button>
					</c:if>
					<a href="mypagetravel?memCode=${CODE}">
						<button type="button" class="boradBtn">목록</button>
					</a>
				</form>
			</div>
			
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>