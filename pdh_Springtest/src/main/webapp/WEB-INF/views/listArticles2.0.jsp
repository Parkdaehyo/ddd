
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%
  request.setCharacterEncoding("UTF-8");
%>  

<jsp:include page="header.jsp"/>
<script>

//아까 이 함수가 작동되지 않은 이유는 오타.
function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.") 
	    location.href=loginForm+'?action=/board/articleForm.do'; // ${contextPath}/member/loginForm.do로 이동하면서 action 파라미터값을 붙여서 전송.
	  }
	}

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
							<select id="condition" name="condition">
							
				<option value="title" ${param.condition == 'title' ? 'selected' : ''}>제목</option> <!--  SearchVO의 정보와 PageVO의 정보가 list.jsp에 함께 파라미터로 보내지기 때문에, 컨트롤러에서 	if(condition.equals("title")) 라면,  -->
				<option value="content" ${param.condition == 'content' ? 'selected' : ''}>내용</option>
				<option value="writer" ${param.condition == 'writer' ? 'selected' : ''}>작성자</option>
				<option value="titleContent" ${param.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
					
							</select>
							<input type="text" name="keyword" value="${param.keyword}" id="keywordInput" placeholder="검색어"/>
							<button type="button" id="searchBtn" class="btn bt_search"><span>검색</span></button>
							
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
							<th scope="col">제목</th>
							<th scope="col">첨부파일</th>
							<th scope="col">작성자</th>
							<th scope="col">이메일</th>
							<th scope="col">조회수</th>
							<th scope="col">등록일</th>
						</tr>
						</thead>
						<tbody>			
	
						<c:forEach var="article" items="${articlesList}" varStatus="articleNum" > 
						<c:set var="isFileExisted" value="false" scope="page"></c:set> 
						<c:forEach var="image" items="${ImageFileList}">
						<c:if test="${article.articleNO == image.articleNO and not empty image.imageFileName}"> 
							
								<c:set var="isFileExisted" value="true" scope="page"></c:set> 	<!-- var는 변수명, value는 변수값 -->
							</c:if>
							</c:forEach>
							
				 			<tr>
				 	
							<td><input type="checkbox" id="chk1" name="chk1" value="" /></td>
											
							<td>${article.articleNO}</td>
					<%-- 		<td class="tl"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td> --%>
							<td class="t1">
							<a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}&page=${pc.paging.page}&countPerPage=${pc.paging.countPerPage}">
								${article.title} 
									 </a></td>	
									 
					 	
					 	<c:choose>
					 	<c:when test="${isFileExisted eq true}">
							<td>Y</td>
						</c:when>
						<c:otherwise>
						<td></td>
						</c:otherwise>
						</c:choose> 
							
				<%-- 			<c:choose>
							<c:when test="${article.id == member.id}"> --%>
							<td>${article.id}</td> <!--  첨부파일이 있을때만 된다.. -->
						<%-- 	</c:when>
							<c:otherwise>
							<td></td>
							</c:otherwise>
							</c:choose>
								 --%>
							<%-- 	<td>${true}</td> --%>
					
						
					<%-- 		<td>${Y}</td>
							 --%>
							<td>${Z}</td>
							
							<td>${article.viewCnt}</td>

							<td><fmt:formatDate value="${article.writeDate}"
							pattern="yyyy-MM-dd"/></td>
								
							
						</tr>
								</c:forEach>
							
								
									
					</table>
					
				
					<!-- // list -->
					<!-- paging -->
					
					<!-- 페이징 처리 부분  -->
					
					<!--  처음으로 마지막으로는 옛날에 작업했던 컴퓨터에 있으니 참고해서 만들것. -->
					<!--  EditPlus 다운로드 -->
					
					
						<%-- 
					<a href="#" class="page_bt first">첫페이지</a>
					<a href="#" class="page_bt prev">이전 페이지 그룹</a>
					<!--  이전 버튼 --> <!--  조건부로 활성/비활성 여부를 결정. -->
					<c:if test="${pc.prev}"> <!--  이전버튼이 true일때만 등장. -->
					<li class="page-item"><a class="page_bt prev" href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pc.beginPage - 1)}'/>" 
						>이전</a>
					</li>
					</c:if>

				<!-- 페이지 버튼 -->
					<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
						<!-- 1이 시작값 end가 끝값 -->
						
				<!-- 
					<span class="on">
							<a href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pageNum)}'/>"
						 	class="on ${(pc.paging.page == pageNum) ? 'page-active' : ''}" 
							>${pageNum}</a>
						</span>
				 -->	
				
						<span class="on">
							<a href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pageNum)}'/>"
						 	>${pageNum}</a>
						</span>
						
						
					</c:forEach>
						

					<!--  다음 버튼 -->
					<c:if test="${pc.next}"> <!-- 이것도, next가 true일때만 등장. -->
					<li class="page_bt next"><a class="page-link" href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pc.endPage + 1)}'/>"
						>다음</a>
					</li>
					</c:if>
					 
					<a href="#" class="page_bt next">다음 페이지 그룹</a>
					<a href="#" class="page_bt last">마지막페이지</a>
				</div>
				--%>
			
	
					<div class="paging">
						<a href="#" class="page_bt first">첫페이지</a><a href="#" class="page_bt prev">이전 페이지 그룹</a>
						<p>
							<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
								<!-- 
									파라미터로 전달받은 페이지 번호와 forEach도는 변수의 값이 같으면 <span> 태그를 추가해주면 되고
									그렇지 않으면 그냥 <a> 태그만 뿌려주면 됨.
								 -->
								<c:choose>
									<c:when test="${pc.paging.page == pageNum}">
										<a href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pageNum)}'/>"><span class="on">${pageNum}</span></a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pageNum)}'/>">${pageNum}</a>
									</c:otherwise>
								</c:choose>
								<!-- <a href="<c:url value='/board/listArticles2.0.do${pc.makeURI(pageNum)}'/>"><span class="aa ${(pc.paging.page == pageNum) ? 'on' : ''}">${pageNum}</span></a>-->
							<!-- 	<a href="#">2</a>
								<a href="#">3</a>
								<a href="#">4</a>
								<a href="#">5</a>
								<a href="#">6</a>
								<a href="#">7</a>
								<a href="#">8</a>
								<a href="#">9</a>
								<a href="#">10</a> -->
							</c:forEach>
						</p>
						<a href="#" class="page_bt next">다음 페이지 그룹</a><a href="#" class="page_bt last">마지막페이지</a>
					</div>
	
	
		

					
				<!-- 	<div class="paging">
						<a href="#" class="page_bt first">첫페이지</a><a href="#" class="page_bt prev">이전 페이지 그룹</a>
						<p><span class="on">1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a></p>
						<a href="#" class="page_bt next">다음 페이지 그룹</a><a href="#" class="page_bt last">마지막페이지</a>
					</div> -->
					<div align="right">
						<p class="fr">
						<a href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p>글쓰기</p></a>
				<%-- 		<a href="<c:url value='/board/articleForm.do'/>">글쓰기</a> --%>
						</p>
						</div>
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
		
		location.href="/board/listArticles2.0.do?keyword=" + keyword
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



