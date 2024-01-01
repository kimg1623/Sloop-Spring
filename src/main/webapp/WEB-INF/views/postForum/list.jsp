<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 12/27/23
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List</title>
    <!-- jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<table>
    <input type="button" value="글쓰기" onclick="writeBtn()"/>
    <thead>
        <tr>
            <th>번호</th>
            <th>분류</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${postForumDTOList}" var="postForum" varStatus="status">
        <tr class="tr_${postForum.postIdx}">
            <td><c:out value="${status.index + 1}"/></td>
            <td><c:out value="${postForum.categoryPostName}"/></td>
            <td><a href="/postforum/detail?postIdx=${postForum.postIdx}"><c:out value="${postForum.postForumTitle}"/></a></td>
            <%-- <td><c:out value="${postForum.postForumTitle}"/></td> --%>
            <td><c:out value="${postForum.memberNickname}"/></td>
            <td><c:out value="${postForum.postForumRegDate}"/></td>
            <td><c:out value="${postForum.postForumHits}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    // 문서가 완전히 로드된 후에 스크립트를 실행하기 위해 jQuery의 document.ready() 함수를 사용
    $(document).ready(function() {
        $('table tbody tr').click(function() {
            // Get the href attribute of the anchor tag within the clicked row
            const hrefValue = $(this).find('a').attr('href');
            window.location.href = hrefValue;
            //console.log(hrefValue);
            // Redirect to the href value if needed
            // window.location.href = hrefValue;
        });
    });

    // 글 작성하기 버튼
    writeBtn = () => {
        location.href = "/postforum/write";
    };
</script>
</body>
</html>
