<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/FreeBoardList.css" rel="stylesheet">
<style></style>
</head>
<body>

	<jsp:include page="../Main/header.jsp" />
	
	<div class="board_wrap height">
		<div id="listbox">

			<form class="listUp" method="post">
				<table id="box1">
					<thead>
						<tr id="box2">
							<th>글번호</th>
							<th>글제목</th>
							<th>작성자</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>
					</thead>
					<c:if test="${totCnt==0}">
						<tbody>
							<tr>
								<td colspan="5">글이 없습니다</td>
							</tr>
						</tbody>
					</c:if>
					<c:if test="${totCnt!=0}">
						<tbody>
							<c:forEach items="${list}" var="dto">
								<tr>
									<td>${dto.rNum }</td>
									<td class="left"><c:forEach var="i" begin="1"
											end="${dto.rIndent }">
											<c:if test="${i==dto.rIndent }">└─</c:if>
											<c:if test="${i!=dto.rIndent }"> &nbsp; &nbsp; </c:if>
										</c:forEach> <a href="${conPath }/RequestBoardContentView.do?rNum=${dto.rNum}
																&pageNum=${pageNum}">${dto.rTitle }</a>
									</td>
									<td>${dto.mId }</td>
									<td><fmt:formatDate value="${dto.rDate }" type="date"
											pattern="yy.MM.dd" /></td>
									<td>${dto.rReadcount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
				</table>
			</form>

			<!--   -->

			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/RequestBoardList.do?pageNum=${startPage-1}">
						이전 </a> ]
			</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i == pageNum }">
						<b> [ ${i } ] </b>
					</c:if>
					<c:if test="${i != pageNum }">
					[ <a href="${conPath }/RequestBoardList.do?pageNum=${i}"> ${i } </a> ]
				</c:if>
				</c:forEach>
				<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/RequestBoardList.do?pageNum=${endPage+1}">
						다음 </a> ]
			</c:if>
				<!--   -->
			</div>
			<div class="btn_wrap">
				<c:if test="${not empty member }">
					<a href="${conPath }/RequestBoardWriteView.do" id="after_login" class="btn focus">글쓰기</a>
				</c:if>
				<c:if test="${empty member }">
					<a href="${conPath }/loginView.do" id="before_login" class="btn focus">글쓰기는 사용자 로그인
						이후에만 가능합니다</a>
				</c:if>
			</div>
		</div>
		
		<jsp:include page="../Main/footer.jsp" />
		
	</div>
	
</body>
</html>