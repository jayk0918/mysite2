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
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items = "${bList}" var = "BoardVo" varStatus = "status">
								<tr>
									<td>${BoardVo.no}</td>
									<td class="text-left"><a href="/mysite2/bc?action=read&no=${BoardVo.no}">${BoardVo.title}</a></td>
									<td>${BoardVo.name}</td>
									<td>${BoardVo.hit}</td>
									<td>${BoardVo.date}</td>
									<c:choose>
										<c:when test = "${BoardVo.userNo == authUser.no}">
											<td><a href="/mysite2/bc?action=delete&no=${BoardVo.no}">[삭제]</a></td>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</tr>
								</c:forEach>
							</tbody>
							
						</table>
			
						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						
						<c:choose>
							<c:when test = "${authUser != null}">
								<a id="btn_write" href="/mysite2/bc?action=writeForm&no=${authUser.no}">글쓰기</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- //list -->
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
