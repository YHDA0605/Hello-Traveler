$(function(){
	
	//글쓰기버튼 눌렀을 때 입력 검사 여부
	$('.insert').click(function() {
		var name_value = $("input[name='trav_name']").val();
		var common_value = $("select[name='common_code']").val();
		var price_value = $("input[name='trav_price']").val();
		var nights_value = $("input[name='trav_nights']").val();
		var cont_value = $("textarea[name='trav_cont']").val();
      
		//입력 검사 여부
		if(!name_value){
			alert("여행상품명을 입력해주세요.");
			$("input[name='trav_name']").focus();
			return false;
		}
      
		if(!common_value){
			alert("여행지를 선택해주세요.");
			$("select[name='common_code']").focus();
			return false;
		}
      
		if(!price_value){
			alert("기준가격을 입력해주세요.");
			$("input[name='trav_price']").focus();
			return false;
		}
		
		if(!nights_value){
			alert("여행일수를 입력해주세요.");
			$("input[name='trav_nights']").focus();
			return false;
		}
      
		if(!cont_value){
			alert("여행소개글을 입력해주세요.");
			$("textarea[name='trav_cont']").focus();
			return false;
		}
	})
});

$(function() {
    // 질문유형을 선택한다.
    chnquestType('2', '42001');
 });
 
 function chnquestType(type , select) {
     
     $('#QnaType').empty();
     
     if (type == '2') {  // 국내관련
         $('#QnaType').append("<option value='42001' >서울</option>'");
         $('#QnaType').append("<option value='42002' >경기도</option>'");
         $('#QnaType').append("<option value='42003' >강원도</option>'");
         $('#QnaType').append("<option value='42004' >대전</option>'");
         $('#QnaType').append("<option value='42005' >충청도</option>'");
         $('#QnaType').append("<option value='42006' >대구</option>'");
         $('#QnaType').append("<option value='42007' >부산</option>'");
         $('#QnaType').append("<option value='42008' >경상도</option>'");
         $('#QnaType').append("<option value='42009' >광주</option>'");
         $('#QnaType').append("<option value='42010' >전라도</option>'");
         $('#QnaType').append("<option value='42011' >제주</option>'");
     } else if ( type == '3') {  // 아시아관련
         $('#QnaType').append("<option value='42101' >일본</option>'");
         $('#QnaType').append("<option value='42102' >중국</option>'");
         $('#QnaType').append("<option value='42103' >대만</option>'");
         $('#QnaType').append("<option value='42104' >홍콩</option>'");
         $('#QnaType').append("<option value='42105' >베트남</option>'");
         $('#QnaType').append("<option value='42106' >태국</option>'");
         $('#QnaType').append("<option value='42107' >필리핀</option>'");
         $('#QnaType').append("<option value='42108' >기타</option>'");
     }
     else if ( type == '4') {  // 유럽관련
         $('#QnaType').append("<option value='42201' >영국</option>'");
         $('#QnaType').append("<option value='42202' >프랑스</option>'");
         $('#QnaType').append("<option value='42203' >이탈리아</option>'");
         $('#QnaType').append("<option value='42204' >독일</option>'");
         $('#QnaType').append("<option value='42205' >스페인</option>'");
         $('#QnaType').append("<option value='42206' >러시아</option>'");
         $('#QnaType').append("<option value='42207' >스웨덴</option>'");
         $('#QnaType').append("<option value='42208' >스위스</option>'");
         $('#QnaType').append("<option value='42209' >기타</option>'");
     }
     else if ( type == '5') {  // 아메리카관련
         $('#QnaType').append("<option value='42301' >미국</option>'");
         $('#QnaType').append("<option value='42302' >캐나다</option>'");
         $('#QnaType').append("<option value='42303' >멕시코</option>'");
         $('#QnaType').append("<option value='42304' >브라질</option>'");
         $('#QnaType').append("<option value='42305' >아르헨티나</option>'");
         $('#QnaType').append("<option value='42306' >기타</option>'");
     }
     else if ( type == '6') {  // 오세아니아관련
         $('#QnaType').append("<option value='42401' >호주</option>'");
         $('#QnaType').append("<option value='42402' >뉴질랜드</option>'");
     }
     else if ( type == '7') {  // 아프리카관련
         $('#QnaType').append("<option value='42501' >이집트</option>'");
         $('#QnaType').append("<option value='42502' >콩고</option>'");
         $('#QnaType').append("<option value='42503' >남아공</option>'");
         $('#QnaType').append("<option value='42504' >에티오피아</option>'");
         $('#QnaType').append("<option value='42505' >사우디아라비아</option>'");
         $('#QnaType').append("<option value='42506' >기타</option>'");
     }
     document.getElementById("QnaType").style.display = "";
     
     if ($.trim(select) != "") {
         $('#select1').val(type);
         $('#QnaType').val(select);
     }
 }