
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>글쓰기창</title>

<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/default.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/common.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/content.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/button.css?ver=20.04.02" />
<script src="resources/DR0001JS/jquery-1.11.1.min.js"></script>
<script src="resources/DR0001JS/common.js"></script>
<script src="https://kit.fontawesome.com/79613ae794.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>


function readURL(input) {
    if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
        }
       reader.readAsDataURL(input.files[0]);
    }
}  
function backToList(obj){
  obj.action="${contextPath}/board/listArticles2.0.do";
  obj.submit();
}

var cnt=1;
function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
}  

</script>
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
					<p class="login_user"><span><strong>aaaa</strong> 님 반갑습니다.</span></p>
					<ul>
						<li><a href="#" class="tmenu tm02"><span>로그아웃</span></a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 상단메뉴 -->
		<div id="gnb">
			<h2 class="skip_info">상단메뉴</h2>
			<div class="innerwrap">
				<ul id="menu" class="menu">
					<li class="depth1-menu1"><a href="FN1001LS.html" class="on"><i class="fas fa-info-circle"></i> 기초정보</a>
						<ul>
							<li><a href="#">1-1</a></li>
							<li><a href="#">1-2</a></li>
						</ul>
					</li>
					<li class="depth1-menu2"><a href="FN1001VE.html"><i class="fas fa-user-edit"></i> 회원관리</a>
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
<title>글쓰기창</title>
</head>
<body>
<h1 style="text-align:center">글쓰기</h1>
  <form name="articleForm" method="post"  action="${contextPath}/board/addNewArticle.do"  enctype="multipart/form-data">
	<div id="container">
		<div class="innerwrap">
			<div class="page_tits">
				<h3>거래처관리</h3>
				<p class="path"><strong>현재위치 :</strong> <span>기준정보관리</span> <span>거래처관리</span></p>
			</div>
			<div id="content">
				<div class="section">
					<table class="tb tb_row">
					<colgroup>
						<col style="width:12%;" /><col style="width:auto;" /><col style="width:12%;" /><col style="width:38%;" />
					</colgroup>
					
										
						<tbody>
				
						<tr>
							<td width=30 align="center">
						      제목
						   </td>
							<td><input type="text" id="DT002_05" name="title" /></td>
					
						</tr>
						
		
						<tr width=30 align="center">
							<td>내용</td>
							<td colspan="3"><textarea id="cm_con" name="content" cols="90" rows="10"></textarea></td>
						</tr>
						<!-- <tr>
							<th>첨부파일</th>
							<td colspan="3">
								<div class="file_input">
									<input type="text" title="첨부파일" id="file_route" name="file_route" readonly />
									<label> 첨부파일
						
										
									</label>
							
								</div>
							</td>
						</tr> -->
						<tr>
						<td width=30 align="center">첨부파일</td>
						
						
						<div class="file_input">
						<td>
						<label> 
						 <input type="button" class="btns btn_bdr1 btn_mid" id="file_route" name="file_route" value="파일추가" onClick="fn_addFile()"/>
						</label>
						</div>
						</td>	
						</tr>
						
						<tr>
	      				<td colspan="4"><div id="d_file"></div></td>
	  					 </tr>
						</tbody>
					</table>
					<p class="btn_set">
						<input  type="submit" value="저장" class="btns btn_st1 btn_mid">
						<button type="button" class="btns btn_bdr1 btn_mid" onClick="backToList(this.form)">취소</button>
					<!-- 	<input type=button value="목록보기"onClick="backToList(this.form)" /> -->
					</p>		
				</div>
			</div>
		</div>
	</div>
	</form>
	<footer>Copyright(c)네임즈.All rights reserved.</footer>
</div>
</body>
</html>