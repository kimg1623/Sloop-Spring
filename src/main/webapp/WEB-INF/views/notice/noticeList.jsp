<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 게시판</title>
</head>
<body>

<table border="1">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>수정일</th>
            <th>조회수</th>
            <th>카테고리</th>
            <th>상단고정여부</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${noticeList}" var="notice">
            <tr>
                <td><a href="notice/detail?postIdx=${notice.postIdx}">${notice.postIdx}</a></td>
                <td>${notice.postNoticeTitle}</td>
                <td>${notice.postNoticeContents}</td>
                <td>${notice.memberIdx}</td>
                <td>${notice.postNoticeRegDate}</td>
                <td>${notice.postNoticeEditDate}</td>
                <td>${notice.postNoticeHits}</td>
                <td>${notice.categoryPostIdx}</td>
                <td>${notice.postNoticePinned}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
