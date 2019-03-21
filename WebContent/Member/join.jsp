<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link href="${conPath}/css/join.css" rel="stylesheet">
<script src="http://dloft.net/project/glowseoul/js/jquery-1.11.0.min.js"></script>
<script>

$(document).ready(function() {
	$('#mIdConfirm').click(function() {
		var mId = $('input[name="mId"]').val();
		$.ajax({
			url : '${conPath}/mIdConfirm.do',
			type : 'get',
			dataType : 'html',
			data : "mId=" + mId,
			success : function(data) {
				$('#idConfirmMsg').html(data);
			} 
		});	
	});
	
	$('input[name="pwChk"]').keyup(function(){
		if ($('input[name="mPw"]').val() == $('input[name="pwChk"]').val()){
			$($('#pwChkMsg').html('비밀번호 일치'));
		} else {
			$($('#pwChkMsg').html('비밀번호 불일치'));
		}
	});
});
	
</script>

</head>
<body>
	
	<jsp:include page="../Main/header.jsp" />
		
	<div class="join_wrap height">
	
		<div id="joinbox">
		
			<form action="${conPath}/join.do" class="joinUp" name="frm" onsubmit="return chk()">
			
				<p>아이디<input type="button" class="btn_join" id="mIdConfirm" value="중복체크"></p><input type="text" name="mId" class="join" placeholder="ID" autofocus required="required">
				<div id="idConfirmMsg">&nbsp; &nbsp; &nbsp;</div>
				<p>비밀번호</p>	<input type="password" name="mPw" class="join" placeholder="password" required="required"> 
				<p>비밀번호 확인</p>	<input type="password" name="pwChk" class="join" placeholder="password" required="required">
				<div id="pwChkMsg"></div>
				<p>이름</p>		<input type="text" name="mName" class="join" placeholder="Name" required="required"> 
				<p>전화번호</p>	<input type="text" name="mTel" class="join" placeholder="Tel" required="required"> 
				<p>이메일</p>		<input type="text" name="mEmail" class="join" placeholder="Email" required="required"> 
				<p>생년월일</p>	<input type="date" name="mBirth" class="join" placeholder="Birth" required="required"> 
				<p>주소</p>		<input type="text" name="mAddr" class="join" placeholder="Address" required="required"> 
				
				<div class="btn_wrap">
					<button type="submit" class="btn focus">회원가입</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="button" class="btn focus" onclick="location.href='${conPath}/loginView.do'">목록</button>
				</div>
				
			</form>
			
			<jsp:include page="../Main/footer.jsp" />
			
		</div>
	
	</div>
	
</body>
</html>