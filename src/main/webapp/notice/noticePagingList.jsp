<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>${boardVo.name }</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

<style>
	.userTr:hover{
		cursor: pointer;
	}
</style>

<script>
	$(document).ready(function (){
		//  사용자 tr태그 이벤트 등록
		$(".noticeTr").on("click", function(){
			console.log("noticeTr click");
			
			//userId를 획득하는 방법
// 			$(this).find(".userId").html();
// 			$(this).data("userid");

			// 사용자 아이디를 #userId 값으로 설정해주고
			var notiId = $(this).find(".idHidden").val();
			$("#notiId").val(notiId);
			
			//#frm 을 이용하여 submit();
			$("#frm").submit();
		});
	});

</script>
</head>

<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${boardVo.name }</h2>
						
						<!-- 사용자 상세 조회 : userId가 필요 -->
						<form id="frm" action="${pageContext.request.contextPath}/noticeDetail" method="get">
							<input type="hidden" id="notiId" name="notiId">
						
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호(삭제여부를 가려서 번호를 출력할것)</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일시</th>
								</tr>
								<c:forEach items="${noticeList}" var="notice">
									<tr class="noticeTr">
									<form>
										<input type="hidden" class="idHidden" value="${notice.notiId}">
									</form>
										<td class="notiId">${notice.rn}</td>
										<td>${notice.title}</td>
										<td>${USER_INFO.userId}</td>
										<td><fmt:formatDate value="${notice.reg_dt }" pattern="yyyy-MM-dd a h:mm:ss"/> </td>
									</tr>
								</c:forEach>
							</table>
						</div>
						

					<a class="btn btn-default pull-right" href="${pageContext.request.contextPath }/noticeForm?id=${boardVo.id}">게시글 작성</a>
						<div class="text-center">
							<ul class="pagination">
								<!--  내가 현재 몇번째 페이지에 있는가? -->
								<c:choose> 
									<c:when test="${pageVo.page  == 1}">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/noticeController?page=${pageVo.page - 1 }&pageSize=${pageVo.pageSize}&id=${boardVo.id}">«</a></li>
									</c:otherwise>
								</c:choose>
									
								<c:forEach var="i" begin="1" end="${paginationSize}" step="1">
									<li> 
									<c:choose>    
										<c:when test="${pageVo.page == i}">
											<li class="active" ><span>${i }</span> </li>
										</c:when>
										<c:when test="${pageVo.page != i}">
											<a href="${pageContext.request.contextPath}/noticeController?page=${i}&pageSize=${pageVo.pageSize}&id=${boardVo.id}">${i}</a>
										</c:when>
									</c:choose>
									</li>
								</c:forEach>
								
								<c:choose> 
									<c:when test="${pageVo.page  == paginationSize}">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li class="page-item" ><a href="${pageContext.request.contextPath}/noticeController?page=${pageVo.page + 1 }&pageSize=${pageVo.pageSize}&id=${boardVo.id}">»</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
