<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FileUpModify.css" rel="stylesheet">
<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />

	<div class="write_wrap height">

		<div id="writebox">

			<form action="${conPath }/PeopleModify.do" method="post" enctype="multipart/form-data" class="writeUp">

				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="pNum" value="${PeopleModifyView.pNum }">

				<table id="box1">

					<caption>사진게시판 글수정</caption>

					<tr>
						<td>제목</td>
						<td><input type="text" name="pTitle" value="${PeopleModifyView.pTitle}" required size="130"></td>
					</tr>

					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pPw" value="${PeopleModifyView.pPw}" required size="130"></td>
					</tr>

					<tr>
						<td>본문</td>
						<td><textarea name="pContent" required rows="29" cols="131.5">${PeopleModifyView.pContent }</textarea></td>
					</tr>

					<tr>
						<td>첨부파일</td>
						<td><input type="file" name="pFilename" value="${PeopleModifyView.pFilename}" required></td>
					</tr>

				</table>

				<div class="btn_wrap">
					<button type="submit" class="btn focus">글수정</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="button" class="btn focus" onclick="location.href='${conPath}/PeopleList.do'">목록</button>
				</div>

			</form>

		</div>

		<jsp:include page="../Main/footer.jsp" />

	</div>

</body>
</html>