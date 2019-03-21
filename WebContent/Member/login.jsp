<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="${conPath}/css/login.css" rel="stylesheet">

<script>

	function chk() {
		if (frm.mId.value == '') {
			alert('아이디를 입력하세요.');
			frm.mId.focus();
			return false;
		} else if (frm.mPw.value == '') {
			alert('비밀번호를 입력하세요.');
			return false;
		}
		return true;
	}
	
</script>

</head>
<body>
	
	<jsp:include page="../Main/header.jsp" />
	
	<div class="login_wrap height">
	
		<div id="loginbox">
	
			<form action="${conPath}/login.do" method="post" name="frm" id="frm" onsubmit="return chk()">
			
				<input type="text" name="mId" id="mId" class="login" placeholder="ID" autofocus required> 
				<input type="password" name="mPw" id="mPw" class="login" placeholder="password" required> 
			
				<div class="btn_wrap">
					<button type="submit" class="btn focus">Login</button>
					<button type="button" class="btn focus" onclick="location='${conPath}/joinView.do'">Join</button>
				</div>
			
			</form>
		<jsp:include page="../Main/footer.jsp" />
		
		</div>
	
	</div>
	

</body>
</html>