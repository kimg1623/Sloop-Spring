<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_post.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">

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
                    <div class="box-size">
                        <div id="writeBtn" name="writeBtn">
                            <button class="btn_list" onclick="findNotice()">목록</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--post button 영역 끝 -->

            <!-- post 제목 영역 시작 -->
            <div class="post-top">
                <div class="post_content_category">
                    <c:out value="${noticeDTO.categoryPostName}"/>
                </div>
                <div class="post_content_title">
                    <c:out value="${noticeDTO.postNoticeTitle}"/>
                </div>
                <div class="post_content_title_bottom">
                    <div class="post_content_userAndDate">
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                <c:out value="${noticeDTO.memberNickname}"/>
                            </div>
                        </div>
                        <div class="post_content_seperator"></div>
                        <div class="post_content_registeredDate">
                            <fmt:formatDate value="${noticeDTO.postNoticeRegDate}" pattern="yyyy.MM.dd HH:mm"/>
                            <!-- 수정일시가 존재한다면 작성일 옆에 (edited) 표시 -->
                            <c:if test="${not empty noticeDTO.postNoticeEditDate}">
                                <c:out value="(edited)" />
                            </c:if>
                        </div>
                    </div>
                    <div class="post_content_view">
                        <img src="/resources/images/eye.png" alt="views" class="view_img">
                        <c:out value="${noticeDTO.postNoticeHits}"/>
                    </div>
                </div>
            </div>
            <!-- post 제목 영역 끝 -->

            <!-- post 내용 시작 -->
            <div class="post_content_contents">
                <c:out value="${noticeDTO.postNoticeContents}" escapeXml="false"/>
            </div>
            <!-- post 내용 끝 -->
            <p hidden="true">${noticeDTO.postIdx}</p>
            <div class="box-size buttonList">
            <div class="box-size">
                <div name="writeBtn">
                    <%-- 게시물 작성자 이메일과 session에 저장된 로그인 된 이메일이 동일할 경우에만 수정, 삭제 버튼 출력 --%>
                    <c:if test="${noticeDTO.memberEmail == sessionScope.loginEmail}">
                        <button class="btn_update" onclick="updateNotice('${noticeDTO.postIdx}')">수정</button>
                        <button class="btn_delete" onclick="deleteNotice('${noticeDTO.postIdx}')">삭제</button>
                    </c:if>
                </div>
            </div>
            </div>

        </div>
    </div>
</main>

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
</main>
