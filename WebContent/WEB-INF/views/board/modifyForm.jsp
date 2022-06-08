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

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">방명록</a></li>
			</ul>
		</div>
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
						<form action="#" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span>
								<span class="form-value">정우성</span>
							</div>
							
							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span>
								<span class="form-value">123</span>
							</div>
							
							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span>
								<span class="form-value">2020-03-02</span>
							</div>
							
							<!-- 제목 -->
							<div class="form-group">
								<label class="form-text" for="txt-title">제목</label>
								<input type="text" id="txt-title" name="" value="여기에는 글제목이 출력됩니다.">
							</div>
						
							
						
							<!-- 내용 -->
							<div class="form-group">
								<textarea id="txt-content">여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.
	여기에는 본문내용이 출력됩니다.</textarea>
							</div>
							
							<a id="btn_cancel" href="">취소</a>
							<button id="btn_modify" type="submit" >수정</button>
							
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