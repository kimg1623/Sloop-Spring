<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        location.href = "/study/${studyGroupCode}/notice/${boardIdx}/list";
    };

    // 글 수정하기
    const updateNotice = (postIdx) => {
        location.href = "/study/${studyGroupCode}/notice/${boardIdx}/update?postIdx=" + postIdx;
    };

    // 글 삭제하기
    const deleteNotice = (postIdx) => {
        // location.href = "/postforum/delete?postIdx=" + postIdx;

        if (confirm("삭제하시겠습니까?") == true){
            //true는 확인버튼을 눌렀을 때 코드 작성
            console.log("완료되었습니다.");
            location.href = "/study/${studyGroupCode}/notice/${boardIdx}/delete?postIdx=" + postIdx;
        }else{
            // false는 취소버튼을 눌렀을 때, 취소됨
            console.log("취소되었습니다");
        }
    };

    const currentURL = window.location.href;

    // URL에서 studyGroupCode와 boardIdx 파라미터 추출하기
    const urlParams = new URLSearchParams(currentURL);
    const studyGroupCode = urlParams.get('studyGroupCode');
    const boardIdx = urlParams.get('boardIdx');

    console.log("studyGroupCode:", studyGroupCode);
    console.log("boardIdx:", boardIdx);

    // 이전 URL 생성 예시
    const previousURL = '/study/${studyGroupCode}/notice/${boardIdx}';
</script>
</html>
