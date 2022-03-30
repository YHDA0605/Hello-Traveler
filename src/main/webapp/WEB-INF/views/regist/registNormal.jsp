<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/styleRegistForm.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/regist.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp"%>
<div id="reg_wrap">
	<div class="contaner">
        <div>
           	<div class="reg_title">회원가입</div>
           	<br>
        </div>

        <div id="form">
			<form action="doRegistNormal" method="post">
				<table>
					<tr>
						<th>이름</th>
						<td><!-- 2013 수정 -->
                        	<input type="text" name="mem_name" placeholder="이름을 입력해주세요." class="chkPlace outline ime-active">
                        	<div class="nameChkResult chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                      	<th>성별</th>
                     	<td>
                       		<input type="radio" name="gender" value='0' class="gender">&nbsp;남
                       		<input type="radio" name="gender" value='1' class="gender">&nbsp;여
                      	</td>
                  	</tr>   
                  	<tr>
                     	<th>아이디</th>
                     	<td><!-- 2013 수정 -->
                        	<input type="text" name="mem_id" placeholder="영/숫자 5~15자" class="outline chkPlace ime-disabled idchk">
                        	<button type="button" class="check idCheck">중복확인</button>
                        	<br>
                        	<div class="idChkResult chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                  	<!-- !!!!!!!!!0214 수정!!!!!!!!!!!!!!! -->
                     	<th>닉네임</th>
                     	<td>
                        	<input type="text" name="mem_nick" class="nicchk outline chkPlace" id="contents" placeholder="닉네임을 입력해주세요.">
                          	<span class="nickchk">
                          	<span class="count nickcount">0</span><span class="nickcount">/</span><span class="maxcount nickcount">21</span><span class="nickcount">byte</span>
                        	</span>
                        	<button type="button" class="check nickCheck">중복확인</button>
                        	<br>
                        	<div class="nickChkResult chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                     	<th>비밀번호</th>
                     	<td>
                        	<input type="password" name="mem_pw" id="pw1" placeholder="PW를 입력해주세요." class="outline chkPlace ime-disabled" required>
                        	<div class="result_pw1 chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                     	<th>비밀번호 확인</th>
                     	<td>
                        	<input type="password" name="mem_pwChk" id="pw2" placeholder="PW를 다시 입력해주세요." class="outline chkPlace ime-disabled" required>
                        	<div class="result_pw2 chkResult"></div>
                     	</td>   
                  	</tr>
                  	<tr>
                  	<!-- 20/13 수정부분!!!! -->
                     	<th>휴대폰 번호</th>
                     	<td>
                        	<input type="text" name="mem_phone"  placeholder="'-'없이 숫자만 입력해주세요." class="chknumber outline chkPlace" id="number"> 
                       		<div class="result-phone chkResult"></div>
                     	</td>
                  	</tr>
                  	<!-- 20/13 수정부분!!!! -->
                  	<tr>
                     	<th>이메일</th>
                     	<td>
                        	<input type="text" name="mem_email" placeholder="이메일을 입력해주세요." class="emailchk outline chkPlace">
                        	<button type="button" class="check sendBtn">인증번호요청</button><br>
                        	<div id="error_mail" class="result-email chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                     	<th></th>
                     	<td>
      						<input type="text" class="outline chkPlace" name="certifyValue" placeholder="인증번호를 입력해주세요">
      						<button type="button" class="check certifyBtn">인증번호확인</button><br>
                        	<div id="error_mail" class="certifyResult chkResult"></div>
                     	</td>
                  	</tr>
               	</table>
               	<div id="btnbox">
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