<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<link href="/resources/css/style_studygroup.css" rel="stylesheet">
<style>
    .no-underline{
        text-decoration:none;
    }
</style>

<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-title" aria-current="page" href="/study/${studyGroup.studyGroupCode}">
                    <span data-feather="home"></span>
                    ${studyGroup.studyGroupName}
                </a>
                <div class="sidebar-division-line"></div>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/study/${studyGroup.studyGroupCode}/notice/${groupBoardIdxs[0].boardIdx}">
                    <span data-feather="home"></span>
                    ${groupBoardIdxs[0].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroup.studyGroupCode}/assign/${groupBoardIdxs[1].boardIdx}">
                    <span data-feather="file"></span>
                    ${groupBoardIdxs[1].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroup.studyGroupCode}/postforum/${groupBoardIdxs[2].boardIdx}">
                    <span data-feather="shopping-cart"></span>
                    ${groupBoardIdxs[2].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroup.studyGroupCode}/daily/${groupBoardIdxs[3].boardIdx}">
                    <span data-feather="users"></span>
                    ${groupBoardIdxs[3].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroup.studyGroupCode}/manage/info">
                    <span data-feather="bar-chart-2"></span>
                    스터디 관리
                </a>
            </li>
        </ul>


    </div>

    ----------------------

    <h3>스터디 그룹명</h3>
    <h2><a href="/study/${studyGroup.studyGroupCode}">${studyGroup.studyGroupName}</a></h2>
    -------
    <br>
    <div>
        <h2>메뉴</h2>
        <a href="/study/${studyGroup.studyGroupCode}/notice/${groupBoardIdxs[0].boardIdx}">${groupBoardIdxs[0].categoryName}</a><br>
        <a href="/study/${studyGroup.studyGroupCode}/assign/${groupBoardIdxs[1].boardIdx}">${groupBoardIdxs[1].categoryName}</a><br>
        <a href="/study/${studyGroup.studyGroupCode}/postforum/${groupBoardIdxs[2].boardIdx}">${groupBoardIdxs[2].categoryName}</a><br>
        <a href="/study/${studyGroup.studyGroupCode}/daily/${groupBoardIdxs[3].boardIdx}">${groupBoardIdxs[3].categoryName}</a><br>
        <a href="/study/${studyGroup.studyGroupCode}/manage/info">스터디 관리</a><br>


        <tiles:importAttribute name="menuList"/>
        <c:forEach var ="menu" items="${menuList}">
            <button>${menu}</button>
        </c:forEach>


        <c:set var="title" value="${title}"></c:set>
        <title>${title}</title>

        <!-- 공통변수 처리 -->
        <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="application"/>
        <c:set var="RESOURCES_PATH" value="${CONTEXT_PATH}/resources" scope="application"/>
        <br>
        =>${CONTEXT_PATH} => ${RESOURCES_PATH}



    </div>
</nav>

