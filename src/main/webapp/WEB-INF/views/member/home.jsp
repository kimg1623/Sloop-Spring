<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${sessionScope.loginEmail}의 마이페이지 입니다.</h1>
<a href="/member/mypage">마이페이지</a>

<hr>
<table>
    <tr>
        <th>스터디 그룹 번호</th>
        <th>스터디 그룹명</th>
        <th>스터디 그룹 내 권한</th>
    </tr>
<c:choose>
    <%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
    <c:when test="${empty myStudy}">
        <tr>
            <td colspan="6">등록된 글이 없습니다.</td>
        </tr>
    </c:when>
    <%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
    <c:otherwise>
        <c:forEach items="${myStudy}" var="member" varStatus="status">
        <tr>
            <td><c:out value="${member.studyGroupIdx}"/></td>
            <td><a href="/study/${member.studyGroupCode}"><c:out value="${member.studyGroupName}"/></a></td>
            <td><c:out value="${member.studyMemRole}"/></td>
        </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
</table>
</body>

</html>
