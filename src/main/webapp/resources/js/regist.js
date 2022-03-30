$(document).ready(function(){
	var testId = false;
	var testNick = false;
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
				$(".idChkResult").html("<span style='color:blue'>사용할 수 있는 아이디입니다.</span>");
			}else{
				$(".idChkResult").html("<span style='color:red'>사용할 수 없는 아이디입니다.</span>");
			}
		});
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
				$(".nickChkResult").html("<span style='color:blue'>사용할 수 있는 닉네임입니다.</span>");
				testNick = result;
			}else{
				$(".nickChkResult").html("<span style='color:red'>사용할 수 없는 닉네임입니다.</span>");
				testNick = result;
				return false;
			}
		});
	});
	
	
	//비밀번호 체크 
	$("input[name = mem_pw]").keyup(function(){
		var pw = $("input[name = mem_pw]").val();

		if(pw!=""){
			if(pw.length < 8 || pw.length > 16){
				$(".result_pw1").html("<span style='color:red'>8자리~15자리 이내로 입력해주세요.</span>");
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
	
	$("input[name = mem_email]").keyup(function(){
		test3 = false;
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
		var name_value = $("input[name='mem_name']").val();
		var id_value = $("input[name='mem_id']").val();
		var nick_value = $("input[name='mem_nick']").val();
		var pw_value = $("input[name='mem_pw']").val();
		var pwChk_value = $("input[name='mem_pwChk']").val();
		var phone_value = $("input[name='mem_phone']").val();
		var email_value = $("input[name='mem_email']").val();
		//var input_value = $("input[name='mem_email']").val();
		var url = "EmailChkCtrl.do";
		var gender_value = $("input[name='gender']").val();
		/*   var brith_value = $("input[name='mem_nick']").val();*/
		var certifyValue = $("input[name='certifyValue']").val();

		//입력 검사 여부
		if(!name_value){
			alert("이름을 입력해주세요.");
			$("input[name='mem_name']").focus();
			return false;
		}
	      
		//2014 수정
		if($('input:radio[name=gender]').is(":checked")==false){
			alert("성별을 체크해 주세요.");
			$("input[name='gender']").focus();
			return false;
		}
	      
		if(!id_value){
			alert("아이디을 입력해주세요.");
			$("input[name='mem_id']").focus();
			return false;
		}
	      
		//2013 수정
		/*if(id_value.length < 5){
			alert("5자 이상 입력해주세요.");
			$("input[name='mem_id']").focus();
			return false;
		}*/
	      
		if(!nick_value){
			alert("닉네임을 입력해주세요.");
			$("input[name='mem_nick']").focus();
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
		
		//0213수정
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
	      
		if(pw_value!=pwChk_value){
			alert("비밀번호를 확인해주세요");
			$("input[name='mem_pwChk']").focus();
			return false;
		}
	    
		if(testId == false || testNick == false){
			alert("아이디 혹은 닉네임 중복확인을 해주세요.");
			return false;
		}
		
		if(test3 == false){
			alert("이메일 인증을 해주세요");
			return false;
		}
		
		
	})
	
	$(function () {
        $('.nickchk').each(function () {
            // count 정보 및 count 정보와 관련된 textarea/input 요소를 찾아내서 변수에 저장한다.
            var $maxcount = $('.maxcount', this);
            var $count = $('.count', this);
            var $input = $("#contents");
 
            // .text()가 문자열을 반환하기에 이 문자를 숫자로 만들기 위해 1을 곱한다.
            var maximumByte = $maxcount.text() * 1;
            // update 함수는 keyup, paste, input 이벤트에서 호출한다.
            var update = function () {
                var before = $count.text() * 1;
                var str_len = $input.val().length;
                var cbyte = 0;
                var li_len = 0;
                for (i = 0; i < str_len; i++) {
                    var ls_one_char = $input.val().charAt(i);
                    if (escape(ls_one_char).length > 4) {
                        cbyte += 3; //한글이면 2를 더한다
                    } else {
                        cbyte++; //한글아니면 1을 더한다
                    }
                    if (cbyte <= maximumByte) {
                        li_len = i + 1;
                    }
                }
                // 사용자가 입력한 값이 제한 값을 초과하는지를 검사한다.
                if (parseInt(cbyte) > parseInt(maximumByte)) {
                    // $(".nickChkResult").html("<span style='color:red'>영:최대17자/한:최대7자를 입력해주세요.</span>");
                    var str = $input.val();
                    var str2 = $input.val().substr(0, li_len);
                    $input.val(str2);
                    var cbyte = 0;
                    for (i = 0; i < $input.val().length; i++) {
                        var ls_one_char = $input.val().charAt(i);
                        if (escape(ls_one_char).length > 4) {
                            cbyte += 3; //한글이면 2를 더한다
                        } else {
                            cbyte++; //한글아니면 1을 더한다
                        }
                    }
                }
                $count.text(cbyte);
            };
            // input, keyup, paste 이벤트와 update 함수를 바인드한다
            $input.bind('input keyup keydown paste change', function () {
                setTimeout(update, 0)
            });
            update();
        });
    });
});