$(document).ready(function(){
			$('.showUserId').hide();
			$('.changeUserPasswd').hide();
			
			$(".findId").click(function(){
        		$('.findIdFormTi').text("아이디 찾기");
        		$('.findBtn').addClass("findIdBtn");
        		$('.findInfoBox').removeClass('closedBox');
    		});
    		
    		$(".findPw").click(function(){
    			$('.findIdFormTi').text("비밀번호 찾기");
    			$('.findBtn').addClass("findPwBtn");
        		$('.findInfoBox').removeClass('closedBox');
    		});
    		
    		var certifyNumber = "";
    		
    		//이메일 인증
    		$('.sendBtn').click(function() {
				var email = $("input[name='email']").val();
				
				if(!email){
					alert("이메일을 입력해주세요");
					$("input[name='email']").focus();
					return false;
				}
				
				if( email == '' || email == 'undefined') return;
				if(! email_check(email) ) {
					$(".result-email").html("<span style='color:red'>이메일 형식으로 입력해주세요.</span>")
					alert("이메일 형식으로 입력해주세요.");
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
    		
    		
    		//아이디 찾기
    		$(document).on('click', '.findBtn.findIdBtn', function() {
    			var email = $("input[name='email']").val();
    			var certifyValue = $("input[name='certifyValue']").val();
    			
    			var emailCheck = check(email, certifyValue);
    			
    			if(!emailCheck){
    				return false;
    			}
    			
    			var url = "findIdByEmail.do"
    				
    			//get방식 ajax 연동
    			$.getJSON(url, {"email" : email}, function(json) {
    				//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
    				//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
    					
    				var result = json.result;
    				
    				if(result == ""){
    					alert("등록되지 않은 이메일입니다.");
    					return false;
    				}
    				
    				$('.find_process').hide();
    				$('.showUserId').html("<td>고객님의 아이디는 <strong>"+result+"</strong> 입니다.</td>").show();
    				$('.findBtn').removeClass("findIdBtn");
        			$('.findBtn').removeClass("findPwBtn");
        			$('.findBtn').addClass("close");
    				
    			});
    		});
    		
    		
    		//비밀번호 찾기
    		$(document).on('click', '.findBtn.findPwBtn', function() {
    			var email = $("input[name='email']").val();
    			var certifyValue = $("input[name='certifyValue']").val();
    			
    			var emailCheck = check(email, certifyValue);
    			
    			if(!emailCheck){
    				return false;
    			}
    			
    			var url = "findPwByEmail.do"
    				
        		//get방식 ajax 연동
        		$.getJSON(url, {"email" : email}, function(json) {
        			//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
        			//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
        					
        			var result = json.result;
        				
        			if(result == ""){
        				alert("등록되지 않은 이메일입니다.");
        				return false;
        			}
        				
        			$('.find_process').hide();
        			$('.changeUserPasswd').html("<td><input type='hidden' name='email' value="+email+">"
        									   +"<input type='hidden' name='prevPw' value="+result+">"
        									   +"<input type='password' name='mem_pw' placeholder='새 비밀번호'><br>"
        									   +"<input type='password' name='mem_pwChk' placeholder='새 비밀번호 확인'></td>").show();
  				
        			$('.findBtn').removeClass("findIdBtn");
            		$('.findBtn').removeClass("findPwBtn");
            		$('.findBtn').addClass("changePwBtn");
        				
        		});
    			
    		});
    		
    		$(document).on('click', '.changePwBtn', function(){
    			var email = $("input[name='email']").val();
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
    			
    			var url = "pwChangeAction.do";
    			
      	      	//get방식 ajax 연동
    			$.getJSON(url, {
    				"email" : email,
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
    	         	
    	         	close();
    			});
    			
    		});
    		
    		$(document).on('click', '.close', function() {
    			close();
    			
    		});
    		
    		//이메일 체크
    		function email_check( email ) {    
    			var regex=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    			return (email != '' && email != 'undefined' && regex.test(email));
    		}
    		
    		function close() {
    			$('.findBtn').removeClass("findIdBtn");
    			$('.findBtn').removeClass("findPwBtn");
    			$('.findBtn').removeClass("changePwBtn");
    			$('.findBtn').removeClass("close");
        		$('.findInfoBox').addClass('closedBox');
        		$("input[name='email']").val('');
    			$("input[name='certifyValue']").val('');
    			$('.find_process').show();
				$('.showUserId').empty().hide();
				$('.changeUserPasswd').empty().hide();
				
				certifyNumber = "";
			}
    		
    		function check(email, certifyValue) {
    			var check = true;
    			
    			if(!email){
					alert("이메일을 입력해주세요");
					$('.email').focus();
					return false;
				}
    			
    			if(!certifyValue){
    				alert("인증번호를 입력해주세요.");
    				$('.certifyValue').focus();
    				return check;
    			}
    			
    			if(certifyValue != certifyNumber){
    				alert("인증번호가 틀렸습니다.");
    				$('.certifyValue').focus();
    				return check;
    			}
    			
    			return check;
			}
		});