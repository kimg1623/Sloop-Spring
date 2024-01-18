<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<link href="/resources/css/style_post.css" rel="stylesheet">
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
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--post button 영역 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <a href="javascript:location.href=document.referrer;" class="back_arrow">
                            <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 448 512" color="808080" cursor="pointer" height="20" width="20" xmlns="http://www.w3.org/2000/svg" style="color: rgb(128, 128, 128);">
                                <path d="M257.5 445.1l-22.2 22.2c-9.4 9.4-24.6 9.4-33.9 0L7 273c-9.4-9.4-9.4-24.6 0-33.9L201.4 44.7c9.4-9.4 24.6-9.4 33.9 0l22.2 22.2c9.5 9.5 9.3 25-.4 34.3L136.6 216H424c13.3 0 24 10.7 24 24v32c0 13.3-10.7 24-24 24H136.6l120.5 114.8c9.8 9.3 10 24.8.4 34.3z"></path>
                            </svg>
                        </a>
                    </div>
                </div>
            </div>
            <!--post button 영역 끝 -->

            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="post_content_title_bottom">
                    <div class="box-size title_contents">
                        <div class="box-size">
                            <div class="box-size title_div_text">
                                <h3 class="title_text">
                                    공부인증 게시글 수정
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--board_title 끝 -->

            <form action="/study/${studyGroupCode}/daily/${boardIdx}/update" method="post" name="updateForm">
                <input type="hidden" name="postIdx" value="${daily.postIdx}" >
                <input type="hidden" name="memberIdx" value="${daily.memberIdx}" >

                <div class="daily_title">
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text" id="addon-wrapping">제목</span>
                        <input type="text" name="postDailyTitle" class="form-control" aria-describedby="addon-wrapping"
                               required value="${daily.postDailyTitle}">
                    </div>
                </div>

                <div>
                    <div class="input-group">
                        <span class="input-group-text">내용</span>
                        <textarea required name="postDailyContents" cols="30" rows="10" class="form-control">${daily.postDailyContents}</textarea>
                    </div>
                </div>

                <section class="writeSection">
                    <input type="submit"  value="수정" class="btn_update" onclick="updateReqFn()"/>
                </section>

            </form>

        </div>
    </div>
</main>