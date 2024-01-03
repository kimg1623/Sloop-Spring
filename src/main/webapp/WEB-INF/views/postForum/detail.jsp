<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 2024/01/01
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail</title>
</head>
<body>
    <table>
        <tr>
            <td>분류</td>
            <td><c:out value="${postForumDTO.categoryPostName}"/></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><c:out value="${postForumDTO.postForumTitle}"/></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><c:out value="${postForumDTO.memberNickname}"/></td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>
                <c:out value="${postForumDTO.postForumRegDate}"/>
                <!-- 수정일시가 존재한다면 (edited) 표시 -->
                <c:if test="${not empty postForumDTO.postForumEditDate}">
                    <c:out value="(edited)" />
                </c:if>
            </td>
        </tr>
        <tr>
            <td>조회수</td>
            <td><c:out value="${postForumDTO.postForumHits}"/></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><c:out value="${postForumDTO.postForumContents}" escapeXml="false"/></td>
        </tr>

        <p hidden="true">${postForumDTO.memberIdx}</p>
    </table>
    <button onclick="listFn()">목록</button>
    <button onclick="updateFn()">수정</button>
    <button onclick="deleteFn()">삭제</button>

    <script>
        // 목록으로 돌아가기
        const listFn = () => {
            location.href = "/postforum/list";
        };

        // 글 수정하기
        const updateFn = () => {
            location.href = "/postforum/update?postIdx=" + "${postForumDTO.postIdx}";
        }

        // 글 삭제하기
        const deleteFn = () => {
            location.href = "/postforum/delete?postIdx=" + "${postForumDTO.postIdx}";
        }
    </script>
</body>
</html>
