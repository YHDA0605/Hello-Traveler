<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleMessage.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('.close_btn').click(function() {
			window.close();
		});
		
		$('.send_msg_btn').click(function() {
			var msg_title = $('.msg_title').val();
			var msg_cont = $('.msg_cont').val();
			
			if(msg_title == ""){
				alert("제목을 입력해주세요");
				$('.msg_title').focus();
				return false;
			}
			
			if(msg_cont == ""){
				alert("내용을 입력해주세요");
				$('.msg_cont').focus();
				return false;
			}
		});
	});
	function submitCloes()
    {
       var comSubmit = $("#${action }Form").serialize();
       
       $.ajax(
       {      
          url : "${action }Action",
          data : comSubmit,
          success : function(result)
                {                           
                   window.close();
                }
       });
    }
</script>
</head>
<body>
<div class="message_box">
	<h1>쪽지쓰기</h1>
	<hr>
	<form id="${action }Form" method="post">
		<input class="${closeWindow }" type="hidden" value="${close }">
		<input name="from_mem_code" type="hidden" value="${CODE}"> <!-- 내 회원코드 -->
		
		<c:if test="${common_code != null}">
            <input name="common_code" type="hidden" value="${common_code }">
		</c:if>
		<c:if test="${recu_code != null}">
			<input name="recu_code" type="hidden" value="${recu_code }">
		</c:if>
		
		<table>
			<tr>
				<td>받는 사람</td>
				<td>
					<input name="to_mem_code" type="hidden" value="${mbr.mem_code }"/>
					<input type="text" value="${mbr.mem_nick }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="msg_title" class="msg_title" placeholder="제목을 입력해주세요" /></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="msg_cont" class="msg_cont" placeholder=" 내용을 입력해주세요."></textarea></td>
			</tr>
		</table>
		<input type="button" class="btn send_msg_btn" value="보내기" onclick="submitCloes()"/>
		<input type="button" class="btn close_btn" value="취소" />
	</form>
</div>
</body>
</html>