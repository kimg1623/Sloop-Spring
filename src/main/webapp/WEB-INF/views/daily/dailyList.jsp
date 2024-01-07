<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link href="<c:url value="/src/main/webapp/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<title>daily 리스트</title>
	<style>
		.dailyBox{
			border: 1px solid black;
		}
	</style>
</head>
<body>
<h4>공부인증 게시판</h4>

<div class="searchBox">
	<form action="<c:url value='/daily/search'/>" method="get">
		<input type="text" name="postDailyTitle" placeholder="제목으로 검색" >
		<button type="submit">검색</button>
	</form>
</div>


<div class="container">
	<c:forEach items="${dailyList}" var="daily">
		<div class="dailyBox" align="left">
			<div class="col-md-4">
				<h3>제목 : ${daily.postDailyTitle}</h3>
				<p>내용 : ${daily.postDailyContents}
				<p>닉네임 : ${daily.memberNickname}

				<p>일자 : ${empty daily.postDailyEditDate?daily.postDailyRegDate:daily.postDailyEditDate}</p>
				<p>조회수 : ${daily.postDailyHits}

				<p><a href="<c:url value="/daily/detail?postIdx=${daily.postIdx}"/>"
					  class="btn btn-secondary" role="button">상세정보 &raquo;</a>
			</div>
		</div>
	</c:forEach>
</div>
<button class="btn btn-primary" onclick="writeFn()">글작성</button>

</body>

<script>
	const writeFn = () => {
		location.href = "/daily/write";
	}

</script>
</html>