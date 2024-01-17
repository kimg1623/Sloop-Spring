<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<script>
	// 글 작성
	const writeFn = () => {
		location.href = previousURL+"/write";
	}

	// 현재 페이지 URL 가져오기
	const currentURL = window.location.href;

	// URL에서 studyGroupCode와 boardIdx 파라미터 추출하기
	const urlParams = new URLSearchParams(currentURL);
	const studyGroupCode = urlParams.get('studyGroupCode');
	const boardIdx = urlParams.get('boardIdx');

	console.log("studyGroupCode:", studyGroupCode);
	console.log("boardIdx:", boardIdx);

	// 이전 URL 생성 예시
	const previousURL = `/study/${studyGroupCode}/daily/${boardIdx}`;

	console.log("Previous URL:", previousURL);

	// 검색하기 버튼
	searchBtn = () => {
		let searchType = document.getElementsByName("searchType")[0].value;
		let keyword =  document.getElementsByName("keyword")[0].value;
		keyword = keyword.trim(); // 앞뒤 공백 제거
		/*
        console.log(searchType);
        console.log(keyword);
         */

		// 검색어가 공백인 경우 alert
		if(keyword === "" || keyword.length == 0){
			alert("검색어를 입력해 주세요.");
			return false;
		}

		location.href = previousURL+'/list?page=1&searchType=' + searchType + '&keyword=' + keyword;
	};

</script>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<div class="container-main">

		<%-- 검색 --%>
		<div id="search" name="search" class="searchList">

				<select name="searchType">
					<option value="1" <c:if test="${pageDTO.searchType == 0 || pageDTO.searchType == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${pageDTO.searchType == 2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${pageDTO.searchType == 3}">selected</c:if>>제목+내용</option>
					<option value="4" <c:if test="${pageDTO.searchType == 4}">selected</c:if>>작성자</option>
				</select>
					<input class="searchInfo" type="text" name="keyword" value="${pageDTO.keyword}" />
					<input class="searchButton" type="button" onclick="searchBtn()" value="검색"/>

		<button class="dailyWriteButton" onclick="writeFn()">글작성</button>
		</div><!--searchList-->

		<!-- 글 속성 -->

		<%--		<tr>--%>
		<%--			<th>번호</th>--%>
		<%--			<th>제목</th>--%>
		<%--			<th>작성자</th>--%>
		<%--			<th>일자</th>--%>
		<%--			<th>조회수</th>--%>
		<%--		</tr>--%>

		<!-- 글 본문 -->
		<div class="row">

			<c:choose>
				<%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
				<c:when test="${empty dailyList}">
					<h5 class="card-title">등록된 글이 없습니다.</h5>
				</c:when>
				<%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
				<c:otherwise>
					<c:forEach items="${dailyList}" var="daily" varStatus="status">
						<div class="col-sm-4">
							<div class="card-study">
								<li>
								<a href="/study/${studyGroupCode}/daily/${boardIdx}/detail?postIdx=${daily.postIdx}">
									<img src="${contextPath}/resources/images/thumbnail_01.png" class="card-img-top" alt="...">
									<div class="card-border"></div>
									<div class="card-body">

										<h1 class="dailyList-title"><c:out value="${daily.postDailyTitle}"/></h1>
									<div class="dailyList-date">
										<p class="card-text"><c:out value="${empty daily.postDailyEditDate?daily.postDailyRegDate:daily.postDailyEditDate}"/></p>
									</div><!--dailyList-date-->
										<section class="info-bottom">
										<div><p class="card-text"><c:out value="${daily.memberNickname}"/></p></div>
										<div class="info-bottom-hit"><p class="card-text">조회수: <c:out value="${daily.postDailyHits}"/></p></div>
										</section>
									</div> <!--card-body-->
								</a>
								</li>
							</div><!--card-study-->
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div><!--row-->

		<!-- 페이징 + 검색 -->
			<%-- 글이 1개 이상 존재할 때만 페이징 출력 --%>
			<c:if test="${not empty dailyList}">
				<%-- 페이징 --%>
				<div id="paging" name="paging" class="page_wrap">
					<div class="page_nation">
						<%-- [이전] --%>
					<c:choose>
						<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
						<c:when test="${pageDTO.page<=1}">
							<span class="prev">이전</span>
						</c:when>
						<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
						<c:otherwise>
							<a href="/study/${studyGroupCode}/daily/${boardIdx}/list?page=${pageDTO.page-1}&searchType=${pageDTO.searchType}&keyword=${pageDTO.keyword}">이전</a>
						</c:otherwise>
					</c:choose>
						<%-- 페이징 번호 --%>
					<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" var="i" step="1">

						<c:choose>
							<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
							<c:when test="${i == pageDTO.page}">
								<span>${i}</span>
							</c:when>

							<c:otherwise>
								<a href="/study/${studyGroupCode}/daily/${boardIdx}/list?page=${i}&searchType=${pageDTO.searchType}&keyword=${pageDTO.keyword}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						<%-- [다음] --%>
					<c:choose>
						<c:when test="${pageDTO.page>=pageDTO.maxPage}">
							<span class="next">다음</span>
						</c:when>
						<c:otherwise>
							<a href="/study/${studyGroupCode}/daily/${boardIdx}/list?page=${pageDTO.page+1}&searchType=${pageDTO.searchType}&keyword=${pageDTO.keyword}">다음</a>
						</c:otherwise>
					</c:choose>
					</div><!--page_nation-->
				</div><!--page_wrap-->
			</c:if>


</main>