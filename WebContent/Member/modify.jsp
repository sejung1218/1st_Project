<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<link href="${conPath}/css/modify.css" rel="stylesheet">
<script>

	function chk() {
		if (frm.mPw.value != frm.mPwChk.value) {
			alert('비밀번호를 확인하세요');
			frm.mPw.value = '';
			frm.mPwChk.value = '';
			frm.mPw.focus();
			return false;
		}
		return true;
	}
	
</script>

</head>
<body>

	<jsp:include page="../Main/header.jsp" />
	
	<div class="modify_wrap height">
	
		<div id="modifybox">
		
			<form action="${conPath}/modify.do" method="post" class="modifyUp" name="frm" onsubmit="return chk()">
			
				<p>아이디</p>		<input type="text" name="mId" value="${member.mId}" class="modify" readonly="readonly"> 
				<p>비밀번호</p>	<input type="password" name="mPw" value="${member.mPw}" class="modify" required="required"> 
				<p>비밀번호 확인</p>	<input type="password" name="mPwChk" value="${member.mPw}" class="modify" required="required">
				<p>이름</p>		<input type="text" name="mName" value="${member.mName}" class="modify" required="required"> 
				<p>전화번호</p>	<input type="text" name="mTel" value="${member.mTel}" class="modify" required="required"> 
				<p>이메일</p>		<input type="text" name="mEmail" value="${member.mEmail}" class="modify" required="required"> 
				<p>생년월일</p>	<input type="date" name="mBirth" value="${member.mBirth}" class="modify" required="required"> 
				<p>주소</p>		<input type="text" name="mAddr" value="${member.mAddr}" class="modify" required="required"> 
				
				<div class="btn_wrap">
					<button type="submit" class="btn focus">정보수정</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="reset" class="btn focus" onclick="history.back()">이전</button>
					<button type="button" class="btn focus" onclick="location.href='${conPath}/logout.do'">로그아웃</button>
				</div>
			</form>
			
		</div>
	
	<jsp:include page="../Main/footer.jsp" />
	
	</div>
	
</body>
</html>