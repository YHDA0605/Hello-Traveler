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
    <link rel="stylesheet" href="resources/css/stylePost.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script>
	$(document).ready(function() {

		$(".submitBtn").click(function() {
			var title = $(".textBox1").val();
			var content = $(".textBox2").val();

			if(title == ""){
				alert("제목이 비어있습니다.");
				$(".textBox1").focus();
				return false;
			}
			if(content == ""){
				alert("내용이 비어있습니다.");
				$(".textBox2").focus();
				return false;
			}
			
		});
	})
    </script>
</head>

<body>
    <%@include file="/WEB-INF/views/common/header.jsp" %>
<form action="centerWtiteAction" method="post" enctype="multipart/form-data">
<input type="hidden" value="${CODE }" name="mem_code">
<input type="hidden" value="${UPDATE.post_code}" name="post_code">
<input type="hidden" value="${COMMON.common_code}" name="common_code">

    <div class="contents">
        <div class="WriteArticleInfo">
            <div class="borad_contentTitle">${COMMON.common_name}</div>
            <input class="boradBtn submitBtn" type="submit" value="등록">
        </div>
        <div class="contentBox">
            <div class="contentBoxTop">
                	 <input class="contentBox_text textBox1" name="post_title" type="text" value="${UPDATE.post_title}" placeholder="제목을 입력해주세요">
                	 <div class="inputArea">
					<label for="gdsImg">사진 : </label>
					<input type="file" name="file" id="gdsImg" multiple />
				<div class="select_img"><img src="" /></div>
				
				<script>
					$("#gdsImg").change(function(){
						if(this.files && this.files[0]) {
							var reader = new FileReader;
							reader.onload = function(data) {
								$(".select_img img").attr("src", data.target.result).width(1000).height(300);								
							}
							reader.readAsDataURL(this.files[0]);
						}
					});
				</script>
				</div>
                </div>
               
            </div>
                <div class="borad_content">
                    <div class="borad_content_Box">
                        <textarea class="borad_Write_comentWrite textBox2" name = "post_cont" cols="160" rows="50" placeholder="내용을 입력해주세요">${UPDATE.post_cont}</textarea>
                    </div>
                </div>

        </div>
        <div class="articleBtn">
            <a href="center?common_code=${COMMON.common_code}"> <input class="boradBtn" type="button"
				value="목록">
			</a>
        </div>
    </form>
        
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>