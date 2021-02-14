
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

<jsp:include page="header.jsp"/>

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
			     <input  type= "hidden" name="originalFileName" value="${item.imageFileName }" />	
			     <!-- 추가 --> 
			     <c:set var="aa" value="preview${count }"/>
			   <!--   <c:out value="${aa}" /> -->	
			     <!-- id="preview"에서 ${aa}로 바꿈 -->				     	     
			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="${aa}" /><br>
			<%--     	    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="${aa}" /><br> --%>
			   </td>   
			  </tr>  
			  <tr>
			    <td>  
			    <!--  인자로  img 태그 id값도 보낸다. --> <!-- 여기선 imageFileName이 board_type의 역할을 한다. -->
			       <input  class="selectimage" type="file"  name="imageFileName" id="i_imageFileName" disabled   onchange="readURL(this, '${aa}');"   />
			    </td>
			 </tr>
			 <!--  추가 -->
			 <c:set var="count" value="${count +1 }" />
			 <c:out value="${count }" />
		</c:forEach>
 </c:if>

					</table>
					
					 <tr id="tr_btn_modify"  align="center">
	  				 <td colspan="2">
	  				 <input id="modBtn" type=button value="수정반영하기">
	      			<!--  <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  > --> <!--  frmArticle: 전송하는 form의 name -->
           				<input type=button value="취소" onClick="backToList(frmArticle)">
	   				</td> 
					 </tr>
					<p class="btn_set">
						<button type="button" class="btns btn_st1 btn_mid">저장</button>
						<button type="button" id="list-btn" class="btns btn_bdr1 btn_mid">취소</button>
						
						
				<!-- 
				로그인하면 세션에 사용자 아이디가 저장되니, 글 상세창에서 해당글 작성자 아이디와 비교해서 같으면 표시되게 하면 됩니다. 
				쉬운 가능입니다.
				 -->		
				<tr id="tr_btn">
				<c:if test="${member.id == article.id || member.id == 'admin'}">
				 <input id="button1" type=button value="수정하기">  
				 <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					 </c:if>
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
		    
		  $('#i_imageFileName').attr({
				'name' : 'imageFileName' + count++
				
				});

		 	//$("#i_imageFileName").attr('id', 'i_#imageFileName'+ count++);
		 	
		 	   var active = document.getElementById("i_imageFileName");
		 	   active.id = 'i_imageFileName' + count++;
		 	   
		 	
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