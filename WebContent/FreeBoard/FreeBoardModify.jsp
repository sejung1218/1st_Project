<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<link href="${conPath}/css/FreeBoardModify.css" rel="stylesheet">
<style></style>
</head>

<body>

	<jsp:include page="../Main/header.jsp" />

	<div class="board_wrap height">

		<div id="modifybox">

			<form action="${conPath}/FreeBoardModify.do" class="modifyUp"
				method="post">

				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="fNum"
					value="${FreeBoardModifyView.fNum }">

				<table id="box1">

					<caption>자유게시판 글수정</caption>

					<tr>
						<td>제목</td>
						<td><input type="text" name="fTitle" value="${FreeBoardModifyView.fTitle}" required size="130"></td>
					</tr>

					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="fPw" value="${FreeBoardModifyView.fPw}" required size="130"></td>
					</tr>

					<tr>
						<td>본문</td>
						<td><textarea name="fContent" required rows="29" cols="131.5">${FreeBoardModifyView.fContent }</textarea></td>
					</tr>

				</table>

				<div class="btn_wrap">
					<button type="submit" class="btn focus">글수정</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="button" class="btn focus" onclick="location.href='${conPath}/FreeBoardList.do'">목록</button>
				</div>

			</form>
			
		</div>

		<jsp:include page="../Main/footer.jsp" />

	</div>

</body>
</html>