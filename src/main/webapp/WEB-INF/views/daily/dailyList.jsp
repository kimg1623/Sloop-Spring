<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
	<p><button class="btn btn-primary" onclick="writeFn()">글작성</button></p>
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

<!--페이징 추가-->
<div>
	<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
	<%-- 1페이지보다 작거나 같으면(1페이지면) 더이상 이전으로 갈 수가 없어서 링크없이 [이전] 글자만 보이게--%>
	<c:choose>

		<c:when test="${paging.page<=1}">
			<span>[이전]</span>
		</c:when>
		<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
		<c:otherwise>
			<a href="/daily/list?page=${paging.page-1}">[이전]</a>
		</c:otherwise>
	</c:choose>

	<%--  for(int i=startPage; i<=endPage; i++)      --%>
	<%-- startPage=begin / endPage=end / i=(var="i") / i++=(step="1")--%>
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
			<%-- 현재 페이지에 있을 경우 숫자 아래 링크X --%>
			<c:when test="${i eq paging.page}">
				<span>${i}</span>
			</c:when>

			<c:otherwise>
				<a href="/daily/list?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>


	<c:choose>
		<c:when test="${paging.page>=paging.maxPage}">
			<span>[다음]</span>
		</c:when>

		<c:otherwise>
			<a href="/daily/list?page=${paging.page+1}">[다음]</a>
		</c:otherwise>
	</c:choose>


</body>

<script>
	const writeFn = () => {
		location.href = "/daily/write";
	}

</script>
</html>