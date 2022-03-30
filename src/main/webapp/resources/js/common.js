//바이트 제한
$(document).ready(function() {
	$(function() {
		$('.nickchk').each(function() {
			// count 정보 및 count 정보와 관련된 textarea/input 요소를 찾아내서 변수에 저장한다.
			var $maxcount = $('.maxcount', this);
			var $count = $('.count', this);
			var $input = $("#contents");

			// .text()가 문자열을 반환하기에 이 문자를 숫자로 만들기 위해 1을 곱한다.
			var maximumByte = $maxcount.text() * 1;
			// update 함수는 keyup, paste, input 이벤트에서 호출한다.
			var update = function() {
				
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
			$input.bind('input keyup keydown paste change', function() {
				setTimeout(update, 0)
			});
			update();
		});
	});
});

//글자 수 제한
function chkword(obj, maxByte) {

	var strValue = obj.value;
	var strLen = strValue.length;
	var totalByte = 0;
	var len = 0;
	var oneChar = "";
	var str2 = "";

	for (var i = 0; i < strLen; i++) {
		oneChar = strValue.charAt(i);
		if (escape(oneChar).length > 4) {
			totalByte += 3;
		} else {
			totalByte++;
		}

		// 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
		if (totalByte <= maxByte) {
			len = i + 1;
		}
	}

	// 넘어가는 글자는 자른다.
	if (totalByte > maxByte) {
		alert(maxByte + "자를 초과 입력 할 수 없습니다.");
		str2 = strValue.substr(0, len);
		obj.value = str2;
		chkword(obj, 4000);
	}
}