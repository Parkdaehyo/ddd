
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
<script src="resources/DR0001JS/jquery-1.11.1.min.js"></script>
<script src="resources/DR0001JS/common.js"></script>
<script src="https://kit.fontawesome.com/79613ae794.js" crossorigin="anonymous"></script>

<style>
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
	<div id="container">
		<div class="innerwrap">
			<div class="page_tits">
				<h3>거래처관리</h3>
				<p class="path"><strong>현재위치 :</strong> <span>기준정보관리</span> <span>거래처관리</span></p>
			</div>
			<div id="content">
				<div class="section">
					<!-- 검색 영역 -->
					<div class="search_box">
					<form name="Frm" id="Frm" method="post">
						<div class="sch_group fl">
							<select id="condition" class="form-control" name="condition">
							
				<option value="title" ${param.condition == 'title' ? 'selected' : ''}>제목</option> <!--  SearchVO의 정보와 PageVO의 정보가 list.jsp에 함께 파라미터로 보내지기 때문에, 컨트롤러에서 	if(condition.equals("title")) 라면,  -->
				<option value="content" ${param.condition == 'content' ? 'selected' : ''}>내용</option>
				<option value="writer" ${param.condition == 'writer' ? 'selected' : ''}>작성자</option>
				<option value="titleContent" ${param.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
							<!-- 	<option value="total">전체검색</option>
								<option value="title">거래처명</option>
							    <option value="content">내용</option>
								<option value="">거래처구분</option>
								<option value="">담당자명</option>
								<option value="">국가명</option> -->
							</select>
							<input type="text" name="keyword" value="${param.keyword}" id="keywordInput" placeholder="검색어"/>
							<input type="button" id="searchBtn"  value="검색">
							
						</div>
						<p class="fr">
							<button type="button" class="btns primary"><span class="bt_icon bt_excel">엑셀다운로드</span></button>
							<button type="button" class="btns primary"><span class="bt_icon icons bt_print">인쇄</span></button>
							<button type="button" class="btns primary" onclick="popup_win('../popup/popup.html','pop1','600','750','yes');return false;"><span class="bt_icon icons bt_print">새창 팝업</span></button>
						</p>
					</form>
					</div>
					<!-- // 검색 영역 -->
					<!-- list -->
					<table class="tb tb_col">
					<colgroup>
						<col style="width:50px;" /><col style="width:6%;" /><col style="width:auto;" /><col style="width:10%;" /><col style="width:10%;" /><col style="width:15%;" /><col style="width:10%;" /><col style="width:10%;" />
					</colgroup>
						<thead>
						<tr>
							<th scope="col"><input type="checkbox" id="chkall" name="chkall" value="" /></th>
							<th scope="col">번호</th>
							<th scope="col">회사명</th>
							<th scope="col">전화번호</th>
							<th scope="col">핸드폰번호</th>
							<th scope="col">이메일</th>
							<th scope="col">사용여부</th>
				
							<th scope="col">등록일</th>
						</tr>
						</thead>
						<tbody>
							
						<c:forEach  var="article" items="${articlesList}" varStatus="articleNum" >
				 		<tr>
							<td><input type="checkbox" id="chk1" name="chk1" value="" /></td>
							<td>${article.articleNO}</td>
					<%-- 		<td class="tl"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td> --%>
							<td class="t1">
							<a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}${param.page == null ? pc.makeURI(1) : pc.makeURI(param.page)}">
								${article.title}
									 </a></td>	 
							
							<td>032-1234-1234</td>
							<td>010-1234-1234</td>
							<td>webmaster@admin.com</td>
							<td><span class="btns btn_bdr1 btn_xs btnW40">사용</span></td>
							<td>${article.writeDate}</td>
						</tr>
					<!-- 	<tr>
							<td><input type="checkbox" id="chk2" name="chk2" value="" /></td>
							<td>999</td>
							<td class="tl"><a href="FN1001VE.html">우리회사</a></td>
							<td>032-1234-1234</td>
							<td>010-1234-1234</td>
							<td>webmaster@admin.com</td>
							<td><span class="btns btn_bdr2 btn_xs btnW40">미사용</span></td>
							<td>2020-04-07</td>
						</tr>
						</tbody>  -->
					</c:forEach>
							
					</table>
					<!-- // list -->
					<!-- paging -->
					
					<!-- 페이징 처리 부분  -->
					
					<div align="center" >
					<!--  이전 버튼 --> <!--  조건부로 활성/비활성 여부를 결정. -->
					<c:if test="${pc.prev}"> <!--  이전버튼이 true일때만 등장. -->
					<li class="page-item"><a class="page-link" href="<c:url value='/board/listArticles.do${pc.makeURI(pc.beginPage - 1)}'/>" 
						style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
					</li>
					</c:if>

					<!-- 페이지 버튼 -->
					<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
						<!-- 1이 시작값 end가 끝값 -->
						<li class="grid_item"><a
							href="<c:url value='/board/listArticles.do${pc.makeURI(pageNum)}'/>"
							class="page-link ${(pc.paging.page == pageNum) ? 'page-active' : ''}" 
							style="margin-top: 0; height: 40px; color: BLACK; border: 1px solid pink;">${pageNum}</a>
						</li>
					</c:forEach>

					<!--  다음 버튼 -->
					<c:if test="${pc.next}"> <!-- 이것도, next가 true일때만 등장. -->
					<li class="page-item"><a class="page-link" href="<c:url value='/board/listArticles.do${pc.makeURI(pc.endPage + 1)}'/>"
						style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
					</li>
					</c:if>
				</div>
				
					<!-- 검색 버튼 -->
	
	
		

					
				<!-- 	<div class="paging">
						<a href="#" class="page_bt first">첫페이지</a><a href="#" class="page_bt prev">이전 페이지 그룹</a>
						<p><span class="on">1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a></p>
						<a href="#" class="page_bt next">다음 페이지 그룹</a><a href="#" class="page_bt last">마지막페이지</a>
					</div> -->
						<p class="fr">
						<a href="<c:url value='/board/articleForm.do'/>">글쓰기</a>
						</p>
					<!-- // paging -->		
				</div>
			</div>
		</div>
	</div>

	
	<footer>Copyright(c)네임즈.All rights reserved.</footer>
</div>
</body>


<script>

//start jQuery
$(function() {

	//검색 버튼 이벤트 처리
	$("#searchBtn").click(function() {
		console.log("검색버튼이 클릭됨!");
		const keyword = $("#keywordInput").val(); //id == keywordInput == input="text"의 주소값 keyword는 mapper.xml에 기록된 파라미터 값. 파라미터에 기록된 값들을 한개씩 읽겠다.
		console.log("검색어: " + keyword)
		
		const condition = $("#condition option:selected").val(); //97번: id가 condition인데, option에 선택된애들의 value값을 읽어라. //SearchVO를 받기 때문에 멤버변수를 쓸 수 있다.
		console.log("검색 조건: " + condition);
		
		location.href="/board/listArticles.do?keyword=" + keyword
				+"&condition=" + condition; //검색할때  $("#condition option:selected").val(); 에 의해서 각각의 value값들을 묻혀서 전송함.
		
	});
	
	//엔터키 입력 이벤트
	//키 코드가 13이면 클릭버튼을 활성화 시켜라.
	$("#keywordInput").keydown(function (key) {
		if(key.keyCode == 13) { //키가 13이면 실행(엔터는 13)
			$("#searchBtn").click();
		}
		
	});
	

}); //end jQuery
</script>


</html>



