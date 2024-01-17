<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 작성</title>
</head>
<body>
<%--글 작성 화면--%>
<%--@elvariable id="noticeDTO" type="kr.co.sloop.notice.domain.NoticeDTO"--%>
<form:form modelAttribute="noticeDTO" method="post" action="/study/${studyGroupCode}/notice/${boardIdx}/write">
    <div class="category_">카테고리 선택 :
        <form:radiobutton path="categoryPostIdx" id="categoryPostNormal" value="5" required="true"/>
        <label for="categoryPostNormal">일반</label>
        <form:errors path="categoryPostIdx" cssStyle="color: red"/>
        <form:radiobutton path="categoryPostIdx" id="categoryPostImportant" value="6" required="true"/>
        <label for="categoryPostImportant">중요</label>
        <form:errors path="categoryPostIdx" cssStyle="color: red"/>
        <form:radiobutton path="categoryPostIdx" id="categoryPostEvent" value="7" required="true"/>
        <label for="categoryPostEvent">이벤트</label>
            <form:errors path="categoryPostIdx" cssStyle="color: red"/>
    <div>
        <form:checkbox path="postNoticePinned" id="postNoticePinned" value="1"/>
        <form:hidden path="postNoticePinned" id="postNoticePinned_hidden" value="0"/>
        <label for="postNoticePinned">상단 고정</label>
    </div>
    <div>글 제목<br>
        <form:input path="postNoticeTitle" required="true" maxlength="100"/>
        <form:errors path="postNoticeTitle" cssStyle="color: red"/>
    </div>

    <div>글 작성칸<br>
        <form:textarea path="postNoticeContents" required="true" maxlength="10000"/>
        <form:errors path="postNoticeContents" cssStyle="color: red"/>
    </div>

    <div>
        <input type="submit" value="전송"/>
    </div>
</form:form>
</body>
<script>

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
