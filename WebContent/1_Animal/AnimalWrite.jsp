<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FileUpWrite.css" rel="stylesheet">
<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />

	<div class="write_wrap height">

		<div id="writebox">

			<form action="${conPath }/AnimalWrite.do" method="post" enctype="multipart/form-data" class="writeUp">

				<table id="box1">

					<caption>사진게시판 글쓰기</caption>

					<tr>
						<td>제목</td>
						<td><input type="text" name="aTitle" required size="130"></td>
					</tr>

					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="aPw" required size="130"></td>
					</tr>

					<tr>
						<td>본문</td>
						<td><textarea name="aContent" required rows="29" cols="131.5"></textarea></td>
					</tr>

					<tr>
						<td>첨부파일</td>
						<td><input type="file" name="aFilename" required></td>
					</tr>

				</table>

				<div class="btn_wrap">
					<button type="submit" class="btn focus">글쓰기</button>
					<button type="reset" class="btn focus">취소</button>
					<button type="button" class="btn focus" onclick="location.href='${conPath}/AnimalList.do'">목록</button>
				</div>

			</form>

			<jsp:include page="../Main/footer.jsp" />
			
		</div>

	</div>


</body>
</html>