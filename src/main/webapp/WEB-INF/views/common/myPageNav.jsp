<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function(){
    		$(".pwChange").click(function(){
        		$('.pwChangeBox').removeClass('closedBox')
    		});
    		$(".delmyaccount").click(function(){
                $('.delmyaccountBox').removeClass('closedBox')
             });
		});
	</script>
</head>
<div class="myPageNav">
	<ul>
<c:set var ="session" scope="session" value="${COMMON_CODE}"></c:set>
     <li><a href="mypage?mem_code=${CODE }"><button>내 정보 관리</button></a></li>
      <li><button class="pwChange">비밀번호 수정</button></li>
      <c:if test="${session == '10003'}">
      <li><button>내가 올린 모집글</button></li> 
      <li><a href="myReservation?mem_code=${CODE }"><button>예약/구매 확인</button></a></li>
      </c:if>
      <c:if test="${session == '10004'}">
      <li><a href="mypagetravel?mem_code=${CODE}"><button>여행 상품 관리</button></a></li>
      </c:if>
      <li><button class="delmyaccount">회원 탈퇴</button></li>
	</ul>
</div>