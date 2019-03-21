<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/header.css" rel="stylesheet">
<script src="http://dloft.net/project/glowseoul/js/jquery-1.11.0.min.js"></script>
<script>
	function funLoad() {
		var Cheight = $(window).height();
		$('.height').css({
			'min-height' : Cheight + 'px'
		});
	}
	window.onload = funLoad;
	window.onresize = funLoad;
	$(window).scroll(function() {
		var scTop = $(window).scrollTop();
		head_nav(scTop);
	});
</script>
</head>
<body>

	<div id="nav">

		<div id="n_name">

			<a href="${conPath}/main.do">HOME</a>

		</div>




		<c:if test="${empty member}">

			<div id="n_menu">

				<ul>
					<li><a href="${conPath}/loginView.do">LOGIN</a></li>
					<li><a>BOARD</a>
						<ul>
							<li><a href="${conPath}/FreeBoardList.do">FREE</a></li>
							<li><a href="${conPath}/RequestBoardList.do">REQUEST</a></li>
						</ul>
					</li>
				</ul>

			</div>

		</c:if>


		<c:if test="${not empty member}">

			<div id="n_menu">

				<ul>
				
					<li><a>MYPAGE</a>
						<ul>
							<li><a href="${conPath}/logout.do">LOGOUT</a></li>
							<%-- <li><a href="${conPath}/myInfo.do">MY INFO</a></li> --%>
							<li><a href="${conPath}/modifyView.do">MODIFY</a></li>
						</ul>
					</li>
					
					
					
					
					<li><a>BOARD</a>
						<ul>
							<li><a href="${conPath}/FreeBoardList.do">FREE</a></li>
							<li><a href="${conPath}/RequestBoardList.do">REQUEST</a></li>
						</ul>
					</li>
					
				</ul>

			</div>

		</c:if>

	</div>

</body>
</html>