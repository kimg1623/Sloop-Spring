<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="/resources/css/style_post.css" rel="stylesheet">
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
                    <div class="box-size">
                        <div id="writeBtn" name="writeBtn">
                            <!--본인일때만 수정삭제 보이게 -->
                            <c:if test="${daily.memberEmail == sessionScope.loginEmail}">
                                <button class="btn_update" onclick="updateFn()">수정</button>
                                <button class="btn_delete" onclick="deleteFn()">삭제</button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <!--post button 영역 끝 -->

            <!-- post 제목 영역 시작 -->
            <div class="post-top">
                <div class="post_content_title">
                    ${daily.postDailyTitle}
                </div>
                <div class="post_content_title_bottom">
                    <div class="post_content_userAndDate">
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                ${daily.memberNickname}
                            </div>
                        </div>
                        <div class="post_content_seperator"></div>
                        <div class="post_content_registeredDate">
                            <c:choose>
                                <c:when test="${empty daily.postDailyEditDate}">
                                    <c:set var="dateToFormat" value="${daily.postDailyRegDate}" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="dateToFormat" value="${daily.postDailyEditDate}" />
                                </c:otherwise>
                            </c:choose>

                            <fmt:parseDate var="parsedDate" value="${dateToFormat}" pattern="yyyy-MM-dd HH:mm:ss" />

                            <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" var="formattedDateString" />
                            <c:out value="${formattedDateString}" />
                        </div>
                    </div>
                    <div class="post_content_view">
                        <img src="/resources/images/eye.png" alt="views" class="view_img">
                        <c:out value="${daily.postDailyHits}"/>
                    </div>
                </div>
            </div>
            <!-- post 제목 영역 끝 -->

            <!-- post 내용 시작 -->
            <div class="post_content_contents">
                <c:out value="${daily.postDailyContents}" escapeXml="false"/>
            </div>
            <!-- post 내용 끝 -->
            <p hidden="true">${daily.memberIdx}</p>
        </div><!--box size wrap-->
    </div><!--container studyGroup-->
</main>



    <script>
        //목록
        const listFn = () => {
            location.href = previousURL+"/list";
        }
        //글수정
        const updateFn = () => {
            const postIdx = '${daily.postIdx}';
            location.href = previousURL+"/update?postIdx=" + postIdx;
        }
        //글삭제
        const deleteFn = () => {
            const postIdx = '${daily.postIdx}';
            location.href = previousURL+"/delete?postIdx=" + postIdx;
        }


    </script>

</main>