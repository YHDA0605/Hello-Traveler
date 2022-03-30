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
<script type="text/javascript" src="js/common.js"></script>
<script>
	$(document).ready(function() {
		
		$(".good").click(function() {
			alert("로그인 해주세요.");
		});

		
		$(".comentSubmitBtn").click(function() {
			var content = $(".comentTextBox").val();

			if(content == ""){
				alert("내용이 비어있습니다.");
				$(".comentTextBox").focus();
				return false;
			}
			
		});

	})
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="contents">
		<div class="articleBtn">
			<c:if test= "${POST.mem_code eq CODE||COMMON_CODE < 10003}">
				<a href="postUpdateAction?common_code=${POST.common_code}&POST_CODE=${POST.post_code}">
					<input class="boradBtn" type="button" value="수정">
				</a> 
				<a href="postDeleteAction?common_code=${POST.common_code}&POST_CODE=${POST.post_code}">
					<input class="boradBtn" type="button" value="삭제">
				</a> 
			</c:if>
			<a href="post?common_code=${POST.common_code}"> 
				<input class="boradBtn" type="button" value="목록">
			</a>
		</div>
		<div class="articleInfo">
			<span>${POST.mem_nick}</span> <span>작성날짜 :
				${POST.post_date}</span> <span>조회수 : ${POST.post_hit} &nbsp; 추천 :
				${POST.count } </span>


		</div>
		<div class="contentBox">
			<div style="word-break:break-all;" class="borad_contentTitle">${POST.post_title}
				<c:if test="${session != null}">
					<a href="goodAction?common_code=${POST.common_code }&CODE=${POST.post_code }&MEM_CODE=${CODE}">
						<img class="GoodImg" src="resources/images/good.png">
					</a>
				</c:if>
				<c:if test="${session == null}">
					<img class="GoodImg good" src="resources/images/good.png">
				</c:if>
			</div>
			<c:if test= "${IMG != null}">
				<img src="${IMG}" style=" width: 300px; height: 300px">
			</c:if>
			<div class="borad_content">${POST.post_cont}</div>
			<br>
		</div>
		<div class="comentBox">

<div class="borad_contentTitle">댓글</div>
			<form action="comentAction" method="post">
				<input type="hidden" name="common_code" value="${POST.common_code}">
				<input type="hidden" name="post_code" value="${POST.post_code}">
				<input type="hidden" name="re_step" value="${POST.re_step}">
				<input type="hidden" name="mem_code" value="${CODE}">
				<div class="comentWriteBox">
					<div class="borad_comentWrite">
						<textarea class="comentTextBox" name="post_cont" cols="150" rows="3" onkeyup="chkword(this, 4000)"></textarea>
					</div>
					<div>
					<c:if test="${session != null}">
						<input class="boradBtn comentSubmitBtn" type="submit" value="댓글 달기">
					</c:if>
					<c:if test="${session == null}">
						<input class="boradBtn good" type="button" value="댓글 달기">
					</c:if>
					</div>
				</div>
			</form>
			
		<c:set var="session" scope="session" value="${CODE}"></c:set>
		<c:forEach var="list" items="${LIST}">

				<div class ="re_coment re_coment_Box${list.post_code}">
				<div class="re_coment_Box">
					<form class="coment" action="comentUpdateAction" method="post">
						<input type="hidden" name="common_code" value="${list.common_code}">
						<input type="hidden" name="post_code" value="${list.post_code}">
						<input type="hidden" name="mem_code" value="${CODE}">
						<span>${list.mem_nick }</span> 
						<span>(${list.post_date })</span>
						<c:if test="${list.post_state eq '0'.charAt(0)}">
							<input class = "comentBtn re_coment_Btn${list.post_code}" type="button" value="답글달기">
							<div class="edit${list.post_code}" style="display : inline-block">
								<c:if test= "${COMMON_CODE < 10003 || list.mem_code eq CODE}">
									<!-- 수정 처리 해야함 -> 수정버튼 누르면 텍스트 버튼 활성 -> 텍스트 고치고 확인 누르면 갱신 -->
									<input type="button" class="comentBtn editcontentChange${list.post_code}" value="수정">
	              					<input type="submit" class="comentBtn contentChange${list.post_code} noShowBtn noEdit" value="확인">
									<a href="postDeleteAction?common_code=${list.common_code }&POST_CODE=${list.post_code}">
										<input class="comentBtn" type="button" value="삭제">
									</a> 
								</c:if>
							</div>
								<div>
									<input class = "editBox${list.post_code}" type="text" value="${list.post_cont }" name="post_cont" style="display : inline-block;; width:100%;" readonly>
								</div>
						</c:if>
					</form>
					<div class="coment">
						<c:if test="${list.post_state eq '1'.charAt(0)}">
							<div>
								<span>삭제 된 댓글입니다.</span>
							</div>
						</c:if>
					</div>
				</div>
				
				<form action="re_comentAction" method="post">
					<input type="hidden" name="common_code" value="${POST.common_code}">
					<input type="hidden" name="post_code" value="${POST.post_code}">
					<input type="hidden" name="re_level" value="${list.re_level}">
					<input type="hidden" name="mem_code" value="${CODE}">
					
					<div class="re_coment${list.post_code}">
							<textarea class='comentTextBox re_comentTextBox${list.post_code}' name='post_cont' cols='100' rows='3' style="border : 1px solid black;" onkeyup="chkword(this, 4000)"></textarea>
							<c:if test="${session != null}">
								<input class='comentBtn re_comentSubmitBtn${list.post_code}' type='submit' value='답글 달기'>
							</c:if>
							<c:if test="${session == null}">
								<input class='good' type='button' value='답글 달기'>
							</c:if>
					</div>
				</form>
				
				
					<script type="text/javascript">
                     $(document).ready(function() {
                 		$('.editcontentChange${list.post_code}').click(function() {
                 			$(".editBox${list.post_code}").removeAttr("readonly");
                 			$(".editBox${list.post_code}").focus();
                 			$(".editcontentChange${list.post_code}").addClass("noShowBtn");
                 			$('.contentChange${list.post_code}').removeClass("noShowBtn");
                 		});
                 		
                 		
                    	 $('.re_coment${list.post_code}').css("display","none");
                        
	                        $('.re_coment_Btn${list.post_code}').click(function() {
	                       	 var coment = $('.re_coment${list.post_code}').css('display');
	                        	if(coment == "none"){
	                           $('.re_coment${list.post_code}').show();
                        		}else{
                               $('.re_coment${list.post_code}').hide();
                            }
	                        });
                        
	                		$(".re_comentSubmitBtn${list.post_code}").click(function() {
	                			var content = $(".re_comentTextBox${list.post_code}").val();

	                			if(content == ""){
	                				alert("내용이 비어있습니다.");
	                				$(".re_comentTextBox${list.post_code}").focus();
	                				return false;
	                			}
	                		});
                        });
                  </script>
                  

                  <c:if test="${list.re_step == 3}">
					<style>
						.re_coment_Box{
							display : inline-block;
						}
						.re_coment_Box${list.post_code}:before{
							content:"";
					        background-image: url("resources/images/Arrow.png");
					        background-size:30px 30px;
					        width:30px;
					        height:30px;
					        margin-left : 10px;
					        display:inline-block;
						}
					</style>
				</c:if>
</div>
			</c:forEach>

		</div>
		<div class="articleBtn">
			<a href="post?common_code=${POST.common_code}"> <input class="boradBtn" type="button"
				value="목록으로">
			</a>
		</div>

	</div>


	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>