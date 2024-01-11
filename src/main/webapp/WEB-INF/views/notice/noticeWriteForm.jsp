<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 작성</title>
</head>
<body>
<%--글 작성 화면--%>
<form:form modelAttribute="noticeDTO" method="post" action="/notice/write">
    <div class="category_">카테고리 선택 :
        <form:radiobutton path="categoryPostIdx" id="categoryPostNormal" name="categoryPostIdx" value="5" />
        <label for="categoryPostNormal">일반</label>

        <form:radiobutton path="categoryPostIdx" id="categoryPostImportant" name="categoryPostIdx" value="6" />
        <label for="categoryPostImportant">중요</label>

        <form:radiobutton path="categoryPostIdx" id="categoryPostEvent" name="categoryPostIdx" value="7" />
        <label for="categoryPostEvent">고민</label>
    </div>
    <div>
        <form:checkbox path="postNoticePinned" id="postNoticePinned" name="postNoticePinned" value="1"/>
        <label for="postNoticePinned">상단 고정</label>
    </div>
    <div>글 제목<br>
        <form:input path="postNoticeTitle" id="postNoticeTitle" name="postNoticeTitle"/>
    </div>

    <div>글 작성칸<br>
        <form:textarea path="postNoticeContents" id="postNoticeContents" name="postNoticeContents"/>
    </div>

    <div>
        <input type="submit" value="전송"/>
    </div>
</form:form>
</body>
</html>
