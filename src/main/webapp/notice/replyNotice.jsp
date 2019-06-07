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
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%@include file="/common/basicLib.jsp"%>

<script>
	$(document).ready(function() {
		
		var msg = '${msg}';
		if (msg != '') {
			alert(msg);
		}
		$("#plus").on("click", function() {
			$("#file").click();
		});

		$("#addrSearchBtn").on("click", function() {

			new daum.Postcode({
				oncomplete : function(data) {
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
			if ($("#userId").val() == "") {
				alert("아이디를 입력해주세요");
				return;
			}
			if ($("#name").val() == "") {
				alert("이름을 입력해주세요");
				return;
			}
			if ($("#alias").val() == "") {
				alert("별명을 입력해주세요");
				return;
			}
			if ($("#pass").val() == "") {
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

	function dataInit() {
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
						<h2 class="sub-header">게시글 등록</h2>

						<form id="frm" class="form-horizontal"
							action="${pageContext.request.contextPath}/replyNotice"
							method="post" role="form" enctype="multipart/form-data">
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									<%@include file="../SE2/reply.jsp"%>
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
