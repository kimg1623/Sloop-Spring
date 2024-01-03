<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link href="<c:url value="/src/main/webapp/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<title>daily 리스트</title>
</head>
<body>
	<h4>공부인증 게시판</h4>
	<div class="container">
			<c:forEach items="${dailyList}" var="daily">
				<div class="border border-success p-2 mb-2 border-opacity-25" align="left">
				<div class="col-md-4">
					<h3>제목 : ${daily.postDailyTitle}</h3>
					<p>내용 : ${daily.postDailyContents}
					<p>닉네임 : ${daily.memberNickname}
					<p>작성일자 : ${daily.postDailyRegDate}
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
