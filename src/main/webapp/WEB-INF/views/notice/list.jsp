<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 헤더 임포트 -->
<jsp:include page="../include/header.jsp"></jsp:include>
<section>
	<div class="container px-5 my-5 px-5">
		<div class="search-box col-md-5">
			<form id="searchFrm" name="searchFrm" method="get" action="/notice/list">
				<div class="input-group mb-3">
					<select class="browser-default custom-select" id="searchType" name="searchType">
						<option value="all" <c:if test="${noticeDTO.searchType == 'all'}"> selected="selected" </c:if> >전체</option>
						<option value="title" <c:if test="${noticeDTO.searchType == 'title'}"> selected="selected" </c:if> >제목</option>
						<option value="content" <c:if test="${noticeDTO.searchType == 'content'}">selected="selected"</c:if>>내용</option>
					</select>
					<input type="text" class="form-control" id="searchTxt" name="searchTxt" value="<c:out escapeXml='true' value='${noticeDTO.searchTxt}' />">
					<div class="input-group-append">
						<button class="btn btn-success" type="button" onclick="javascript:document.searchFrm.submit();" >검색</button>
					</div>
				</div>
			</form>
		</div>
		<div>
			<h2>게시판 목록</h2>
			<table class="table table-striped table-hover">
				<thead>
				<tr>
					<th scope="col" class="w-auto p-3">카테고리</th>
					<th scope="col" class="w-auto p-3">연번</th>
					<th scope="col" class="w-50 p-3">제목</th>
					<th scope="col" class="w-auto p-3">작성자</th>
					<th scope="col" class="w-auto p-3">조회수</th>
					<th scope="col" class="w-auto p-3">작성일</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${!empty list && fn:length(list) != 0}">
						<c:forEach items="${list}" var="list" varStatus="status">
							<tr>
								<td>
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${list.categoryPostName}' />
									</a>
								</td>
								<td>
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${totalCnt - (noticeDTO.page-1)*noticeDTO.row - status.index}' />
									</a>
								</td>
								<td>
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${list.postNoticeTitle}' />
									</a>
								</td>
								<td >
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${list.memberIdx}' />
									</a>
								</td>
								<td>
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${list.postNoticeHits}' />
									</a>
								</td>
								<td>
									<a href="<c:url value="/notice/detail?searchType=${noticeDTO.searchType}&searchTxt=${noticeDTO.searchTxt}&page=${noticeDTO.page}&postIdx=${list.postIdx}"/>" >
										<c:out escapeXml='true' value='${list.postNoticeRegDate}' />
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5"><span>데이터가 없습니다.</span></td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<div class="paging" align="center">
				<c:out escapeXml='false' value="${pagingHtml}"/>
			</div>
		</div>
		<div>
			<button type="button" class="btn btn-sm btn-primary" onclick="javascript:location.href='/notice/write';">글쓰기</button>

			<div style='width:80px;float: right;'>
				<input type='button' class="btn btn-sm btn-primary" onclick="location.href='/'" name='btn2' value='홈'>

			</div>
			<!-- 로그인 사용자에게만 등록버튼을 노출한다. -->
			<!-- <c:if test="${sessionScope.loginMemberInfo != null}" >-->
			<!-- </c:if>-->
		</div>
</section>
<!-- 푸터 임포트 -->
<jsp:include page="../include/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>

<style type="text/css">
	a:link { text-decoration: none;}
	a:visited { text-decoration: none;}
	a:active { text-decoration: none;}
	a:hover {text-decoration:underline;}
</style>
