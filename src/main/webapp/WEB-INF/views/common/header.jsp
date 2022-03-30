<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
   
	$(".logOut_Btn").click(function(){
        alert("로그아웃 했습니다.");
    });
	
    if("${MSG}"=="회원 탈퇴가 완료되었습니다.\n그동안 이용해주셔서 감사합니다."){
    	alert("${MSG}");
    };
});

</script>
</head>
<body>
   <div id="headerContainer">
      <div id="header">
         <div id="topHeader">
            <div>
               <a href="/helloTraveler"><img class="logo" src="resources/images/logo.png" alt="Logo"></a>
            </div>
            <c:set var="session" scope="session" value="${CODE}"></c:set>
            <c:if test="${session == null}">
               <div id="headerBtn">
                  <a href="login">로그인</a> <a href="regist">회원가입</a>
               </div>
            </c:if>
            <c:if test="${session != null}">
               <div id="headerBtn">
	                <c:if test="${NOREAD == 0 || NOREAD == null}">
	                	<a href="to_message?to_mem_code=${CODE}"><img class="messageImg" src="resources/images/message.png"></a>
	                </c:if>
	                <c:if test="${NOREAD > 0}">
	                	<a href="to_message?to_mem_code=${CODE}"><img class="messageImg" src="resources/images/newMessage.png"></a>
	                </c:if>
	                <span class="mem_nick_header">${NICK}님 환영합니다!</span>
                    <a href="mypage">마이페이지</a> 
                    <a href="logOut" class="logOut_Btn">로그아웃</a>
               </div>
            </c:if>
         </div>
         <div id="nav" class="main_menu menu">
            <ul class="list_gnb">
            <li class="list">
            	<a href="/helloTraveler"><button>홈</button></a>
            </li>
            <li class="list"><button>커뮤니티</button>
                <div id="mega-menu1">
                    <ul class="list_lnb">
                        <li class="drop"><a href="post?common_code=21001">자유게시판</a>
                        </li>
                        <li class="drop"><a href="post?common_code=21002">유머게시판</a>
                        </li>
                        <li class="drop"><a href="post?common_code=22000">국내</a>
                            <div class="sub_drop">
                                  <ul class="sub">
                                    <li><a href="post?common_code=22001">서울</a></li>
                                    <li><a href="post?common_code=22002">경기도</a></li>
                                    <li><a href="post?common_code=22003">강원도</a></li>
                                    <li><a href="post?common_code=22004">대전</a></li>
                                    <li><a href="post?common_code=22005">충청도</a></li> 
                                    <li><a href="post?common_code=22006">대구</a></li> 
                                    <li><a href="post?common_code=22007">부산</a></li> 
                                    <li><a href="post?common_code=22008">경상도</a></li> 
                                    <li><a href="post?common_code=22009">광주</a></li> 
                                    <li><a href="post?common_code=22010">전라도</a></li> 
                                    <li><a href="post?common_code=22011">제주</a></li> 
                                </ul>
                             </div>
                         </li>
                        <li class="drop"><a href="post?common_code=22100">아시아</a>
                            <div class="sub_drop">
                                <ul class="sub">
                                    <li><a href="post?common_code=22101">일본</a></li>
                                    <li><a href="post?common_code=22102">중국</a></li>
                                    <li><a href="post?common_code=22103">대만</a></li>
                                    <li><a href="post?common_code=22104">홍콩</a></li> 
                                    <li><a href="post?common_code=22105">베트남</a></li> 
                                    <li><a href="post?common_code=22106">태국</a></li> 
                                    <li><a href="post?common_code=22107">필리핀</a></li> 
                                    <li><a href="post?common_code=22108">기타</a></li> 
                                </ul>
                            </div>
                        </li>
                        <li class="drop"><a href="post?common_code=22200">유럽</a>
                            <div class="sub_drop">
                               <ul class="sub">
                                 <li><a href="post?common_code=22201">영국</a></li>
                                 <li><a href="post?common_code=22202">프랑스</a></li>
                                 <li><a href="post?common_code=22203">이탈리아</a></li>
                                 <li><a href="post?common_code=22204">독일</a></li> 
                                 <li><a href="post?common_code=22205">스페인</a></li> 
                                 <li><a href="post?common_code=22206">러시아</a></li> 
                                 <li><a href="post?common_code=22207">스웨덴</a></li> 
                                 <li><a href="post?common_code=22208">스위스</a></li> 
                                 <li><a href="post?common_code=22209">기타</a></li> 
                             </ul>
                          </div>
                      </li>
                      <li class="drop"><a href="post?common_code=22300">아메리카</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="post?common_code=22301">미국</a></li>
                             <li><a href="post?common_code=22302">케나다</a></li>
                             <li><a href="post?common_code=22303">멕시코</a></li>
                             <li><a href="post?common_code=22304">브라질</a></li> 
                             <li><a href="post?common_code=22305">아르헨티나</a></li> 
                             <li><a href="post?common_code=22306">기타</a></li> 
                            </ul>
                        </div> 
                      </li>
                      <li class="drop"><a href="post?common_code=22400">오세아니아</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="post?common_code=22401">호주</a></li>
                             <li><a href="post?common_code=22402">뉴질랜드</a></li>
                         </ul>
                      </div>
                      </li>
                      <li class="drop"><a href="post?common_code=22500">아프리카</a>
                        <div class="sub_drop">
                            <ul class="sub">
                            <li><a href="post?common_code=22501">이집트</a></li>
                            <li><a href="post?common_code=22502">콩고</a></li>
                            <li><a href="post?common_code=22503">남아공</a></li>
                            <li><a href="post?common_code=22504">에티오피아</a></li> 
                            <li><a href="post?common_code=22505">사우디아라비아</a></li> 
                            <li><a href="post?common_code=22500">기타</a></li> 
                            </ul>
                        </div>
                      </li>   
                    </ul>
                </div>
            </li>
            
            
            <li class="list"><button>모집</button>
                <div id="mega-menu2">
                    <ul class="list_lnb">
                    <li class="drop"><a href="recruit?common_code=32000">국내</a>
                            <div class="sub_drop">
                                  <ul class="sub">
                                    <li><a href="recruit?common_code=32001">서울</a></li>
                                    <li><a href="recruit?common_code=32002">경기도</a></li>
                                    <li><a href="recruit?common_code=32003">강원도</a></li>
                                    <li><a href="recruit?common_code=32004">대전</a></li>
                                    <li><a href="recruit?common_code=32005">충청도</a></li> 
                                    <li><a href="recruit?common_code=32006">대구</a></li> 
                                    <li><a href="recruit?common_code=32007">부산</a></li> 
                                    <li><a href="recruit?common_code=32008">경상도</a></li> 
                                    <li><a href="recruit?common_code=32009">광주</a></li> 
                                    <li><a href="recruit?common_code=32010">전라도</a></li> 
                                    <li><a href="recruit?common_code=32011">제주</a></li> 
                                </ul>
                             </div>
                         </li>
                        <li class="drop"><a href="recruit?common_code=32100">아시아</a>
                            <div class="sub_drop">
                                <ul class="sub">
                                    <li><a href="recruit?common_code=32101">일본</a></li>
                                    <li><a href="recruit?common_code=32102">중국</a></li>
                                    <li><a href="recruit?common_code=32103">대만</a></li>
                                    <li><a href="recruit?common_code=32104">홍콩</a></li> 
                                    <li><a href="recruit?common_code=32105">베트남</a></li> 
                                    <li><a href="recruit?common_code=32106">태국</a></li> 
                                    <li><a href="recruit?common_code=32107">필리핀</a></li> 
                                    <li><a href="recruit?common_code=32108">기타</a></li> 
                                </ul>
                            </div>
                        </li>
                        <li class="drop"><a href="recruit?common_code=32200">유럽</a>
                            <div class="sub_drop">
                               <ul class="sub">
                                 <li><a href="recruit?common_code=32201">영국</a></li>
                                 <li><a href="recruit?common_code=32202">프랑스</a></li>
                                 <li><a href="recruit?common_code=32203">이탈리아</a></li>
                                 <li><a href="recruit?common_code=32204">독일</a></li> 
                                 <li><a href="recruit?common_code=32205">스페인</a></li> 
                                 <li><a href="recruit?common_code=32206">러시아</a></li> 
                                 <li><a href="recruit?common_code=32207">스웨덴</a></li> 
                                 <li><a href="recruit?common_code=32208">스위스</a></li> 
                                 <li><a href="recruit?common_code=32209">기타</a></li> 
                             </ul>
                          </div>
                      </li>
                      <li class="drop"><a href="recruit?common_code=32300">아메리카</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="recruit?common_code=32301">미국</a></li>
                             <li><a href="recruit?common_code=32302">케나다</a></li>
                             <li><a href="recruit?common_code=32303">멕시코</a></li>
                             <li><a href="recruit?common_code=32304">브라질</a></li> 
                             <li><a href="recruit?common_code=32305">아르헨티나</a></li> 
                             <li><a href="recruit?common_code=32306">기타</a></li> 
                            </ul>
                        </div> 
                      </li>
                      <li class="drop"><a href="recruit?common_code=32400">오세아니아</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="recruit?common_code=32401">호주</a></li>
                             <li><a href="recruit?common_code=32402">뉴질랜드</a></li>
                         </ul>
                      </div>
                      </li>
                      <li class="drop"><a href="recruit?common_code=32500">아프리카</a>
                        <div class="sub_drop">
                            <ul class="sub">
                            <li><a href="recruit?common_code=32501">이집트</a></li>
                            <li><a href="recruit?common_code=32502">콩고</a></li>
                            <li><a href="recruit?common_code=32503">남아공</a></li>
                            <li><a href="recruit?common_code=32504">에티오피아</a></li> 
                            <li><a href="recruit?common_code=32505">사우디아라비아</a></li> 
                            <li><a href="recruit?common_code=32506">기타</a></li> 
                            </ul>
                        </div>
                      </li>   
                    </ul>
                </div>
            </li>
            <li class="list"><button>여행상품</button>
                <div id="mega-menu2">
                    <ul class="list_lnb">
                       <li class="drop"><a href="travel?common_code=42000">국내</a>
                            <div class="sub_drop">
                                  <ul class="sub">
                                    <li><a href="travel?common_code=42001">서울</a></li>
                                    <li><a href="travel?common_code=42002">경기도</a></li>
                                    <li><a href="travel?common_code=42003">강원도</a></li>
                                    <li><a href="travel?common_code=42004">대전</a></li>
                                    <li><a href="travel?common_code=42005">충청도</a></li> 
                                    <li><a href="travel?common_code=42006">대구</a></li> 
                                    <li><a href="travel?common_code=42007">부산</a></li> 
                                    <li><a href="travel?common_code=42008">경상도</a></li> 
                                    <li><a href="travel?common_code=42009">광주</a></li> 
                                    <li><a href="travel?common_code=42010">전라도</a></li> 
                                    <li><a href="travel?common_code=42011">제주</a></li> 
                                </ul>
                             </div>
                         </li>
                        <li class="drop"><a href="travel?common_code=42100">아시아</a>
                            <div class="sub_drop">
                                <ul class="sub">
                                    <li><a href="travel?common_code=42101">일본</a></li>
                                    <li><a href="travel?common_code=42102">중국</a></li>
                                    <li><a href="travel?common_code=42103">대만</a></li>
                                    <li><a href="travel?common_code=42104">홍콩</a></li> 
                                    <li><a href="travel?common_code=42105">베트남</a></li> 
                                    <li><a href="travel?common_code=42106">태국</a></li> 
                                    <li><a href="travel?common_code=42107">필리핀</a></li> 
                                    <li><a href="travel?common_code=42108">기타</a></li> 
                                </ul>
                            </div>
                        </li>
                        <li class="drop"><a href="travel?common_code=42200">유럽</a>
                            <div class="sub_drop">
                               <ul class="sub">
                                 <li><a href="travel?common_code=42201">영국</a></li>
                                 <li><a href="travel?common_code=42202">프랑스</a></li>
                                 <li><a href="travel?common_code=42203">이탈리아</a></li>
                                 <li><a href="travel?common_code=42204">독일</a></li> 
                                 <li><a href="travel?common_code=42205">스페인</a></li> 
                                 <li><a href="travel?common_code=42206">러시아</a></li> 
                                 <li><a href="travel?common_code=42207">스웨덴</a></li> 
                                 <li><a href="travel?common_code=42208">스위스</a></li> 
                                 <li><a href="travel?common_code=42209">기타</a></li> 
                             </ul>
                          </div>
                      </li>
                      <li class="drop"><a href="travel?common_code=42300">아메리카</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="travel?common_code=42301">미국</a></li>
                             <li><a href="travel?common_code=42302">케나다</a></li>
                             <li><a href="travel?common_code=42303">멕시코</a></li>
                             <li><a href="travel?common_code=42304">브라질</a></li> 
                             <li><a href="travel?common_code=42305">아르헨티나</a></li> 
                             <li><a href="travel?common_code=42306">기타</a></li> 
                            </ul>
                        </div> 
                      </li>
                      <li class="drop"><a href="travel?common_code=42400">오세아니아</a>
                        <div class="sub_drop">
                           <ul class="sub">
                             <li><a href="travel?common_code=42401">호주</a></li>
                             <li><a href="travel?common_code=42402">뉴질랜드</a></li>
                         </ul>
                      </div>
                      </li>
                      <li class="drop"><a href="travel?common_code=42500">아프리카</a>
                        <div class="sub_drop">
                            <ul class="sub">
                            <li><a href="travel?common_code=42501">이집트</a></li>
                            <li><a href="travel?common_code=42502">콩고</a></li>
                            <li><a href="travel?common_code=42503">남아공</a></li>
                            <li><a href="travel?common_code=42504">에티오피아</a></li> 
                            <li><a href="travel?common_code=42505">사우디아라비아</a></li> 
                            <li><a href="travel?common_code=42506">기타</a></li> 
                            </ul>
                        </div>
                      </li>   
                    </ul>
                </div>
            </li>
            <li class="list"><button>고객센터</button>
                <div id="mega-menu3">
                    <ul class="list_lnb">
                        <li class="drop"><a href="center?common_code=50001">공지사항</a>
                        <li class="drop"><a href="event?common_code=50002">이벤트</a>
                        <li class="drop"><a href="center?common_code=50003">Q&amp;A</a>
                    </ul>
                </div>
            </li>
            <c:if test="${COMMON_CODE < 10003}">
            	<li>
					<a href="adminMem"><button>관리자 페이지</button></a> 
            	</li>
            </c:if>   
        </ul>
         </div>
      </div>
   </div>
</body>