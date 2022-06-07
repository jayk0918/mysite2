<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import = "com.javaex.vo.UserVo"%>

<% UserVo authUser = (UserVo)session.getAttribute("authUser");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="header" class="clearfix">
		<h1>
			<a href="/mysite2/main">MySite</a>
		</h1>

		<%if (authUser != null) {%>
		<ul>
			<li><%=authUser.getName()%>님 안녕하세요^^</li>
			<li><a href="/mysite2/user?action=logout" class="btn_s">로그아웃</a></li>
			<li><a href="/mysite2/user?action=modifyForm" class="btn_s">회원정보수정</a></li>
		</ul>
		<%}else {%>
		<ul>
			<li><a href="/mysite2/user?action=loginForm" class="btn_s">로그인</a></li>
			<li><a href="/mysite2/user?action=joinForm" class="btn_s">회원가입</a></li>
		</ul>
		<%}%>
	</div>



</body>
</html>