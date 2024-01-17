<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<link href="/resources/css/style_main.css" rel="stylesheet">
<!-- main 페이지의 컨텐츠 부분 시작 -->
<div class="container-main">
    <div class="studyContent_wrapper">
        <a href="/study" class="back_arrow">
            <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 448 512" color="808080" cursor="pointer" height="20" width="20" xmlns="http://www.w3.org/2000/svg" style="color: rgb(128, 128, 128);">
                <path d="M257.5 445.1l-22.2 22.2c-9.4 9.4-24.6 9.4-33.9 0L7 273c-9.4-9.4-9.4-24.6 0-33.9L201.4 44.7c9.4-9.4 24.6-9.4 33.9 0l22.2 22.2c9.5 9.5 9.3 25-.4 34.3L136.6 216H424c13.3 0 24 10.7 24 24v32c0 13.3-10.7 24-24 24H136.6l120.5 114.8c9.8 9.3 10 24.8.4 34.3z"></path>
            </svg>
        </a>
        <div class="introduce-title">
            <div class="studyContent_title">${studyGroup.studyGroupName}</div>
            <div class="studyContent_userAndDate">
                <div class="studyContent_user">
                    <img class="studyContent_userImg" src="." alt="userImg">
                    <div class="studyContent_userName">글쓴이</div>
                </div>
                <div class="studyContent_seperator"></div>
                <div class="studyContent_registeredDate">2024.01.00</div>
            </div>
        </div>

        <section class="introduce-wrapper">
            <ul class="studyInfo_studyGrid">
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">스터디 방식</span>
                    <span class="studyInfo_content">${studyGroup.studyGroupMethod}</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">그룹 구분</span>
                    <span class="studyInfo_content">
                        <span class="badge rounded-pill bg-lightblue text-dark">${studyGroup.studyGroupGrade}</span>
                    </span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">스터디 과목</span>
                    <span class="studyInfo_content">
                        <span class="badge rounded-pill bg-lightorange text-dark">${studyGroup.studyGroupSubject}</span>
                    </span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">지역 구분</span>
                    <span class="studyInfo_content">
                        <span class="badge rounded-pill bg-lightgreen text-dark">${studyGroup.studyGroupRegion}</span>
                    </span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">모집 마감</span>
                    <span class="studyInfo_content">
                        <fmt:formatDate value="${studyGroup.studyGroupDuedate}" pattern="yyyy.MM.dd"></fmt:formatDate>
                    </span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">시작 예정</span>
                    <span class="studyInfo_content">
                        <fmt:formatDate value="${studyGroup.studyGroupStartdate}" pattern="yyyy.MM.dd"></fmt:formatDate>
                    </span>
                </li>
            </ul>
        </section>

        <div class="studyContent_postContentWrapper">
            <h2 class="studyContent_postInfo">스터디 그룹 소개</h2>
            <div class="studyContent_postContent">
                ${studyGroup.studyGroupIntrotxt}
            </div>
        </div>

        <section class="sc_watch">
            <div class="lpaEYv">
                <img src="/resources/images/eye.png" alt="views" class="sc_img">
                <span class="kydRHc">${studyGroup.studyGroupHits}</span>
            </div>
        </section>

        <div class="sc_button">
            <button class="sc_join">가입신청</button>
            <button class="sc_share">공유하기</button>
        </div>
    </div>
</div>