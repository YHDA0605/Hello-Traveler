$(document).ready(function() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0 so need to add 1 to make it 1!
	var yyyy = today.getFullYear();
	if(dd<10){
	  dd='0'+dd
	} 
	if(mm<10){
	  mm='0'+mm
	} 

	today = yyyy+'-'+mm+'-'+dd;
	
	$('.start_date').attr("min", today);
	
	$('.start_date').change(function() {
		$('.end_date').val('');
		$('.end_date').attr("min", $('.start_date').val());
	});
	
	$('.end_date').click(function() {
		if(!$('.start_date').val()){
			alert("시작 기간을 먼저 입력해주세요");
			return false;
		}
	});

});
   
   $(function(){
		
		//글쓰기버튼 눌렀을 때 입력 검사 여부
		$('.insert').click(function() {
			var recru_value = $("input[name='recru_n']").val();
			var sale_price  = $("input[name='sale_price']").val();
	        var start_day   = $("input[name='start_day']").val();
	        var end_day     = $("input[name='end_day']").val(); 
	      
			//입력 검사 여부
			if(!recru_value){
				alert("최대 모집 인원을 입력해주세요.");
				$("input[name='recru_n']").focus();
				return false;
			}
			
			if(!sale_price){
	            alert("상품 가격을 입력해주세요.");
	            $("input[name='sale_price']").focus();
	            return false;
	         }
			
	         if(!start_day){
	            alert("등록 시작일을 등록해주세요.");
	            $("input[name='start_day']").focus();
	            return false;
	         }
	         
	         if(!end_day){
	            alert("등록 종료일을 등록해주세요.");
	            $("input[name='end_day']").focus();
	            return false;
	         }
			
			 if($('input:checkbox[name="day"]:checked').length == 0){
				alert("요일을 선택해주세요.");
				return false;
			 }
		})
	});