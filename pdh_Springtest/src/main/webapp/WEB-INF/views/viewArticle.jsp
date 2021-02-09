
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 



<!DOCTYPE html>
<html lang="ko">
<head>
<!--  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>  -->
 
   <script
 		src="https://code.jquery.com/jquery-3.5.0.min.js" 
		integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
 		crossorigin="anonymous"></script>
 
 
   <script type="text/javascript" >
   
 
   
   
 /*     function backToList(obj){
		//document.write(obj);
	    obj.action="${contextPath}/board/listArticles.do";
	    obj.submit();
     } */
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false; //활성화
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
		 //document.getElementById("tr_btn").style.display=false; //아마도 input=file 값을 하나 지워서 되는 것 같다.
	 }
	 
	 
	 function fn_modify_article(obj){ //frmArticle가 obj 안으로 들어감.
		 formElement.attr("action" , "${contextPath}/board/modArticle.do");
		 //obj.action="${contextPath}/board/modArticle.do";
		 //$("#frm").submit();
		formElement.submit();
		 //obj.submit();
	 }
	 
	 function fn_remove_article(url,articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","articleNO");
	     articleNOInput.setAttribute("value", articleNO);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 
	 }
	 
	 
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	        	 
	        	 var count = 0;
	        	 $('#i_imageFileName'+count).attr('src', e.target.result);
	        	 //$('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
	 
	
	 
 </script>





<style>
.selectimage {



}



</style>
<script>



</script>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>글보기</title>
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/default.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/common.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/content.css?ver=20.04.02" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/button.css?ver=20.04.02" />
<script src="resources/DR0001JS/jquery-1.11.1.min.js"></script>
<script src="resources/DR0001JS/common.js"></script>
<script src="https://kit.fontawesome.com/79613ae794.js" crossorigin="anonymous"></script>
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


<!--  frmArticle을 탄다. -->
 <form id="frm"  role="form" name="frmArticle" method="post"  action="${contextPath}/board/modArticle.do"  enctype="multipart/form-data">
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
						      글번호
						   </td>
						   <td >
						    <input type="text"  value="${article.articleNO }"  disabled />
						    <input type="hidden" name="articleNO" value="${article.articleNO}"  />
						    <input type="hidden" name="page" value="${p.page}"/>
						   </td>
						  </tr>
						<tr>
							<th scope="row">거래처명</th>
							<td><input type="text" id="i_title" name="title" value="${article.title}" disabled/></td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td colspan="3"><textarea id="i_content" name="content" cols="90" rows="10" disabled>${article.content}</textarea></td>
						</tr>
				<%-- 
						<tr>
							<th>첨부파일</th>
							<td colspan="3">
								<div class="file_input">
									<input type="text" title="첨부파일" id="file_route" name="file_route" readonly />
									<label> 첨부파일
										<input type="file" id="att_file" name="att_file" onChange="javascript:document.getElementById('file_route').value=this.value"/>
									</label>
								</div>
							</td>
						</tr> --%>
						</tbody>
						
		 <c:if test="${not empty imageFileList && imageFileList!='null' }">
                 <!-- 밖에다 써야함. -->
			   <c:set var="count" value="0"/>
	  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
		    <tr>
			    <td width="150" align="center" rowspan="2">
			          이미지${status.count }
			   </td>
			   <td>
			     <input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />	
			     <!-- 추가 --> 
			     <c:set var="aa" value="preview${count }"/>
			   <!--   <c:out value="${aa}" /> -->	
			     <!-- id="preview"에서 ${aa}로 바꿈 -->				     	     
			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="${aa}" /><br>
			    
			   </td>   
			  </tr>  
			  <tr>
			    <td>  
			    <!--  인자로  img 태그 id값도 보낸다. -->
			       <input  class="selectimage" type="file"  name="imageFileName" id="i_imageFileName" disabled   onchange="readURL(this, '${aa}');"   />
			    </td>
			 </tr>
			 <!--  추가 -->
			 <c:set var="count" value="${count +1 }" />
			 <c:out value="${count }" />
		</c:forEach>
 </c:if>

					</table>
					
					 <tr id="tr_btn_modify"  align="center" >
	  				 <td colspan="2"   >
	  				 <input id="modBtn" type=button value="수정반영하기">
	      			<!--  <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  > --> <!--  frmArticle: 전송하는 form의 name -->
           				<input type=button value="취소" onClick="backToList(frmArticle)">
	   				</td> 
					 </tr>
					<p class="btn_set">
						<button type="button" class="btns btn_st1 btn_mid">저장</button>
						<button type="button" id="list-btn" class="btns btn_bdr1 btn_mid">취소</button>
						
						 <tr id="tr_btn">
					<!--   <input type=button value="수정하기" onClick="fn_enable(this.form)">   -->
				  <input id="button1" type=button value="수정하기">  
				   	 <!-- <input id="list-btn" type=button value="리스트로 돌아가기"> -->
						  <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					    </tr>
					    
					</p>		
				</div>
			</div>
		</div>
	</div>
	
	<footer>Copyright(c)네임즈.All rights reserved.</footer>
</div>
</body>

</form>

<script>
$(function() {
	
	$("#button1").click(function(){ 
		//alert("버튼1을 누르셨습니다.");
		 document.getElementById("i_title").disabled=false; //활성화
		 document.getElementById("i_content").disabled=false;
	$(".selectimage").attr('disabled', false);
	//$("#frm").submit();

	//obj.submit();
	
	});
	
	
	//수정 클릭 이벤트 처리
	//var modifyBtn = document.getElementById("modBtn"); //vanila js
	var modifyBtn = $("#modBtn"); //jQuery에서 돔객체를 지목 하는 방법
	
	modifyBtn.click(function() { //클릭 했을때 생성되는 이벤트 처리
		console.log("수정 반영버튼이 클릭됨!");
		 //formElement.attr("action" , "${contextPath}/board/modArticle.do");//attr(속성 , 변경값 ) 태그의 내부 속성을 변경
		 //formElement.attr("method", "get"); 
		 $("#frm").submit();
	});
	 
	
	$("#list-btn").click(function () {
		console.log("목록 버튼이 클릭됨!"); //${contextPath}
		location.href='/board/listArticles2.0.do?page=${p.page}'
				+ '&countPerPage=${p.countPerPage}';
		
		
	});
	
	
	  var count = 0;
 	  
	  $("#i_imageFileName").click(function() {
		    
	
		 	//$("#i_imageFileName").attr('id', 'i_#imageFileName'+ count++);
		 	
		 	   var active = document.getElementById("i_imageFileName");
		 	   active.id = 'i_imageFileName' + count++;
		 	   
		 		$('#i_imageFileName').attr({
					'name' : 'imageFileName' + count++
					});
	  }); 	 
	  
	 
	  /* 	
	  	$(document).ready(function () {
			$('#i_imageFileName').click(function () {
				
			
				var count = 0;
				
				$('#i_imageFileName').attr({
				//'name' : 'imageFileName' + count++;
				'id'   : 'imageFileName' + conut++;
	 			});
			
			});
		}); 
		 */
		 
		
	
}); //end jQuery 


</script>



</html>