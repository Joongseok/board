<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%@include file="/common/basicLib.jsp"%>
<script>
	$(document).ready(function() {
		if($("#userId").val() == ""){
			alert("로그인을 하지 않았습니다.")
			$("#frm").attr("action", "<%=request.getContextPath()%>/login/login.jsp");
			$("#frm").submit();
			return;
		}
		$("#plus").on("click", function() {
			$("#file").click();
		});
	});
</script>
<title>게시글 등록</title>
<%@include file="/common/basicLib.jsp"%>
</head>

<body>
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 등록</h2>

						<form id="frm" class="form-horizontal"
							action="${pageContext.request.contextPath}/noticeForm"
							method="post" role="form" enctype="multipart/form-data">
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									<%@include file="../SE2/index.jsp"%>
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
