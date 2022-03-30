<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="resources/css/styleRegistForm.css">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/registCom.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div id="reg_wrap">
		<div class="contaner">
			<div>
				<div class="reg_title">여행사회원가입</div>
			</div>

			<div id="form">
				<form action="doRegistCompany" method="post">
					<table>
						<tr>
							<th>여행사명</th>
							<td><input type="text" name="com_name"
								placeholder="여행사명을 입력해주세요." class="outline"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" name="mem_name"
								placeholder="이름을 입력해주세요." class="outline"></td>
						</tr>
						<tr>
							<th>아이디</th>
							<td><input type="text" name="mem_id"
								placeholder="아이디를 입력 해주세요." class="outline chkPlace">
								<button type="button" class="check idCheck">중복확인</button> <br>
								<div class="idChkResult chkResult"></div></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="mem_pw" id="pw1"
								placeholder="PW를 입력해주세요." class="outline chkPlace" required>
								<div class="result_pw1 chkResult"></div></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="mem_pwChk" id="pw2"
								placeholder="PW를 다시 입력해주세요." class="outline chkPlace" required>
								<div class="result_pw2 chkResult"></div></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" name="com_addr"
								placeholder="주소를 입력해주세요." class="outline"></td>
						</tr>
						<tr>
							<th>휴대폰 번호</th>
							<td><input type="text" name="mem_phone"
								placeholder="'-'없이 숫자만 입력해주세요."
								class="chknumber outline chkPlace" id="number">
								<div class="result-phone chkResult"></div></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="text" name="mem_email"
								placeholder="이메일을 입력해주세요." class="emailchk outline chkPlace">
								<button type="button" class="check sendBtn">인증번호요청</button>
								<br>
								<div id="error_mail" class="result-email chkResult"></div></td>
						</tr>
						<tr>
							<th></th>
							<td><input type="text" class="outline chkPlace"
								name="certifyValue" placeholder="인증번호를 입력해주세요">
								<button type="button" class="check certifyBtn">인증번호확인</button>
								<br>
								<div id="error_mail" class="certifyResult chkResult"></div></td>
						</tr>
						<tr>
						<th>사업자 번호</th>
						<td><input type="text" name="com_number" placeholder="사업자 번호를 입력해주세요." class="outline chkPlace comNumText">
							<button type="button" class="check comCheck">확인</button>
							<br>
                        	<div class="comChkResult chkResult"></div>
						</td>
					</tr>
					</table>
					<div id="bntbox">
						<button type="submit" class="btn insert">회원가입</button>
						<button type="button" class="btn cancle">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>