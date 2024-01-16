<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 작성</title>
</head>
<body>

<%--글 작성 화면--%>
<%--@elvariable id="noticeDTO" type="kr.co.sloop.notice.domain.NoticeDTO"--%>
<form:form modelAttribute="noticeDTO" method="post" action="/study/${studyGroupCode}/notice/${boardIdx}/update">
    <!-- hidden -->
    <!-- 작성자 idx -->
    <p><form:hidden path="memberIdx"/></p>
    <!-- 작성자 email -->
    <p><form:hidden path="memberEmail"/></p>
    <!-- 작성자 닉네임 -->
    <p><form:hidden path="memberNickname"/></p>
    <!-- postIdx -->
    <p><form:hidden path="postIdx"/></p>

    <div class="category_">카테고리 선택 :
        <form:radiobutton path="categoryPostIdx" id="categoryPostNormal" name="categoryPostIdx" value="5" />
        <label for="categoryPostNormal">일반</label>

        <form:radiobutton path="categoryPostIdx" id="categoryPostImportant" name="categoryPostIdx" value="6" />
        <label for="categoryPostImportant">중요</label>

        <form:radiobutton path="categoryPostIdx" id="categoryPostEvent" name="categoryPostIdx" value="7" />
        <label for="categoryPostEvent">고민</label>
    </div>
    <div>
        <form:checkbox path="postNoticePinned" name="postNoticePinned" id="postNoticePinned" value="1"/>
        <form:hidden path="postNoticePinned" name="postNoticePinned" id="postNoticePinned_hidden" value="0"/>
        <label for="postNoticePinned">상단 고정</label>
    </div>
    <div>글 제목<br>
        <form:input path="postNoticeTitle" id="postNoticeTitle" name="postNoticeTitle"/>
    </div>

    <div>글 작성칸<br>
        <form:textarea path="postNoticeContents" id="postNoticeContents" name="postNoticeContents"/>
    </div>

    <div>
        <input type="submit" value="수정하기"/>
    </div>
</form:form>
</body>
<script>
    if(document.getElementById("input_check").checked) {
        document.getElementById("input_check_hidden").disabled = true;
    }
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
