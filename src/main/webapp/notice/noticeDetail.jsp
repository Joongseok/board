<%@page import="kr.or.ddit.notice.model.NoticeVO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%@include file="/common/basicLib.jsp" %>
<style>
	.hiddenFile{
	visibility: hidden;
	}
</style>
<script>
$("#content").val();
$(document).ready(function (){
	if($("#userId").val() == ""){
		alert("로그인을 하지 않았습니다.")
		$("#frm").attr("action", "<%=request.getContextPath()%>/login/login.jsp");
		$("#frm").submit();
		return;
	}
	$("#commentBtn").on("click", function (){
		alert("commentBtn");
		$("#commentForm").submit();
		
	})
	
	$(".fileLabel").on("click", function(){
		alert("fileLable");
		$("#frm").submit();
	});
	
	$("#plus").on("click", function () {
		$("#file").click();
	});
	
});
</script>
<title>게시글 조회</title>
<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 조회</h2>
							
						<form id="frm" class="form-horizontal" action="${pageContext.request.contextPath}/fileDownload" 
						method="post" role="form"
						enctype="multipart/form-data"
						>

							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<label class="control-label">${noticeVo.title}</label>
								</div>
							</div>
							
							<div class="form-group">
							<label for="userId" class="col-sm-2 control-label">내용</label>		
								<div class="col-sm-10">
									${noticeVo.content }
								</div>
							</div>
							<div class="form-group">
							<label for="userId" class="col-sm-2 control-label">첨부파일</label>		
								<div class="col-sm-8">
								<c:forEach items="${uploadFileList}" var="file">
										<input type="hidden" class="hiddenFile" name="fileId" value="${file.fileId}">
									<label class="fileLabel">${file.fileName }</label><br>
								</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">댓글</label>
								<div class="col-sm-6">
								<c:forEach items="${ntcList }" var="comment">
									<label class="control-label">${comment.content} [${comment.userId }/ <fmt:formatDate value="${comment.reg_dt }" pattern="yyyy-MM-dd"/> ]</label>
										
										<c:if test="${comment.del_yn eq true && comment.userId eq USER_INFO.userId}">
										<a href="${pageContext.request.contextPath }/deleteComment?notiId=${noticeVo.notiId}&userId=${USER_INFO.userId}&id=${comment.id}">
										<button  type="button" class="btn btn-default">댓글 삭제</button> </a>
										</c:if>
										<br>
									</c:forEach>
								</div>
							</div>
							</form>
							<form id="commentForm"  class="form-horizontal" action="${pageContext.request.contextPath }/noti_comment" method="post">
								<div class="form-group">
									<label for="userId" class="col-sm-2 control-label"></label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="comment"
											name="comment" placeholder="댓글">
										<input type="hidden" name="cntNotiId" value="${noticeVo.notiId}">
									</div>
										<input id="commentBtn" type="button" class="btn btn-default" value="댓글저장">
								</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:if test="${noticeVo.userId eq USER_INFO.userId }">
										<a href="${pageContext.request.contextPath }/updateNotice?notiId=${noticeVo.notiId}"><button  type="button" class="btn btn-default">수정하기</button> </a>
										<a href="${pageContext.request.contextPath }/deleteNotice?notiId=${noticeVo.notiId}"><button  type="button" class="btn btn-default">삭제하기</button> </a>
									</c:if>
										<a href="${pageContext.request.contextPath }/replyNotice?notiId=${noticeVo.notiId}"><button type="button" id="replyBtn" class="btn btn-default">답글</button> </a>
								</div>
							</div>
							<input type="hidden" id="userId" value="${USER_INFO.userId }"> 
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
