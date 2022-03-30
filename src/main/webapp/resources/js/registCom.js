$(document).ready(function(){
	var testId = false;
	var test3 = false;
	
//여기부터 추가함	
	
	var certifyNumber = "";
	//이메일 보내기
	$('.sendBtn').click(function() {
		var email = $("input[name='mem_email']").val();
		
		if(!email){
			alert("이메일을 입력해주세요");
			$("input[name='mem_email']").focus();
			return false;
		}
		
		if( email == '' || email == 'undefined') return;
		if(! email_check(email) ) {
			$(".result-email").html("<span style='color:red'>이메일 형식으로 입력해주세요.</span>")
			alert("올바른 이메일 형식으로 입력해주세요.");
			$("input[name='mem_email']").focus();
			return false;
		}
		
		alert("인증번호를 보냈습니다.");
		
		var url = "mailSend.do"
		
		//get방식 ajax 연동
		$.getJSON(url, {"email" : email}, function(json) {
			//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
			//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
			certifyNumber = json.certifyNumber;
			
		});
	});
	
	
	$('.certifyBtn').click(function() {
		var email = $("input[name='mem_email']").val();
		var certifyValue = $("input[name='certifyValue']").val();
		
		if(!certifyValue){
			alert("인증번호를 입력해주세요");
			$("input[name='certifyValue']").focus();
			return false;
		}
		
		if(certifyNumber != certifyValue){
			alert("잘못된 인증번호를 입력하였습니다.");
			$("input[name='certifyValue']").focus();
			return false;
		}
		
		var url = "certifyEmail.do"
		
		//get방식 ajax 연동
		$.getJSON(url, {
			"email" : email,
			"certifyValue" : certifyValue
		}, function(json) {
			//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
			//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
			var certifyResult = json.certifyResult;
			
			test3 = eval(certifyResult);
			
			if(test3){
				$('.certifyResult').html("<span style='color:blue'> 사용 가능한 이메일입니다.</span>");
			}else{
				$('.certifyResult').html("<span style='color:red'> 사용할 수 없는 이메일입니다.</span>");
			}
		});
	});
	
	
	
	
//여기까지 추가함
	
	//아이디에 한글 입력 방지   2013 추가
	$("input[name=mem_id]").keyup(function(event){ 
		var str  = $("input[name='mem_id']").val();
		if (!(event.keyCode >=37 && event.keyCode<=40)) { 
			var inputVal = $(this).val(); 
			var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; 
			
			if(check.test(inputVal)){ 
				$(this).val(""); 
				$(".idChkResult").html("<span style='color:red'> 영문자와 숫자 조합만 가능합니다.</span>");   
			} else {
				$(".idChkResult").text('');
            	} 
		};
		
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
		if(special_pattern.test(str) == true){
			$(".idChkResult").html("<span style='color:red'>영문자와 숫자 조합만 가능합니다.</span>");   
		}
	});
    
	//전화번호에 한글,영어 입력 방지  2013 추가
	$(".chknumber").keyup( function(){ 
		var temp = document.getElementById('number').value;
		if(isNaN(temp) == true) {
			$(".result-phone").html("<span style='color:red'>숫자만 입력 해주세요.</span>");
		} else {
			$(".result-phone").html("");
		}
	});
	
	//전화번호 10~11자리 입력 확인  2013 추가
	$("input[name = mem_phone]").blur(function(){
		var number = $("input[name = mem_phone]").val();
      
		if(number){
			if(number.length < 10 || number.length >11){
				$(".result-phone").html("<span style='color:red'>전화번호를 정확히 입력해 주세요.</span>");
			}else{         
				$(".result-phone").html("");
			}      
		}
	});
   
	/* id속성이 "checkId"인 요소에 대한 "click"이벤트 정의 */
	$('.idCheck').click(function() {
		//사용자 입력값 받아오기
		var input_value = $("input[name='mem_id']").val();
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

		//입력 검사 여부
		if(!input_value){
			alert("아이디를 입력해주세요.");
			$("input[name='mem_id']").focus();
			return false;
		}
       
		if(input_value.length<5 ||input_value.length>15){
			$(".idChkResult").html("<span style='color:red'>5자리~15자리 이내로 입력해주세요.</span>");
			return false;
		}
       
		if(special_pattern.test(input_value) == true){
			$(".idChkResult").html("<span style='color:red'>영문자와 숫자 조합만 가능합니다.</span>");
			return false;
		}
          
		var url = "IdChkCtrl.do";
      
		//get방식 ajax 연동
      
		$.getJSON(url, {"mem_id" : input_value}, function(json) {
        //결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
        //JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
        var result_text = json.result;
        
        //alert(result_text);
        var result = eval(result_text);
        testId = result;
         
         
        	//결과 출력
        	if(result){
        		$(".idChkResult").html("<span style='color:blue'>사용할 수 있는 아이디입니다.</span>")
        	}else{
        		$(".idChkResult").html("<span style='color:red'>사용할 수 없는 아이디입니다.</span>")
        	}
		});
	});
   
	//비밀번호 체크 
	$("input[name = mem_pw]").keyup(function(){
		var pw = $("input[name = mem_pw]").val();

		if(pw!=""){
			if(pw.length < 8 || pw.length > 16){
				$(".result_pw1").html("<span style='color:red'>8자리~16자리 이내로 입력해주세요.</span>");
			}else{         
				$(".result_pw1").html("");
			}      
		}
	});
   
	$("#pw2").keyup(function(){
		var pw1 = $("#pw1").val();
		var pw2 = $("#pw2").val();

		if(pw1!=""||pw2!=""){
			if(pw1==pw2){
				$(".result_pw2").html("");
			}else{
				$(".result_pw2").html("<span style='color:red'>비밀번호가 일치하지 않습니다.</span>");
			}
		}      
	});

	//이메일 체크
   
	function email_check( email ) {    
		var regex=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		return (email != '' && email != 'undefined' && regex.test(email));
	}
   
	$("input[name = mem_email]").blur(function(){
		var email = $("input[name = 'mem_email']").val();
		if( email == '' || email == 'undefined') return;
		
		if(! email_check(email) ) {
        
			$(".result-email").html("<span style='color:red'>이메일 형식으로 입력해주세요.</span>")
			//$("input[name='mem_email']").focus();
			return false;
		}else {
			$(".result-email").text('');
		}
	});
   
  
	//회원가입버튼 눌렀을 때 입력 검사 여부
	$('.insert').click(function() {
		var com_name_value = $("input[name='com_name']").val();
		var name_value = $("input[name='mem_name']").val();
		var id_value = $("input[name='mem_id']").val();
		var pw_value = $("input[name='mem_pw']").val();
		var pwChk_value = $("input[name='mem_pwChk']").val();
		var com_addr_value = $("input[name='com_addr']").val();
		var phone_value = $("input[name='mem_phone']").val();
		var email_value = $("input[name='mem_email']").val();
		var com_number_value = $("input[name='com_number']").val();
      
      
      
		//입력 검사 여부
      
		if(!com_name_value){
			alert("여행사명을 입력해주세요.");
			$("input[name='com_name']").focus();
			return false;
		}

		if(!name_value){
			alert("이름을 입력해주세요.");
			$("input[name='mem_name']").focus();
			return false;
		}

		if(!id_value){
			alert("아이디을 입력해주세요.");
			$("input[name='mem_id']").focus();
			return false;
		}

		if(!pw_value){
         
			alert("비밀번호를 입력해주세요.");
         
			$("input[name='mem_pw']").focus();
         
			return false;
      
		}
      
		if(!pwChk_value){
			alert("비밀번호 확인을 입력해주세요.");
			$("input[name='mem_pwChk']").focus();
			return false;
		}

		if(pw_value.length < 8 || pw_value.length > 16){
			$("input[name='mem_pw']").focus();
			alert("비밀번호를 8자리~16자리 이내로 입력해주세요..");
			return false;
		}
      
		if(pw_value!==pwChk_value){
			$("input[name='mem_pwChk']").focus();
			alert("비밀번호를 확인해주세요.");
			return false;
		}

		if(!com_addr_value){
			alert("주소를 입력해주세요.");
			$("input[name='com_addr']").focus();
			return false;
		}

		if(!phone_value){
			alert("전화번호을 입력해주세요.");
			$("input[name='mem_phone']").focus();
			return false;
		}
      
		if(phone_value.length < 10 || phone_value.length >11){
			$("input[name='mem_phone']").focus();
			alert("휴대폰번호를 정확히 입력해 주세요.");
			return false;
		}
      
		if(!email_value){
			alert("이메일을 입력해주세요.");
			$("input[name='mem_email']").focus();
			return false;
		}
      
		if(!com_number_value){
			alert("사업자 번호를 입력해주세요.");
			$("input[name='com_number']").focus();
			return false;
		}
      
		if(pw_value!=pwChk_value){
			alert("비밀번호를 확인해주세요");
			$("input[name='mem_pwChk']").focus();
			return false;
		}
      
		if(testId == false){
			alert("정보를 다시 확인해주세요");
			return false;
		}
		
		if(test3 == false){
			alert("이메일 인증을 해주세요");
			return false;
		}
	});
	
	$('.comCheck').click(function(){
		var data = {
			    "b_no": [$('.comNumText').val()] // 사업자번호 "xxxxxxx" 로 조회 시,
			   }; 
		var comCheckValue = "";
		
		$.ajax({
			  url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=daC%2F48Lcj0V%2FwGFG%2BK4mstMXEHJz2BpK6cDQeRqhK30u5etHWC%2F%2B6ORQ8dgWj%2B7nc7zttVauxmFvP5JQ1%2BTKgw%3D%3D",  // serviceKey 값을 xxxxxx에 입력
			  type: "POST",
			  data: JSON.stringify(data), // json 을 string으로 변환하여 전송
			  dataType: "JSON",
			  contentType: "application/json",
			  accept: "application/json",
			  success: function(result) {
			      console.log(result);
			      comCheckValue = result.data[0].tax_type;
			  },
			  error: function(result) {
			      console.log(result.responseText); //responseText의 에러메세지 확인
			  }
			});
		
		//나온 결과값안에 tax_type를 가져와서 
		if(comCheckValue == "국세청에 등록되지 않은 사업자등록번호입니다."){
			$(".comChkResult").html("<span style='color:red'>사용할 수 없는 사업자 등록 번호입니다.</span>")
		}else{
			$(".comChkResult").html("<span style='color:blue'>사용할 수 있는 사업자 등록 번호입니다.</span>")
		}
	});
});