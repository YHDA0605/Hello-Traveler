<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/styleRecruit.css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script>
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
    		
    		$('.deadline').attr("min", today);
    		
    		$('.deadline').change(function() {
    			$('.start_date').val('');
				$('.start_date').attr("min", $('.deadline').val());
			});
    		
    		$('.start_date').click(function() {
				if(!$('.deadline').val()){
					alert("모집 기한을 먼저 입력해주세요");
					return false;
				}
				$('.start_date').change(function() {
					$('.end_date').val('');
					$('.end_date').attr("min", $('.start_date').val());
				});
			});
    		
    		$('.end_date').click(function() {
				if(!$('.start_date').val()){
					alert("시작 날짜를 먼저 입력해주세요");
					return false;
				}
			});
    		
			$('.recruit_Btn').click(function() {
				var recu_title = $(".recu_title").val();
				var max_num = $('.max_num').val();
				var deadline = $('.deadline').val();
				var start_date = $('.start_date').val();
				var end_date = $('.end_date').val();
				var recu_cont = $('.recu_cont').val();
				var coomon_code = $('.common_code option:selected').val();
				
				if(recu_title == ""){
					alert("제목이 비어있습니다.");
					$('.recu_title').focus();
					return false;
				}
				
				if(coomon_code == ""){
					alert("나라/지역을 선택해주세요.");
					$('.common_code').focus();
					return false;
				}
				
				if(max_num == ""){
					alert("모집 인원을 확인해주세요.");
					$('.max_num').focus();
					return false;
				}
				
				if(deadline == ""){
					alert("모집 마감일을 확인해주세요.");
					$('.deadline').focus();
					return false;
				}
				
				if(start_date == ""){
					alert("여행 기간을 확인해주세요.");
					$('.start_date').focus();
					return false;
				}
				
				if(end_date == ""){
					alert("여행 기간을 확인해주세요.");
					$('.end_date').focus();
					return false;
				}
				
				if(recu_cont == ""){
					alert("내용이 비어있습니다.");
					$('.recu_cont').focus();
					return false;
				}
				
			});
		});
    </script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="recruit_container">
		<div class="recruit_body">
			<form action="recruitWriteAction" method="post">
				<div class="recruit_top">
					<div class="common_name">모집 글쓰기</div>
					<input class="recruit_Btn btn" type="submit" value="등록" />
				</div>
				<input type="hidden" name="mem_code" value="${CODE}">
				<input type="hidden" name="recu_code" value="${recu.recu_code }">
				<input class="recruit_title recu_title" name="recu_title" type="text" placeholder="제목을 입력해주세요" value="${recu.recu_title }" onkeyup="chkword(this, 120)"/><br />
				<table class="recruit_table">
					<tr>
						<td><label>나라/도시</label></td>
						<td width="145px">
							${common.common_name}
						</td>
						<td>
							<select name="common_code" class="common_code">
								<option value="" selected disabled hidden="hidden">국가/지역</option>
								<c:forEach var="commons" items="${common_list }">
									<option value="${commons.common_code }">${commons.common_name }</option>
								</c:forEach>
							</select>
						</td>
						<td><label>카테고리</label></td>
						<td>
							<select>
								<option value="Korea">먹방투어</option>
							</select>
						</td>
						
					</tr>
					<tr>
						<td><label>모집 인원</label></td>
						<td colspan="2">
							<input type="number" min="1" max="99" class="max_num" name="recu_max_n" style="width: 50px; height: 20px;" value="${recu.recu_max_n }"/>명
						</td>
						<td>
							모집마감
						</td>
						<td>
							<input class="deadline" type="date" name="recu_deadline" value="${recu.recu_deadline }"/>
						</td>
					</tr>
					<tr>
						<td><label>여행기간</label></td>
						<td colspan="4">
							<input class="start_date" type="date" name="recu_start_date" value="${recu.recu_start_date }" />~
							<input class="end_date" type="date" name="recu_end_date" value="${recu.recu_end_date }"/>
						</td>
					</tr>
				</table>
				<div class="recruit_content">
					<textarea class="recu_cont" name="recu_cont" placeholder="내용을 입력해주세요."  onkeyup="chkword(this, 4000)">${recu.recu_cont }</textarea>
				</div>
				
				<div class="back_Btn">
					<input class="recruit_Btn btn" type="button" value="목록으로" />
				</div>
			</form>
			
		</div>
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
