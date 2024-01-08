<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kjw85
  Date: 2024-01-04
  Time: 오전 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sloop슬룹</title>
</head>
<body>
    <h1>스터디 그룹 home</h1>
    <h2>스터디 그룹명: ${studyGroup.studyGroupName}</h2>
    -----------------------------------
    <br>
    <div>
        <h2>메뉴</h2>
        <c:forEach items="${groupBoardIdxs}" var="boardIdxMap">
        <a href="/study/${studyGroup.studyGroupCode}/notice/${boardIdxMap.boardIdx}">${boardIdxMap.categoryName}</a><br>
<%--        <a href="#">자유게시판</a><br>--%>
<%--        <a href="#">인증게시판</a><br>--%>
<%--        <a href="#">과제게시판</a><br>--%>
        </c:forEach>
        <a href="/study/${studyGroup.studyGroupCode}/manage/info">스터디 관리</a><br>
    </div>

</body>
</html>
