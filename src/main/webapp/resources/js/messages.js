$(function(){
	/* id속성이 "checkId"인 요소에 대한 "click"이벤트 정의 */
	$('.idCheck').click(function() {
		//사용자 입력값 받아오기
		var input_value = $("input[name='mem_id']").val();
      
		//입력 검사 여부
		if(!input_value){
			alert("아이디를 입력해주세요.");
			$("input[name='mem_id']").focus();
			return false;
		}
      
		var url = "IdChkCtrl.do";
      
		//get방식 ajax 연동
		$.getJSON(url, {"mem_id" : input_value}, function(json) {
			//결과가 전달되는 파라미터에 읽어온 데이터가 담겨 있으며, 
			//JSON이므로 별도의 추출 과정없이 점(.)으로 데이터 계층으 연결하여 사용할 수 있다.
			var result_text = json.result;
         
			//alert(result_textWW);
         
			var result = eval(result_text);
         
			//결과 출력
			if(result){
				$(".idChkResult").html("<span style='color:red'>존재하지 않는 아이디입니다.</span>")
			}else{
				$(".idChkResult").html("<span style='color:blue'>존재하는 아이디입니다.</span>")
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
				$(".nickChkResult").html("<span style='color:blue'>사용할 수 있는 닉네임입니다.</span>")
			}else{
				$(".nickChkResult").html("<span style='color:red'>사용할 수 없는 닉네임입니다.</span>")
			}
		});
	});
   
	

	

});