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

    <h4>공부인증 게시글 수정</h4>

    <form action="/study/${studyGroupCode}/daily/${boardIdx}/update" method="post" name="updateForm">
        <input type="hidden" name="postIdx" value="${daily.postIdx}" >
        <p>${daily.postIdx}</p>
        <input type="hidden" name="memberIdx" value="${daily.memberIdx}" >

        <div class="form-group row">
            <label class="col-sm-2 control-label">제목</label>
            <div class="col-sm-3">
                <input type="text" name="postDailyTitle" value="${daily.postDailyTitle}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 control-label">내용</label>
            <div class="col-sm-5">
            <textarea name="postDailyContents" cols="30" rows="10" placeholder="내용을 입력하세요"
                      class="form-control">${daily.postDailyContents}</textarea>
            </div>
        </div>

        <input class="btn btn-primary" type="submit" value="글수정" onclick="updateReqFn()">
        <button class="btn btn-primary" onclick="listFn()">목록</button>

    </form>

</main>