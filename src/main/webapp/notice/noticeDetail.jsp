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
	
	$("#commentBtn").on("click", function (){
		alert("commentBtn");
		$("#commentForm").submit();
		
	})
	
	$(".fileLabel").on("click", function(){
		alert("fileLable");
		$("#frm").submit();
	});
	
	var msg = '${msg}';
	if(msg != ''){
		alert(msg);
	}
	$("#plus").on("click", function () {
		$("#file").click();
	});
	
	$("#addrSearchBtn").on("click", function(){
		
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            console.log(data);
	            console.log(data.roadAddress);
	            console.log(data.zonecode);
	            $("#addr1").val(data.roadAddress);
	            $("#zipcd").val(data.zonecode);
	        }
	    }).open();
	});
	$("#userRegBtn").on("click", function() {
		console.log("userRegBtn click")
		if($("#userId").val() == ""){
			alert("아이디를 입력해주세요");
			return;
		}
		if($("#name").val() == ""){
			alert("이름을 입력해주세요");
			return;
		}
		if($("#alias").val() == ""){
			alert("별명을 입력해주세요");
			return;
		}
		if($("#pass").val() == ""){
			alert("비밀번호를 입력해주세요");
			return;
		}
		$("#frm").submit();
		
	});
	
	// 개발용 데이타 초기화 함수 ***** 추후 지울것
// 	 if($("#frm :input").val() == ""){
// 			dataInit();
// 		}
});

function dataInit(){
	$("#userId").val("userTest");
	$("#name").val("대덕인");
	$("#alias").val("중앙로");
	$("#addr1").val("대전광역시 중구 중앙로76");
	$("#addr2").val("영민빌딩 2층 대덕인재개발원");
	$("#zipcd").val("34940");
	$("#birth").val("2019-05-31");
	$("#pass").val("userTest1234");
}
</script>
<title>게시글 등록</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

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
						<h2 class="sub-header">게시글 조회</h2>
							
							
						<form id="frm" class="form-horizontal" action="${pageContext.request.contextPath}/fileDownload" 
						method="post" role="form"
						enctype="multipart/form-data"
						>

<!-- 							<div class="form-group"> -->
<!-- 								<label for="filename" class="col-sm-2 control-label">사용자 -->
<!-- 									사진</label> -->
<!-- 								<div class="col-sm-10"> -->
<!-- 									<input type="file" id="file" -->
<%-- 										name="profile" value="${param.filename}" > --%>
<!-- 								</div> -->
<!-- 							</div> -->
							
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<label class="control-label">${noticeVo.title}</label>
<!-- 									<input type="text" class="form-control" id="userId" -->
<%-- 										name="userId" value="${noticeVo.title}" placeholder="제목"> --%>
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
<!-- 							<div class="form-group"> -->
<!-- 								<label for="filename" class="col-sm-2 control-label"></label> -->
<!-- 								<div class="col-sm-2"> -->
<!-- 									<input type="text" id="fileName"class="form-control" readonly > -->
<!-- 								</div> -->
<!-- 								<img alt="" id="plus" src="/SE2/img/ico_extend.png">  -->
<!-- 									<input type="file" id="file" -->
<!-- 										name="modifyFile" class="btn btn-default" > -->
<!-- 							</div> -->
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">댓글</label>
								<div class="col-sm-6">
								<c:forEach items="${ntcList }" var="comment">
									<label class="control-label">${comment.content} [${comment.userId }/ <fmt:formatDate value="${comment.reg_dt }" pattern="yyyy-MM-dd"/> ]</label>
										
										<c:if test="${comment.del_yn eq true}">
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
							</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
