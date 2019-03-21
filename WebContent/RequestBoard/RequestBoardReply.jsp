<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FreeBoardWrite.css" rel="stylesheet">
<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />

	<div class="write_wrap height">

		<div id="writebox">

			<form action="${conPath}/RequestBoardReply.do" class="writeUp" method="post">

				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="rGroup" value="${RequestBoardReplyView.rGroup }">
				<input type="hidden" name="rStep" value="${RequestBoardReplyView.rStep }">
				<input type="hidden" name="rIndent" value="${RequestBoardReplyView.rIndent }">

				<table id="box1">

					<caption>${RequestBoardReplyView.rTitle} 의 답글</caption>

					<tr>
						<td>제목</td>
						<td><input type="text" name="rTitle" required size="130"></td>
					</tr>

					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="rPw" required size="130"></td>
					</tr>

					<tr class="area">
						<td>본문</td>
						<td><textarea name="rContent" required rows="29" cols="131.5"></textarea></td>
					</tr>

				</table>

				<div class="btn_wrap">
					<button type="submit" class="btn focus">글쓰기</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="button" class="btn focus"
						onclick="location.href='${conPath}/RequestBoardList.do'">목록</button>
				</div>

			</form>

			<jsp:include page="../Main/footer.jsp" />

		</div>

	</div>

</body>
</html>