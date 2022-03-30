<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/styleProduct.css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/travelWrite.js"></script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="tourproduct_container">
		<div class="tourproduct_body">
			<form action="travelWriteAction" method="post" enctype="multipart/form-data">
				<div>
					<div class="tourproduct_header">
						<div class="common_name">여행상품 글쓰기</div>
						<div>
							<input class="boradBtn tourproduct_Btn insert" type="submit" value="등록" />
						</div>
					</div>

					<input type="hidden" name="mem_code" value="${CODE}"> 
					<input type="hidden" name="com_code" value="${trav.com_code}"> 
					<input type="hidden" name="trav_code" value="${trav.trav_code}">
					
					<input class="tourproduct_title" name="trav_name" type="text" value="${trav.trav_name}" placeholder="여행상품명을 입력해주세요" onkeyup="chkword(this, 120)"/><br />
					
					<table class="tourproduct_table">
						<tr>
							<td colspan="2"><label>나라</label></td>
							<td>
								<select name="questType" id="select1" onChange="chnquestType(this.value)">
									<option value="1">선택</option>
									<option value="2">한국</option>
									<option value="3">아시아</option>
									<option value="4">유럽</option>
									<option value="5">아메리카</option>
									<option value="6">오세아니아</option>
									<option value="7">아프리카</option>
								</select>
							</td>
							<td>
								<select id="QnaType" name="common_code"></select>
							</td>
						</tr>
						<tr>
							<td><label>상품 기준가격</label></td>
							<td><input name="trav_price" class="box" type="number"  min="1" max="9999999" value="${trav.trav_price}" />원</td>
							<td><label>여행 일수</label></td>
							<td><input name="trav_nights" class="box" type="number"  min="1" max="99" value="${trav.trav_nights}" />박</td>
						</tr>
					</table>
					
					<div class="travel_content" id="write">
						<textarea class="trav_desc" name="trav_desc" cols="90" rows="10" placeholder="상품에 대한 간단한 정보를 입력해주세요" onkeyup="chkword(this, 4000)">${trav.trav_desc}</textarea>
						<textarea class="trav_cont" name="trav_cont" cols="90" placeholder="*옵션'기타 '선택시 반드시 상세설명을 넣어주세요*" onkeyup="chkword(this, 4000)">${trav.trav_cont}</textarea>
					</div>
					
					<div class="inputArea">
						<label for="gdsImg">사진 : </label>
						<input type="file" name="file" id="gdsImg" multiple />
					<div class="select_img"><img src="" /></div>
				
				<script>
					$("#gdsImg").change(function(){
						if(this.files && this.files[0]) {
							var reader = new FileReader;
							reader.onload = function(data) {
								$(".select_img img").attr("src", data.target.result).width(300).height(300);								
							}
							reader.readAsDataURL(this.files[0]);
						}
					});
				</script>
				</div>
					
					<div class="back_Btn">
						<div>
							<a href="mypagetravel?memCode=${CODE}"> 
								<input class="boradBtn tourproduct_Btn" type="button" value="목록으로" />
							</a>
						</div>
					
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>