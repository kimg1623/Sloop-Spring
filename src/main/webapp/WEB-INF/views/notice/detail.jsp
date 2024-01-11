<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-01-11
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 상세보기</title>
</head>
<body>
<h1>공지사항</h1>

<table>
    <tr>
        <td>분류</td>
        <td><c:out value="${noticeDTO.postIdx}"/></td>
    </tr>
    <tr>
        <td>제목</td>
        <td><c:out value="${noticeDTO.postIdx}"/></td>
    </tr>
    <tr>
        <td>작성자</td>
        <td><c:out value="${noticeDTO.memberNickname}"/></td>
    </tr>
    <tr>
        <td>작성일</td>
        <td><c:out value="${noticeDTO.postNoticRegDate}"/>
        <c:if test="${not empty noticeDTO.postNoticeEditDate}">
            <c:out value="(edited)"/>
        </c:if>
        </td>
    </tr>
    <tr>
        <td>조회수</td>
        <td><c:out value="${noticeDTO.postIdx}"/></td>
    </tr>
    <tr>
        <td>내용</td>
        <td><c:out value="${noticeDTO.postIdx}"/></td>
    </tr>

</table>
<button onclick="findNotice()"></button>
<c:if test="${noticeDTO.memberEmail == sessionScope.loginEmail}">
    <button onclick="updateNotice('${noticeDTO.postIdx}')">수정</button>

</c:if>
</body>
</html>
