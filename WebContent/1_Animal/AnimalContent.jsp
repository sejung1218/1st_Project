<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FileUpContent.css" rel="stylesheet">
<script src="http://dloft.net/project/glowseoul/js/jquery-1.11.0.min.js"></script>
<script>
	$(function(){
		$("#btn_delete").on("click", function(){
			 if(!confirm("정말로 삭제하시겠습니까?")) return;
			 location="${conPath}/AnimalDelete.do?aNum=${AnimalContentView.aNum }&pageNum=${param.pageNum }";
		});		
	});
	
</script>
<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />

	<div class="board_wrap height">

		<div id="contentbox">

			<form action="${conPath}/AnimalModifyView.do" method="post"
				class="contentUp">

				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="aNum"
					value="${AnimalContentView.aNum }">

				<div id="left">
					<img src="${conPath}/FileBoardUp/1_AnimalUp/${AnimalContentView.aFilename}" height=100%; width=100%;>
				</div>


				<div id="right">

					<table id="ct_table">
					
						<caption>${AnimalContentView.aTitle }</caption>

						<tr class="ct_1">
							<td>작성자</td>
							<td>${AnimalContentView.mId}</td>
						</tr>
						
						<tr class="ct_2">
							<td>제목</td>
							<td><div>${AnimalContentView.aTitle}</div></td>
						</tr>

						<tr class="ct_3">
							<td>파일명</td>
							<td><div>${AnimalContentView.aFilename}</div></td>
						</tr>

						<tr class="ct_4">
							<td>본문</td>
							<td><div>${AnimalContentView.aContent}</div></td>
						</tr>
						
						<tr class="ct_5">
							<td>업로드 날짜</td>
							<td><div>${AnimalContentView.aDate}</div></td>
						</tr>

					</table>

				</div>
				

				<div class="btn_wrap">
					<c:if test="${member.mId eq AnimalContentView.mId }">
						<button type="submit" class="btn focus">수정</button>
					</c:if>

					<c:if
						test="${member.mId eq AnimalContentView.mId or not empty admin}">
						<button type="button" class="btn focus" id="btn_delete">삭제</button>
					</c:if>

					<button type="button" class="btn focus"
						onclick="location='${conPath}/AnimalList.do?pageNum=${param.pageNum }'">목록</button>

				</div>

			</form>
		</div>

		<jsp:include page="../Main/footer.jsp" />

	</div>

</body>
</html>