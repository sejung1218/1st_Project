<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FreeBoardContent.css" rel="stylesheet">
<script src="http://dloft.net/project/glowseoul/js/jquery-1.11.0.min.js"></script>
<script>
	$(function(){
		$("#btn_delete").on("click", function(){
			 if(!confirm("정말로 삭제하시겠습니까?")) return;
			 location="${conPath}/RequestBoardDelete.do?rNum=${RequestBoardContentView.rNum }&pageNum=${param.pageNum }";
		});		
	});
	
</script>

<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />
	<div class="board_wrap height">
		<div id="contentbox">

			<form action="${conPath}/RequestBoardModifyView.do" method="post" class="contentUp">

				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="rNum" value="${RequestBoardContentView.rNum }">
				
				<table>
					<caption>${RequestBoardContentView.rTitle }</caption>
					
					<tr>
						<td>작성자</td>
						<td>${RequestBoardContentView.mId}</td>
					</tr>
					
					<tr>
						<td>제목</td>
						<td><div>${RequestBoardContentView.rTitle}</div></td>
					</tr>
					
					<tr>
						<td>본문</td>
						<td><div>${RequestBoardContentView.rContent}</div></td>
					</tr>

				</table>

				<div class="btn_wrap">
					<c:if test="${member.mId eq RequestBoardContentView.mId }">
						<button type="submit" class="btn focus">수정</button>
					</c:if>

					<c:if
						test="${member.mId eq RequestBoardContentView.mId or not empty admin}">
						<button type="button" class="btn focus" id="btn_delete"
								onclick="location='${conPath}/RequestBoardDelete.do?rNum=${RequestBoardContentView.rNum }&pageNum=${param.pageNum }'">삭제</button>
					</c:if>

					<c:if test="${not empty member }">
						<button type="button" class="btn focus"
								onclick="location='${conPath}/RequestBoardReplyView.do?rNum=${RequestBoardContentView.rNum}&pageNum=${param.pageNum}'">답변</button>
					</c:if>
					
					<button type="button" class="btn focus"
							onclick="location='${conPath}/RequestBoardList.do?pageNum=${param.pageNum }'">목록</button>

				</div>

			</form>
			
		</div>
		
		<jsp:include page="../Main/footer.jsp" />
		
	</div>
	
</body>
</html>