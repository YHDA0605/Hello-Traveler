<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/styleMyPage.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script>
		<c:set var="session" scope="session" value="${CODE}"/>
		
		$(document).ready(function(){
    		
			$(".closeBtn").click(function(){
        		$('.pwChangeBox').addClass('closedBox');
        		$("input[name='prevPw']").val('');
    			$("input[name='mem_pw']").val('');
    			$("input[name='mem_pwChk']").val('');
				$(".delmyaccountBox").addClass('closedBox');

    		});
    		
    		
    		
    		$('.nickCheck').click(function() {
    			//사용자 입력값 받아오기
    			var input_value = $("input[name='mem_nick']").val();
    	      
    			//입력 검사 여부
    			if(!input_value){
    				alert("닉네임을 입력해주세요.");
    				$("input[name='mem_nick']").focus();
    				return false;
    			}
    	      
    			var url = "NickChkCtrl.do";
    	      
    			//get방식 ajax 연동
    			$.getJSON(url, {
    				"mem_nick" : input_value
    			}, function(json) {
    				//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
    				//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
    				var result_text = json.result;
    	         
    	         	//alert(result_text);
    	         
    				var result = eval(result_text);
    	         
    				//결과 출력
    				if(result){
    					$(".myPageNickChkResult").html("<span style='color:blue'>사용할 수 있는 닉네임입니다.</span>")
    				}else{
    					$(".myPageNickChkResult").html("<span style='color:red'>사용할 수 없는 닉네임입니다.</span>")
    				}
    			});
    		});
    		
    		
    		// 비밀번호 변경 눌렀을 때 입력 검사 여부
    		$('.pwChangeBtn').click(function() {
    			var prevPwChk_value = $("input[name='prevPw']").val();
    			var pw_value = $("input[name='mem_pw']").val();
    			var pwChk_value = $("input[name='mem_pwChk']").val();
    			
    			
    			//입력 검사 여부
    			if(!pwChk_value){
    				alert("비밀번호 확인을 입력해주세요.");
    				$("input[name='mem_pw']").focus();
    				return false;
    			}
    			
    			if(!pwChk_value){
    				alert("비밀번호 확인을 입력해주세요.");
    				$("input[name='mem_pwChk']").focus();
    				return false;
    			}
    			
    			if(pw_value!=pwChk_value){
    				alert("변경할 비밀번호를 확인해주세요");
    				$("input[name='mem_pwChk']").focus();
    				return false;
    			}
    			
    			var url = "pwChkCtrl.do";
    			
      	      	//get방식 ajax 연동
    			$.getJSON(url, {
    				"mem_prevPw" : prevPwChk_value,
    				"mem_pw" : pw_value
    			}, function(json) {
    				//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
    				//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
    				var result_text = json.result;
    	         
    	         	var result = eval(result_text);
    				
    	         	if(result){
    	         		alert("비밀번호가 변경되었습니다.")
    	         		$("input[name='prevPw']").val('');
    	    			$("input[name='mem_pw']").val('');
    	    			$("input[name='mem_pwChk']").val('');
    				}else{
    					alert("비밀번호가 틀렸습니다.\n다시 변경해주세요");
    					$("input[name='prevPw']").val('');
    	    			$("input[name='mem_pw']").val('');
    	    			$("input[name='mem_pwChk']").val('');
    					return false;
    				}
    	         	$('.pwChangeBox').addClass('closedBox');
    			});
    			
    		});
    		
    		$('.editNickChange').click(function() {
				$("input[name='mem_nick']").removeAttr("readonly");
				$("input[name='mem_nick']").focus();
				$(this).addClass("noShowBtn");
				$('.nickCheck').removeClass("noShowBtn");
				$('.confirmNickChange').removeClass("noShowBtn");
			});
    		
    		$('.editPhoneChange').click(function() {
				$("input[name='mem_phone']").removeAttr("readonly");
				$("input[name='mem_phone']").focus();
				$(this).addClass("noShowBtn");
				$('.confirmPhoneChange').removeClass("noShowBtn");
			});
    		
    		$('.editEmailChange').click(function() {
				$("input[name='mem_email']").removeAttr("readonly");
				$("input[name='mem_email']").focus();
				$(this).addClass("noShowBtn");
				$('.confirmEmailChange').removeClass("noShowBtn");
			});
		});
	</script>
</head>
<body>
	<div class="pwChangeBox closedBox changeBox">
      	<div class="pwChangeForm">
      		<table>
      			<tr><td height="20px"><button class="closeBtn" type="button">&#x2715;</button></td></tr>
      			<tr><td class="pwChangeFormTi">비밀번호 변경</td></tr>
      			<tr><td><input type="password" name="prevPw" placeholder="현재 비밀번호"></td></tr>
      			<tr><td><input type="password" name="mem_pw" placeholder="새 비밀번호"></td></tr>
      			<tr><td><input type="password" name="mem_pwChk" placeholder="새 비밀번호 확인"><td></tr>
      		</table>
      		<button class="btn pwChangeBtn" type="submit">확인</button>
		</div>
	</div>
	
	 <form action="dodelmem" method="post">
      <div class="delmyaccountBox closedBox changeBox">
         <div class="dlemyaccountForm">
            <table>
               <tr><td height="20px"><button class="btn closeBtn" type="button">&#x2715;</button></td></tr>
               <tr><td class="dlemyaccountFormTi">회원탈퇴</td></tr>
               <tr><td>탈퇴한 아이디는 재사용및 복구가 불가합니다 <br>그래도 탈퇴하시겠습니까?</td></tr>
            </table>
            <button class="btn delBtn"type="submit">탈퇴하기</button>
         </div>
      </div>
   </form> 
</body>
</html>