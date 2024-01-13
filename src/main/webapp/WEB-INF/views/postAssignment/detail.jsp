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
            <td>제목</td>
            <td><c:out value="${postAssignmentDTO.postAssignmentTitle}"/></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><c:out value="${postAssignmentDTO.memberNickname}"/></td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>
                <c:out value="${postAssignmentDTO.postAssignmentRegDate}"/>
                <!-- 수정일시가 존재한다면 작성일 옆에 (edited) 표시 -->
                <c:if test="${not empty postAssignmentDTO.postAssignmentEditDate}">
                    <c:out value="(edited)" />
                </c:if>
            </td>
        </tr>
        <tr>
            <td>과제 마감일시</td>
            <td>
                <c:out value="${postAssignmentDTO.assignmentEndDate}"/>
            </td>
        </tr>
        <tr>
            <td>조회수</td>
            <td><c:out value="${postAssignmentDTO.postAssignmentHits}"/></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><c:out value="${postAssignmentDTO.postAssignmentContents}" escapeXml="false"/></td>
        </tr>
        <tr>
            <td>첨부파일</td>
            <td>
                <c:forEach items="${postAssignmentDTO.attachmentDTOList}" var="attachmentDTO">
                    <ul><c:out value="${attachmentDTO.attachmentName}"/> </ul>
                </c:forEach>
            </td>
        </tr>

        <p hidden="true">${postAssignmentDTO.memberIdx}</p>
    </table>
    <button onclick="listFn()">목록</button>
    <%-- 게시물 작성자 이메일과 session에 저장된 로그인 된 이메일이 동일할 경우에만 수정, 삭제 버튼 출력 --%>
    <c:if test="${postAssignmentDTO.memberEmail == sessionScope.loginEmail}">
        <button onclick="updateFn('${postAssignmentDTO.postIdx}')">수정</button>
        <button onclick="deleteFn('${postAssignmentDTO.postIdx}')">삭제</button>
    </c:if>

    <script>
        // 목록으로 돌아가기
        const listFn = () => {
            location.href = "/postassignment/list";
        };

        // 글 수정하기
        const updateFn = (postIdx) => {
            location.href = "/postassignment/update?postIdx=" + postIdx;
        };

        // 글 삭제하기
        const deleteFn = (postIdx) => {
            // location.href = "/postassignment/delete?postIdx=" + postIdx;

            if (confirm("삭제하시겠습니까?") == true){
                //true는 확인버튼을 눌렀을 때 코드 작성
                console.log("완료되었습니다.");
                location.href = "/postassignment/delete?postIdx=" + postIdx;
            }else{
                // false는 취소버튼을 눌렀을 때, 취소됨
                console.log("취소되었습니다");
            }
        };
    </script>
</body>
</html>
