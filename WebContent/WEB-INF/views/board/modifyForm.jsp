<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite2/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url = "/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url = "/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<!-- board-aside -->
			<c:import url = "/WEB-INF/views/includes/boardaside.jsp"></c:import>
			<!-- //board-aside -->

			<div id="content">

				<!-- content-head -->
				<c:import url = "/WEB-INF/views/includes/boardcontenthead.jsp"></c:import>
				<!-- //content-head -->
	
				<div id="board">
					<div id="modifyForm">
						<form action="bc" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span>
								<span class="form-value">${boardVo.name}</span>
							</div>
							
							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span>
								<span class="form-value">${boardVo.hit}</span>
							</div>
							
							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span>
								<span class="form-value">${boardVo.date}</span>
							</div>
							
							<!-- 제목 -->
							<div class="form-group">
								<label class="form-text" for="txt-title">제목</label>
								<input type="text" id="txt-title" name = "title" value = "${boardVo.title}"></input>
							</div>
						
							<!-- 내용 -->
							<div class="form-group">
								<textarea id="txt-content" name = "content">${boardVo.content}</textarea>
							</div>
							
							<a id="btn_cancel" href="/mysite2/bc?action=read&no=${boardVo.no}">취소</a>
							<input type = "hidden" name = "no" value = "${boardVo.no}"></input>
							<input type = "hidden" name = "action" value = "modify"></input>
							<button id="btn_modify" type="submit">수정</button>
							
						</form>
						<!-- //form -->
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<!-- footer -->
		<c:import url = "/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>