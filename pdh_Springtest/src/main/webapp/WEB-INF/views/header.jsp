
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>글목록</title>
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/default.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/common.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/content.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/button.css?ver=20.04.02" />
<script src="/resources/DR0001JS/jquery-1.11.1.min.js"></script>
<script src="/resources/DR0001JS/common.js"></script>
<script src="https://kit.fontawesome.com/79613ae794.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>


<style>

.on {

}


.page-active {
	background-color:#3232FF; 
}

.grid_item {
      
        display: inline-block; /*이부분에 성질을 inline-block로 바꿔줘서 가로배치를 해줬다.*/
        vertical-align: top; /*밑에 4px 여백을 없에는것*/
}

</style>


 <script
 		src="https://code.jquery.com/jquery-3.5.0.min.js" 
		integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
 		crossorigin="anonymous"></script>
        





<!--[if lt IE 9]>
<script src="../DR0001JS/html5shiv.js"></script>
<![endif]-->
</head>

<body>
<div id="adm_wrap">
	<header>
		<div class="head">
			<div class="innerwrap">
				<h1>타이틀</h1>
				<div class="top_menu">
					
					<ul>
					<c:if test="${empty member}">
					<p class="login_user"><span><a href="<c:url value='/member/loginForm.do'/>"><strong>로그인</a></strong></span></p>
					</c:if>
					</ul>
						
					<c:if test="${not empty member}">
					<p class="login_user"><span><strong>${member.id}</strong>님 반갑습니다.</span></p>
					</c:if>
					
					<c:if test="${not empty member}">
					<ul>
						<li><a href="/member/logout.do" onclick="return confirm('로그아웃 하시겠습니까?')" class="tmenu tm02"><span>로그아웃</span></a></li>
					</ul>
					</c:if>
				</div>
			</div>
		</div>
		<!-- 상단메뉴 -->
		<div id="gnb">
			<h2 class="skip_info">상단메뉴</h2>
			<div class="innerwrap">
				<ul id="menu" class="menu">
					<li class="depth1-menu1"><a href="${contextPath}/board/listArticles.do" class="on"><i class="fas fa-info-circle"></i> 게시판1.0</a>
						<ul>
							<li><a href="#">1-1</a></li>
							<li><a href="#">1-2</a></li>
						</ul>
					</li>
					<li class="depth1-menu2"><a href="${contextPath}/board/listArticles2.0.do"><i class="fas fa-user-edit"></i> 게시판2.0</a>
						<ul>
							<li><a href="#">회원리스트</a></li>
							<li><a href="#">회원리스트</a></li>
							<li><a href="#">회원리스트</a></li>
						</ul>
					</li>
					<li class="depth1-menu3"><a href="FN1001WR.html"><i class="fas fa-list-alt"></i> 대메뉴3</a></li>
					<li class="depth1-menu4"><a href="FN1002LS.html"><i class="fas fa-chart-line"></i> 대메뉴4</a>
						<ul>
							<li><a href="#">4-1</a></li>
							<li><a href="#">4-2</a></li>
							<li><a href="#">4-3</a></li>
						</ul>
					</li>
					<li class="depth1-menu4"><a href="calendar.html"><i class="fas fa-calendar-check"></i> 달력형</a></li>
					<li class="depth1-menu5"><a href="FN1001VE.html"><i class="fas fa-info-circle"></i> VE</a></li>
					<li class="depth1-menu6"><a href="FN1001WR.html"><i class="fas fa-info-circle"></i> WR</a></li>
					<li class="depth1-menu6"><a href="FN1002LS.html"><i class="fas fa-info-circle"></i> LS2</a></li>
				</ul>
			</div>
		</div>
		<!-- // 상단메뉴 -->
	</header>
<link rel="stylesheet" href="<c:url value='resources/DR0001JS/jquery-ui-1.12.1.custom/jquery-ui.css'/>">
<script src="../DR0001JS/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script>
$(document).ready(function(){
	// datePicker
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        showOn: "both", 
        buttonImage: "../DR0001IMG/common/icon_cal.png", 
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		yearSuffix: '년',
        buttonImageOnly: true 
    });
    $(function() {
			$( "#dateVal" ).datepicker({
				changeMonth: true,
				changeYear: true
			});
			$( "#dateValend" ).datepicker({
				changeMonth: true,
				changeYear: true
			});
    });
});

</script>
