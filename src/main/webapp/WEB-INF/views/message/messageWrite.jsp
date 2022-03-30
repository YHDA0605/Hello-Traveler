<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleMessage.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/messages.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="message container">
		<h1 class="title">쪽지함</h1>
		<div class="messageBody">
			<div class="message_nav">
				<div class="messageWriteBox">
					<a href="messageWrite"><button class="messageBtn" type="button">쪽지쓰기</button></a>
					<a href="messageReply?mem_code=${CODE}&mem_nick=${NICK}"><button class="messageBtn" type="button">내게쓰기</button></a>
				</div>
				<ul class="messageBox">
					<li><a href="to_message?to_mem_code=${CODE}">받은쪽지함   ${NOREAD}</a></li>
					<li><a href="from_message?from_mem_code=${CODE}">보낸쪽지함</a></li>
				</ul>
			</div>
			<div class="message_content">
				<form action="messageWriteAction">
					<input type="hidden" name="from_mem_code" value="${CODE}">
					<div class="messageBtn_margin">
						<span>쪽지 쓰기</span>
						<input type="submit" class="messageBtn" value="보내기"/>
						<a href="to_message?to_mem_code=${CODE}"><input type="button" class="messageBtn" value="취소"></a>
					</div>
					<div class="messageBtn_margin">
						<table>
							<tr>
								<th style="width:40px;">받는사람</th>
								<c:if test="${MEM_NICK != null }">
									<input type="hidden" name="to_mem_code" value="${MEM_CODE}">
									<th style="width:500px;"><input type="text" name="mem_id" value="${MEM_NICK}" class="outline chkPlace"></th>
								</c:if>
								<c:if test="${MEM_NICK == null }">
									<th style="width:500px;">
										<input style="border:1px solid black;" type="text" name="mem_id" onkeyup="chkword(this, 15)" placeholder="아이디를 입력 해주세요." class="textarea outline chkPlace">
										<button type="button" class="messageBtn check idCheck" style="background-color: #1187cf; color:white;">아이디 확인</button>
										<span class="idChkResult chkResult"></span>
									</th>
								</c:if>
							</tr>
							<tr>
								<th style="width:40px; margin:10px">제목</th>
								<th><input style="border:1px solid black;" type="text" name="msg_title" placeholder="제목를 입력 해주세요." onkeyup="chkword(this, 120)"></th>
							</tr>
						</table>
						내용
						<textarea class="comentTextBox" style="border:1px solid black"  name="msg_cont" cols="135" rows="45" onkeyup="chkword(this, 4000)"></textarea>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>