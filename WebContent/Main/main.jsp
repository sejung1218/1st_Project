<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Photo.PX</title>
<link href="${conPath}/css/main.css" rel="stylesheet">
<style></style>
</head>

<body>

	<jsp:include page="header.jsp" />

	<div id="profile" class="height">

		<jsp:include page="../Main/Slide.html" />

		<div id="p_wrap">

			<div id="p_name">
				<h3>Photo.PX</h3>
			</div>

			<div id="p_title">
				my photograph album
			</div>

			<div id="p_btn" onclick="location.href='#body'">
				<a href="#body"><img src="${conPath}/images/etc/btn_arrow.png"></a>
			</div>

		</div>

	</div>

	<div id="body">

		<div id="b_list">
		
			<div class="bl_photo" style="background-image: url('./images/1.animal/1.jpg');" 
				 onclick="location.href='${conPath}/AnimalList.do'">
				<p>동물</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/2.people/1.jpg');" 
				 onclick="location.href='${conPath}/PeopleList.do'">
				<p>사람</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/3.vehicle/1.jpg');" 
				 onclick="location.href='#body'">
				<p>교통</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/4.nature/1.jpg');" 
				 onclick="location.href='#body'">
				<p>자연</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/5.illust/1.jpg');" 
				 onclick="location.href='${conPath}/IllustList.do'">
				<p>일러스트</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/6.sports/1.jpg');" 
			 	 onclick="location.href='#body'">
				<p>스포츠</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/7.landmark/1.jpg');" 
				 onclick="location.href='#body'">
				<p>건물</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

			<div class="bl_photo" style="background-image: url('./images/8.technique/1.jpg');" 
				 onclick="location.href='#body'">
				<p>기술</p>
				<img src="./images/etc/ratio_1x1.png" alt="공백" />
			</div>

		</div>

	</div>

	<jsp:include page="../Main/footer.jsp" />

</body>
</html>