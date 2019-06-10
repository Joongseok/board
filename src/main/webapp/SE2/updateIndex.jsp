<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>
<%@include file="/common/basicLib.jsp" %>
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="${pageContext.request.contextPath }/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
cnt = 1;
count = parseInt(1);
$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${pageContext.request.contextPath }/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
	
	$("#count").val(count);
	$("#pp").on("click", function (){
		
		if($("#count").val() >= 6){
			alert("파일은 다섯개까지만 첨부하실수 있습니다.")
			return;
		}
		$('<input type="file" " name="files">').insertAfter("#pp");
		count +=parseInt(1);
		$("#count").val(count);
	});
	
	$(".mm").on("click", function (){

		if($(this).prev().text() != ""){
			alert($(this).prev().remove())
			$(this).prev().attr("name", "deleteFileId"+cnt);
			cnt += 1;
		}
		if($(this).prev().html() == ""){
			$(this).css("display", "none");
		}
		$('<input type="file" name="files">').insertAfter("#pp");
		count +=parseInt(1);
		$("#count").val(count);
	});
	
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}
</script>
</head>
<body>
<form  action="${pageContext.request.contextPath}/updateNotice" method="post" id="frm" enctype="multipart/form-data">
<div class="form-group">
	<label for="title" class="col-sm-1 control-label">제목</label>
	<div class="col-sm-10">
<%-- 	<label class="control-label">${userVo.userId }</label> --%>
		<input type="text" class="form-control" id="title"
			name="title" value="${noticeVo.title}" placeholder="제목">
	</div>
</div>

	<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">${noticeVo.content }</textarea> 
<!-- <input type="file" name="file" id="file"> -->
<div class="form-group">
	<label for="userId" class="col-sm-2 control-label">첨부파일</label>		
		<div class="col-sm-8">
		<c:forEach items="${fileList}" var="file">
				<input type="hidden" class="hiddenFile" name="fileId" value="${file.fileId}">
			<label id="fileLabel">${file.fileName }</label>
			<img alt="" class="mm" src="${pageContext.request.contextPath }/img/minus.png">
			<br>
			<script>count +=1;</script>
		</c:forEach>
		</div>
</div>

<img alt="" id="pp" src="${pageContext.request.contextPath }/img/plus.png">

<input type="hidden" id="count" >
			<script>
			
			$("#count").val(count);
			</script>
<input type="hidden" name="id" value="${id }">
<input type="hidden" name="notiId" value="${noticeVo.notiId}">

<input type="button" class="col-sm-1 btn btn-default" id="savebutton" value="수정" />
</form>
</body>
</html>






