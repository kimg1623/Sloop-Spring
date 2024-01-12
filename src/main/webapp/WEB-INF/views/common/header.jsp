<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>헤더</title>
    <link href="/resources/css/style_header.css" rel="stylesheet">
</head>
<body>

    <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="/">
            <img src="${contextPath}/resources/images/logo3.png"/>
        </a>
        <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
        <div class="navbar-nav">
            <div class="nav-item text-nowrap">
                <c:choose>
                    <c:when test="${sessionScope.loginMemberNickname != null}">
                        <span class="nav-link px-3">환영합니다. ${sessionScope.loginMemberNickname}님!</span>
                        <a class="nav-link px-3" href="/member/logout">로그아웃</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link px-3" href="/member/login">로그인</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>

</body>
</html>