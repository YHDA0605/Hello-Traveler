<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleMessage.css">
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
			<div class="message_content msgTable">
				<div>
					<table style="table-layout: fixed;">
						<tr>
							<th style="width:100px;">${MSG}</th>
							<th style="width:300px;">제목</th>
							<th>날짜</th>
							<th></th>
						</tr>
						<c:forEach var="list" items="${LIST}">
						<c:if test="${MSG eq '보낸사람' && list.to_common_code != 03}">
							<tr>
								<td style="word-break:break-all;" >${list.from_mem_nick}</td>
								<c:if test="${list.to_common_code == 02}">
									<td style="word-break:break-all" ><a href="messageContent?msg_code=${list.msg_code}&mem_code=${CODE}">${list.msg_title}</a></td>
								</c:if>
								<c:if test="${list.to_common_code != 02}">
									<td style="word-break:break-all" ><a href="messageContent?msg_code=${list.msg_code}&mem_code=${CODE}" style="color: #1187cf;">${list.msg_title}</a></td>
								</c:if>
								<td style="word-break:break-all" ><fmt:formatDate value="${list.msg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>
										<a href="messageReply?mem_code=${list.from_mem_code}&mem_nick=${list.from_mem_nick}"><input type="button" class="messageBtn" value="답장"></a>
									    <a href="messageUpdateState?msg_code=${list.msg_code}&mem_code=${CODE}"><input type="button" class="messageBtn" value="삭제"></a>
									</td>
							</tr>
						</c:if>
						<c:if test="${MSG eq '받은사람' && list.from_common_code != 03}">
							<tr>
								<td style="word-break:break-all">${list.to_mem_nick}</td>
								<c:if test="${list.from_common_code == 02}">
									<td style="word-break:break-all"><a href="messageContent?msg_code=${list.msg_code}&mem_code=${CODE}">${list.msg_title}</a></td>
								</c:if>
								<c:if test="${list.from_common_code != 02}">
									<td style="word-break:break-all"><a href="messageContent?msg_code=${list.msg_code}&mem_code=${CODE}">${list.msg_title}</a></td>
								</c:if>
								<td style="word-break:break-all"><fmt:formatDate value="${list.msg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>
									    <a href="messageUpdateState?msg_code=${list.msg_code}&mem_code=${CODE}"><input type="button" class="messageBtn" value="삭제"></a>
									</td>
							</tr>
						</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	<!-- 받은 쪽지함 페이징 -->
	<c:if test="${MSG eq '보낸사람'}">
	<div style="border-top:1px solid rgba(0,0,0,0.2); display: block; text-align: center;">		
		<c:if test="${PAGE.startPage != 1 }">
			<a href="to_message?cntPage=${PAGE.startPage - 1 }&to_mem_code=${CODE}">&lt;</a>
		</c:if>
		<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }" var="p">
			<c:choose>
				<c:when test="${p == PAGE.cntPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != PAGE.cntPage }">
					<a href="to_message?cntPage=${p }&to_mem_code=${CODE}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGE.endPage != PAGE.lastPage}">
			<a href="to_message?cntPage=${PAGE.endPage+1 }&to_mem_code=${CODE}">&gt;</a>
		</c:if>
	</div>
	</c:if>
	
	<!-- 보낸 쪽지함 페이징 -->
	<c:if test="${MSG eq '받은사람'}">
	<div style="border-top:1px solid rgba(0,0,0,0.2); display: block; text-align: center;">		
		<c:if test="${PAGE.startPage != 1 }">
			<a href="from_message?cntPage=${PAGE.startPage - 1 }&from_mem_code=${CODE}">&lt;</a>
		</c:if>
		<c:forEach begin="${PAGE.startPage }" end="${PAGE.endPage }" var="p">
			<c:choose>
				<c:when test="${p == PAGE.cntPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != PAGE.cntPage }">
					<a href="from_message?cntPage=${p }&from_mem_code=${CODE}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGE.endPage != PAGE.lastPage}">
			<a href="from_message?cntPage=${PAGE.endPage+1 }&from_mem_code=${CODE}">&gt;</a>
		</c:if>
	</div>
	</c:if>
	
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>