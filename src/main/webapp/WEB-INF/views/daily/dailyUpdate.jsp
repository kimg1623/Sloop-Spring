<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<script>

    // 현재 페이지 URL 가져오기
    const currentURL = window.location.href;

    // URL에서 studyGroupCode와 boardIdx 파라미터 추출하기
    const urlParams = new URLSearchParams(currentURL);
    const studyGroupCode = urlParams.get('studyGroupCode');
    const boardIdx = urlParams.get('boardIdx');

    console.log("studyGroupCode:", studyGroupCode);
    console.log("boardIdx:", boardIdx);

    // 이전 URL 생성 예시
    const previousURL = `/study/${studyGroupCode}/daily/${boardIdx}`;

    console.log("Previous URL:", previousURL);

    const updateReqFn = () => {
        document.updateForm.submit();
    }

    const listFn = () => {
        location.href = "/daily";
    }

</script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

    <h4 class="dailyWrite-title">공부인증 게시글 수정</h4>

    <form action="/study/${studyGroupCode}/daily/${boardIdx}/update" method="post" name="updateForm">
        <input type="hidden" name="postIdx" value="${daily.postIdx}" >
        <input type="hidden" name="memberIdx" value="${daily.memberIdx}" >


        <div class="daily_title">
            <div class="input-group flex-nowrap">
            <span class="input-group-text" id="addon-wrapping">제목</span>
            <input type="text" name="postDailyTitle" class="form-control" aria-describedby="addon-wrapping"
                   value="${daily.postDailyTitle}">
            </div>
        </div>

        <div class="detail-border"></div>

        <div>
            <div class="input-group">
                <span class="input-group-text">내용</span>
            <textarea name="postDailyContents" cols="30" rows="10" class="form-control">
                ${daily.postDailyContents}</textarea>
            </div>
        </div>

        <br>

        <input class="writeUpdate" type="submit" value="글수정" onclick="updateReqFn()">
        <button class="writeList" onclick="listFn()">목록</button>

    </form>

</main>