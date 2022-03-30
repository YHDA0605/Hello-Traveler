<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleRecruit.css">
<link rel="stylesheet" href="resources/css/index.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
	new Swiper('.swiper1', {

			slidesPerView : 2, // 동시에 보여줄 슬라이드 갯수
			spaceBetween : 10, // 슬라이드간 간격
			slidesPerGroup : 2, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
				
			// 그룹수가 맞지 않을 경우 빈칸으로 메우기
			// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
			loopFillGroupWithBlank : true,

			loop : true, // 무한 반복

			pagination : { // 페이징
				el : '.swiper-pagination',
				clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
			},
			navigation : { // 네비게이션
				nextEl : '.swiper-button-next', // 다음 버튼 클래스명
				prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
			},
		});

		new Swiper('.swiper2', {

			slidesPerView : 4, // 동시에 보여줄 슬라이드 갯수
			spaceBetween : 35, // 슬라이드간 간격
			slidesPerGroup : 4, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

			// 그룹수가 맞지 않을 경우 빈칸으로 메우기
			// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
			loopFillGroupWithBlank : true,

			loop : true, // 무한 반복

			pagination : { // 페이징
				el : '.swiper-pagination',
				clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
			},
			navigation : { // 네비게이션
				nextEl : '.swiper-button-next', // 다음 버튼 클래스명
				prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
			},
		});
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<div class="index_wrap">
			<div class="index_Box"><img src="resources/images/back2.jpg"></div>
			<div class="index_Box2">
				<div class="bestPost">
					<div class="postHitTitle">지금 이시각 베스트글</div>
					<c:forEach var="hit" items="${HLIST}">
						<div class="postHit">
							<div class="postHitBox" style=" max-width: 400px">
								<span style="word-break:break-all;"><a href="postContent?common_code=${hit.common_code }&POST_CODE=${hit.post_code}">${hit.post_title}</a></span>
							</div>
							<div class="postHitCount">
								<span style="color: #1187cf;">[${hit.coment_count}]</span>
							</div>
						</div>
					</c:forEach>
				</div>

				<div>
					<div class="postHitTitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;여행
						친구를 찾고있어요!</div>
					<div class="swiper-container swiper1 recruit_Box" id="recru">
						<div class="swiper-wrapper">
							<c:forEach var="recru" items="${NEWLIST}">
								<div id="super" class="swiper-slide recruit_contents"
									OnClick="location.href ='recruitContent?common_code=${recru.common_code}&recu_code=${recru.recu_code }'"
									style="cursor: pointer;">
									<div class="recruit_wrap">
										<div class="recruit_contents_top">
											<div>${recru.membersDTO.mem_nick }</div>
											<c:if test="${recru.recu_common_code eq '31001'}">
												<div class=recruiting>모집중</div>
											</c:if>
											<c:if test="${recru.recu_common_code eq '31002'}">
												<div class="recruitied">모집종료</div>
											</c:if>
										</div>
										<div class="recruit_contents_title">${recru.recu_title }</div>
										<div class="recruit_contents_hit">신청수 :
											${recru.recu_apply_n } &middot;조회수 : ${recru.recu_hit }</div>
										<hr>
										<div class="recruit_contents_bottom">
											<span>${recru.commonsDTO.common_name }</span> <span>${recru.recu_max_n }
												명</span>
											<fmt:parseDate value="${recru.recu_deadline}" var="deadline"
												pattern="yyyyMMdd" />
											<fmt:parseDate value="${recru.recu_start_date}"
												var="start_date" pattern="yyyyMMdd" />
											<fmt:parseDate value="${recru.recu_end_date}" var="end_date"
												pattern="yyyyMMdd" />
											<span>기한 : <fmt:formatDate value="${deadline}"
													pattern="M월d일" /></span> <span> <fmt:formatDate
													value="${start_date}" pattern="M월d일" />~ <fmt:formatDate
													value="${end_date}" pattern="M월d일" />
											</span>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<!-- 				   
						<div class="swiper-button-next"></div>
    			  	 	<div class="swiper-button-prev"></div> 
    			   		-->
						<div class="swiper-pagination"></div>
					</div>
				</div>
			</div>


			<div class="index_Box">
				<div class="postHitTitle">다양한 여행 상품이 기다리고있어요!</div>
				<div class="swiper-container swiper2 sale_margin">
					<div class="swiper-wrapper">
						<c:forEach var="trav" items="${travList}">
							<div id="sale" class="swiper-slide product_tour"
								OnClick="location.href ='saleTravelContent?common_code=${trav.common_code}&trav_code=${trav.trav_code}'"
								style="cursor: pointer;">
								<div class="product_picture">
									<img src="${trav.file_name }" width="100%" height="100%">
								</div>
								<div class="product_info">
									<div class="product_name">${trav.trav_name}</div>
									<div class="product_desc">${trav.trav_desc }</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!--                
					<div class="swiper-button-next"></div>
               		<div class="swiper-button-prev"></div> 
               		-->
					<div class="swiper-pagination"></div>
				</div>
			</div>
		</div>
	</div>


	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>

