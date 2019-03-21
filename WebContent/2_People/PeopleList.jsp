<%@page import="com.tj.ex.dto.PeopleDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tj.ex.dao.PeopleDao"%>
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
<link href="${conPath}/css/FileUpList.css" rel="stylesheet">

</head>
<body>

	<jsp:include page="../Main/header.jsp" />

	<div>

		<div id="SliderMain">
			<ul id="slider">
				<li style="background-image: url('./images/2.people/slide/1.jpg');"></li>
				<li style="background-image: url('./images/2.people/slide/2.jpg');"></li>
				<li style="background-image: url('./images/2.people/slide/3.jpg');"></li>
				<li style="background-image: url('./images/2.people/slide/4.jpg');"></li>
				<li style="background-image: url('./images/2.people/slide/5.jpg');"></li>
			</ul>
		</div>



		<div class="sm">

			<c:if test="${totCnt==0 }">
				<div class="block_empty">글이 없습니다</div>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${list}" var="dto" varStatus="status"> 
					<c:if test="${status.index % 4 == 0 }">
						<fmt:parseNumber var="curIndex" value="${status.index/4 + 1}" integerOnly="true" />
						<div class="block${curIndex }">
					</c:if>

					<div class="white" onclick="location.href='${conPath }/PeopleContentView.do?pNum=${dto.pNum}&pageNum=${pageNum}'">
						<img src="${conPath }/FileBoardUp/2_PeopleUp/${dto.pFilename}" alt="${dto.pTitle }" style='width:100%; height:100%;'/>
					</div>

					<c:if test="${status.index % 4 == 3 || status.last }">
						</div>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	
	
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }">
					[ <a href="${conPath }/PeopleList.do?pageNum=${startPage-1}"> 이전 </a> ]
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i == pageNum }">
						<b> [ ${i} ] </b>
					</c:if>
					<c:if test="${i != pageNum }">
						[ <a href="${conPath }/PeopleList.do?pageNum=${i}"> ${i } </a> ]
					</c:if>
				</c:forEach>
				<c:if test="${endPage<pageCnt }">
			  	[ <a href="${conPath }/PeopleList.do?pageNum=${endPage+1}"> 다음 </a> ]
				</c:if>
			</div>
		
	<div class="btn_wrap">
			<c:if test="${not empty member }">
				<a href="${conPath }/PeopleWriteView.do" id="after_login" class="btn focus">글쓰기</a>
			</c:if>
			<c:if test="${empty member }">
				<a href="${conPath }/loginView.do" id="before_login" class="btn focus">글쓰기는 사용자 로그인 이후에만 가능합니다</a>
			</c:if>
	</div>
		
	
	
	
	




	<script type='text/javascript'>
		var x = 100;
		var slider = document.getElementById("slider");
		var slideArray = slider.getElementsByTagName("li");
		var slideMax = slideArray.length - 1;
		var curSlideNo = 0;

		for (i = 0; i <= slideMax; i++) {
			if (i == curSlideNo)
				slideArray[i].style.left = 0;
			else
				slideArray[i].style.left = -x + "%";
		}

		slider.addEventListener('click', function() {
			changeSlide();
		}, false);

		var aniStart = false;
		var next = 1;
		var changeSlide = function() {
			if (aniStart === true)
				return;
			next = curSlideNo + 1;
			if (next > slideMax)
				next = 0;
			aniStart = true;
			sliding();
		}

		function sliding() {
			var curX = parseInt(slideArray[curSlideNo].style.left, 10);
			var nextX = parseInt(slideArray[next].style.left, 10);
			var newCurX = curX + 1;
			var newNextX = nextX + 1;
			if (newCurX >= x) {
				slideArray[curSlideNo].style.left = -x + "%";
				slideArray[next].style.left = 0;
				curSlideNo = curSlideNo + 1;
				if (curSlideNo > slideMax)
					curSlideNo = 0;
				aniStart = false;
				return;
			}
			slideArray[curSlideNo].style.left = newCurX + "%";
			slideArray[next].style.left = newNextX + "%";
			setTimeout(function() {
				sliding();
			}, 20);
		}
		setInterval(changeSlide, 4000);
	</script>

	<jsp:include page="../Main/footer.jsp" />

</body>
</html>