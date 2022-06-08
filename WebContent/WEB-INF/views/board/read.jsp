<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite2/assets/css/board.css" rel="stylesheet"
	type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<!-- board-aside -->
			<c:import url="/WEB-INF/views/includes/boardaside.jsp"></c:import>
			<!-- //board-aside -->

			<div id="content">

				<!-- content-head -->
				<c:import url="/WEB-INF/views/includes/boardcontenthead.jsp"></c:import>
				<!-- //content-head -->

				<div id="board">
					<div id="read">
						<form action="bc" method="post">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span> <span class="form-value">${boardVo.name}</span>
							</div>

							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span> <span class="form-value">${boardVo.hit}</span>
							</div>

							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span> <span class="form-value">${boardVo.date}</span>
							</div>

							<!-- 제목 -->
							<div class="form-group">
								<span class="form-text">제 목</span> <span class="form-value">${boardVo.title}</span>
							</div>

							<!-- 내용 -->
							<div id="txt-content">
								<span class="form-value"> ${boardVo.content} </span>
							</div>

							<c:choose>
								<c:when test="${boardVo.userNo == authUser.no}">
									<a id="btn_modify"
										href="/mysite2/bc?action=modifyForm&no=${boardVo.no}">수정</a>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							<a id="btn_modify" href="/mysite2/bc?action=list">목록</a>
						</form>
						<!-- //form -->
					</div>
					<!-- //read -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
