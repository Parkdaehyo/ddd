
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>로그인창</title>

<c:choose>
	<c:when test="${result=='loginFailed'}">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    }
	  </script>
	</c:when>
</c:choose>

<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/default.css?ver=20.07.23" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/common.css?ver=20.07.23" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/content.css?ver=20.07.23" />
<link rel="stylesheet" type="text/css" href="/resources/DR0001CSS/button.css?ver=20.07.23" />
<!--[if lt IE 9]>
<script src="DR0001JS/html5shiv.js"></script>
<![endif]-->

</head>

<body>
<div id="login_wrap">
	<div id="login_box">
		<div class="login_con">
			<!-- 로그인 타이틀 -->
			<div class="login_tit">
				<h2>회사명넣어주세요</h2>
				<p>LOG<i>IN</i></p>
			</div>
			<!-- // 로그인 타이틀 -->
			
			<!-- 로그인 form -->
			<div class="login_input">
		<form name="frmLogin" method="post"  action="${contextPath}/member/login.do">	
				<ul>
					<li>
						<label for="login_id" class="skip_info">아이디</label>
						<input type="text" title="아이디" placeholder="아이디" id="id" name="id" />
					</li>
					<li>
						<label for="login_pwd" class="skip_info">비밀번호</label>
						<input type="password" title="비밀번호" placeholder="비밀번호" id="pwd" name="pwd" />
					</li>
				</ul>
				<input type="submit" value="로그인" class="login_btn" />
			</form>
			</div>
			<!-- // 로그인 form -->
		</div>
		<p class="copyright">Copyright(c)회사명.All rights reserved.</p>	
	</div>
</div>
</body>
</html>