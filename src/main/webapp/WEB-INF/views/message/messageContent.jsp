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

				<form action="messageWriteAction" method="post">
					<input type="hidden" name="from_mem_code" value="${CODE}">
					<div class="messageWriteTitle">
						<input type="submit" class="messageBtn" value="답장하기"/>
						<a href="messageUpdateState?msg_code=${MSG.msg_code}&mem_code=${CODE}"><input type="button" class="messageBtn" value="삭제"></a>
					</div>
					<div>
						<table>
							<tr>
								<th>보낸사람 : ${MSG.from_mem_nick }</th>
							</tr>
							<tr>
								<th>받은사람 : ${MSG.to_mem_nick }</th>
							</tr>
							<tr>
								<th>제목 : <input type="text" name="msg_title" value="${MSG.msg_title }" readonly></th>
							</tr>
						</table>
						내용
						<textarea class="comentTextBox" name="msg_cont" cols="150" rows="3" readonly>${MSG.msg_cont}</textarea>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>