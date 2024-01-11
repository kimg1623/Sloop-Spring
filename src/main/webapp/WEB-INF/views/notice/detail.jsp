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

<table border="1">
    <tr>
        <td>분류</td>
        <td><c:out value="${noticeDTO.postIdx}"/></td>
    </tr>
    <tr>
        <td>카테고리 이름</td>
        <td><c:out value="${noticeDTO.categoryPostName}"/></td>
    </tr>
    <tr>
        <td>제목</td>
        <td><c:out value="${noticeDTO.postNoticeTitle}"/></td>
    </tr>
    <tr>
        <td>작성자</td>
        <td><c:out value="${noticeDTO.memberNickname}"/></td>
    </tr>
    <tr>
        <td>작성일</td>
        <td><c:out value="${noticeDTO.postNoticeRegDate}"/>
        <c:if test="${not empty noticeDTO.postNoticeEditDate}">
            <c:out value="(edited)"/>
        </c:if>
        </td>
    </tr>
    <tr>
        <td>조회수</td>
        <td><c:out value="${noticeDTO.postNoticeHits}"/></td>
    </tr>
    <tr>
        <td>내용</td>
        <td><c:out value="${noticeDTO.postNoticeContents}"/></td>
    </tr>
</table>
<button onclick="findNotice()">목록</button>
<c:if test="${noticeDTO.memberEmail == sessionScope.loginEmail}">
    <button onclick="updateNotice('${noticeDTO.postIdx}')">수정</button>
    <button onclick="deleteNotice('${noticeDTO.postIdx}')">삭제</button>
</c:if>
</body>
<script>
    // 목록으로 돌아가기
    const findNotice = () => {
        location.href = "/notice/list";
    };

    // 글 수정하기
    const updateNotice = (postIdx) => {
        location.href = "/notice/update?postIdx=" + postIdx;
    };

    // 글 삭제하기
    const deleteNotice = (postIdx) => {
        // location.href = "/postforum/delete?postIdx=" + postIdx;

        if (confirm("삭제하시겠습니까?") == true){
            //true는 확인버튼을 눌렀을 때 코드 작성
            console.log("완료되었습니다.");
            location.href = "/notice/delete?postIdx=" + postIdx;
        }else{
            // false는 취소버튼을 눌렀을 때, 취소됨
            console.log("취소되었습니다");
        }
    };
</script>
</html>
