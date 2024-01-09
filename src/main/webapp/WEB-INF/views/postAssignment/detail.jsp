<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 2024/01/09
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>과제 게시판: 게시글 상세 조회</title>
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
                <!-- 수정일시가 존재한다면 작성일 옆에 (edited) 표시 -->
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
    <%-- 게시물 작성자 이메일과 session에 저장된 로그인 된 이메일이 동일할 경우에만 수정, 삭제 버튼 출력 --%>
    <c:if test="${postForumDTO.memberEmail == sessionScope.loginEmail}">
        <button onclick="updateFn('${postForumDTO.postIdx}')">수정</button>
        <button onclick="deleteFn('${postForumDTO.postIdx}')">삭제</button>
    </c:if>

    <script>
        // 목록으로 돌아가기
        const listFn = () => {
            location.href = "/postforum/list";
        };

        // 글 수정하기
        const updateFn = (postIdx) => {
            location.href = "/postforum/update?postIdx=" + postIdx;
        };

        // 글 삭제하기
        const deleteFn = (postIdx) => {
            // location.href = "/postforum/delete?postIdx=" + postIdx;

            if (confirm("삭제하시겠습니까?") == true){
                //true는 확인버튼을 눌렀을 때 코드 작성
                console.log("완료되었습니다.");
                location.href = "/postforum/delete?postIdx=" + postIdx;
            }else{
                // false는 취소버튼을 눌렀을 때, 취소됨
                console.log("취소되었습니다");
            }
        };
    </script>
</body>
</html>
