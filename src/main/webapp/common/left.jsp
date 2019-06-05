<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a
			href="${pageContext.request.contextPath}/boardManager">게시판 관리</a></li>
			
			<hr>
			<c:forEach items="${boardList }" var="board">
				<li class="active"><a
					href="${pageContext.request.contextPath}/noticeController?id=${board.id}">${board.name }</a>
					</li>
			</c:forEach>
<!-- 		<li class="active"><a -->
<%-- 			href="${pageContext.request.contextPath}/userPagingList">사용자페이징리스트</a></li> --%>
<!-- 		<li class="active"><a -->
<%-- 			href="${pageContext.request.contextPath}/lprodPagingList">lprod 페이징 리스트</a></li> --%>
	</ul>
</div>