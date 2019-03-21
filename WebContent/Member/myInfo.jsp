<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/myInfo.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../Main/header.jsp" />
	
	<div class="myInfo_wrap height">
	
		<div id="myInfobox">
			
			<table>
				
				<caption>${member.mName}님</caption>
				
				<tr><td>게시판명</td>
					<td>글 수</td>
					<td></td>
				
				<tr><td>자유</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>신청</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>1</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>2</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>3</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>4</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>5</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>6</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>7</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				<tr><td>8</td>
					<td>갯수</td>
					<td><button type="button">게시판이동</button></td>
				</tr>
				
			</table>
				
			<button type="button" class="btn focus" onclick="location.href='${conPath}/modifyView.do'">정보수정</button>
			<button type="button" class="btn focus" onclick="location.href='${conPath}/logout.do'">로그아웃</button>
			
			
			
		<jsp:include page="../Main/footer.jsp" />
		
		</div>
	
	</div>

</body>
</html>