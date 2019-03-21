<%@page import="com.tj.ex.dao.RequestBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/style.css" rel="stylesheet">
</head>
<body>
	<%
		for(int i=1 ; i<115 ; i++){
			RequestBoardDao dao = new RequestBoardDao();
			dao.write("lsj", i+"번 글", i+"번 글 내용", "111");
		}
		response.sendRedirect("${conPath}/RequestBoardlist.do");
	%>
</body>
</html>