
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<jsp:include page="../header.jsp" />

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

var cnt=1;
function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
}  

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
							작성자
						</td>
						<td><input type="text" value="${member.id}" readonly/></td>
						</tr>
						
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
	      				<td colspan="4"><div id="d_file" name="All_file"></div></td>
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