<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<link href="/resources/css/style_post.css" rel="stylesheet">


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
                                    공지게시판 수정
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--board_title 끝 -->

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

    <div class="CategoryList">
        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostNormal" name="categoryPostIdx" value="5" required="true"/>
        <label for="categoryPostNormal">일반</label>
        <form:errors path="categoryPostIdx" cssStyle="color: red"/>

        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostImportant" name="categoryPostIdx" value="6" required="true"/>
        <label for="categoryPostImportant">중요</label>
        <form:errors path="categoryPostIdx" cssStyle="color: red"/>

        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostEvent" name="categoryPostIdx" value="7" required="true"/>
        <label for="categoryPostEvent">고민</label>
        <form:errors path="categoryPostIdx" cssStyle="color: red"/>
    </div>
    <div class="Pinned">
        <form:checkbox path="postNoticePinned" name="postNoticePinned" id="postNoticePinned" value="1" class="form-check-input"/>
        <form:hidden path="postNoticePinned" name="postNoticePinned" id="postNoticePinned_hidden" value="0"/>
        <label for="postNoticePinned">상단 고정</label>
    </div>


    <div class="daily_title">
        <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">제목</span>
        <form:input path="postNoticeTitle" required="true" maxlength="100"
                    class="form-control" aria-describedby="addon-wrapping"/>
        <form:errors path="postNoticeTitle" cssStyle="color: red"/>
        </div>
    </div>

    <div>
        <div class="input-group">
            <span class="input-group-text">내용</span>
        <form:textarea path="postNoticeContents" required="true" maxlength="10000"
                       cols="30" rows="10" class="form-control"
        />
        <form:errors path="postNoticeTitle" cssStyle="color: red"/>
        </div>
    </div>

    <section class="writeSection">
        <input type="submit"  value="글수정" class="btn_update" onclick="updateReqFn()"/>
    </section>
    </form:form>

        </div>
    </div>
</main>

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
