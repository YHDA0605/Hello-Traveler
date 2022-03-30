<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/styleRegistForm.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/regist_SNS.js"></script>
<script>
alert("최초 로그인 입니다.\n추가정보를 입력해주세요!");
 $(document).ready(function(){
	var gender = '${gender}';
	if(gender){
		if(gender=='female'){
		$('.female').attr('checked',true);
	}else{
		$('.male').attr('checked',true);
		}
	}
	
}); 
</script>
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
			<form action="doRegistNormal_SNS" method="post">
					<!--////////////////////////////////0221 추가추가추가추가 -->			
				<input type="hidden" name="mem_sns_id" value="${id}">
				<table>
					<tr>
						<th>이름</th>
						<td>
                        	<input type="text" name="mem_name" placeholder="이름을 입력해주세요." class="chkPlace outline ime-active">
                        	<div class="nameChkResult chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                      	<th>성별</th>
                     	<td>
                       		<input type="radio" name="gender" value='0' class="gender male">&nbsp;남
                       		<input type="radio" name="gender" value='1' class="gender female">&nbsp;여
                      	</td>
                  	</tr>   
                  	<tr>
                     	<th>닉네임</th>
                     	<td>
                        	<input type="text" name="mem_nick" class="nicchk outline chkPlace" id="contents" placeholder="닉네임을 입력해주세요." value="${nickname}">
                          	<span class="nickchk">
                          	<span class="count nickcount">0</span><span class="nickcount">/</span><span class="maxcount nickcount">21</span><span class="nickcount">byte</span>
                        	</span>
                        	<button type="button" class="check nickCheck">중복확인</button>
                        	<br>
                        	<div class="nickChkResult chkResult"></div>
                     	</td>
                  	</tr>
                  
                  	<tr>
                     	<th>휴대폰 번호</th>
                     	<td>
                        	<input type="text" name="mem_phone"  placeholder="'-'없이 숫자만 입력해주세요." class="chknumber outline chkPlace" id="number"> 
                       		<div class="result-phone chkResult"></div>
                     	</td>
                  	</tr>
                  	<tr>
                     	<th>이메일</th>
                     	<td>
                        	<input type="email" name="mem_email" placeholder="이메일을 입력해주세요." class="emailchk outline chkPlace" value="${email}">
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