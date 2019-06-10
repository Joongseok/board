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
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시판관리</title>
<style>
	.lprodTr:hover{
		cursor: pointer;
	}
	
</style>
<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
<script>
$(document).ready(function () {
	
	if($("#userId").val() == ""){
		alert("로그인을 하지 않았습니다.")
		$("#frm").attr("action", "<%=request.getContextPath()%>/login/login.jsp");
		$("#frm").submit();
		return;
	}
	
	if( $(".update_use_yn").val() == "true"){
		$(".updateUse_yn").val("true");
	}
	
	$("#createClick").on("click", function (){
		if($("#create_use_yn").val() == "true"){
			
			$("#use_yn").val("true");
		}
		if($("#createBoardName").val() == ""){
			alert("게시판 이름을 입력해주세요");
			return;
		}else{
			$("#createFrm").submit();
		}
	})
	$('#create_use_yn').change(function() {
		if($(this).val == ""){
			$(this).val("true");
		}
	    var option = $(this).val();
	    $("#use_yn").val(option);
	});
	$('.update_use_yn').change(function() {
		if($(this).val == ""){
			$(this).val("true");
		}
	    var option = $(this).val();
	    alert($(this).val())
	    $(".updateUse_yn").val(option);
	});
})
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
					<form id="createFrm" class="navbar-form navbar-right" id="createfrm" action="${pageContext.request.contextPath }/createBoard" method="post">
						<input type="text" class="form-control" id="createBoardName" name="createBoardName" placeholder="게시판 이름">
						<select class="form-control" id="create_use_yn">
						  <option value="true">사용</option>
						  <option value="false">미사용</option>
						</select>
						<input type="hidden" id="use_yn" name="use_yn">
						<input type="hidden" id="user_info" name="user_info" value="${USER_INFO.userId }">
						<input type="button" id="createClick" class="form-control"value="게시판 생성">
					</form>
						<h2 class="sub-header">게시판관리</h2>
						
						<form id="frm" action="${pageContext.request.contextPath}/boardManager" method="post">
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시판 이름</th>
									<th>생성자</th>
									<th>생성일시</th>
									<th>사용여부</th>
								</tr>
							</table>
						</div>
						
						<div class="text-center">
							<table class="table table-striped">
								<c:if test="${boardAllList != null }">
									<c:forEach items="${boardAllList}" var="board">
										<tr class="updateTr">
											<td>${board.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td>${board.userId }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><fmt:formatDate value="${board.reg_dt }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<c:choose>
												<c:when test="${board.use_yn eq 'true' }">
													<td> 사용</td>
												</c:when>
												<c:otherwise>
													<td> 미사용</td>
												</c:otherwise>
											</c:choose>
											<td class="updateTd">
												<form class="navbar-form navbar-right" class="updateBoard" action="${pageContext.request.contextPath }/updateBoard" method="post">
													<select class="update_use_yn">
													  <option value="true">사용</option>
													  <option value="false">미사용</option>
													</select>
													<input type="hidden" class="updateUse_yn" name="updateUse_yn">
													<input type="hidden" name="id" value="${board.id }">
													<input type="hidden" id="userId" value="${USER_INFO.userId}">
													<input type="submit" class="form-control"value="게시판 수정">
												</form>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
