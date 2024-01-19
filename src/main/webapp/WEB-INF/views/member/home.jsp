<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>

<link href="/resources/css/style_main.css" rel="stylesheet">
<link href="/resources/css/style_post.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">


<!-- main 페이지의 컨텐츠 부분 시작 -->
<div class="container-main">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <div class="box-size title_div_text">
                            <h3 class="title_text">
                                마이 페이지
                            </h3>
                        </div>
                        <div class="box-size title_div_contents">
                            내가 가입한 스터디 그룹과 정보를 확인하세요.
                        </div>
                    </div>
                </div>
            </div>


            <div class="container row">
                <div class="col-8 study_group_list">
                    <h4 class="mypage_subtitle">
                        가입한 스터디 그룹
                    </h4>
                    <c:choose>
                        <%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
                        <c:when test="${empty myStudy}">
                            <tr>
                                <td colspan="6">등록된 글이 없습니다.</td>
                            </tr>
                        </c:when>
                        <%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
                        <c:otherwise>
                            <c:forEach items="${myStudy}" var="member" varStatus="status">
                                <a href="/study/${member.studyGroupCode}">
                                    <div class="card group_list_card">
                                        <div class="card-body post-card">
                                            <h5 class="post-card-title">
                                                <c:out value="${member.studyGroupName}"/>
                                            </h5>
                                            <div class="card-bottom">
                                                <div class="bottom_userAndDate">
                                                    <div class="bottom_user">
                                                        <div class="bottom_userName">
                                                            <c:choose>
                                                                <c:when test="${member.studyMemRole eq 'ROLE_STUDY_LEADER'}">
                                                                    스터디 리더
                                                                </c:when>
                                                                <c:when test="${member.studyMemRole eq 'ROLE_STUDY_MEMBER'}">
                                                                    스터디 멤버
                                                                </c:when>
                                                                <c:when test="${member.studyMemRole eq 'ROLE_STUDY_STANDBY'}">
                                                                    승인 대기중
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-4 myinfo_list">
                    <h4 class="mypage_subtitle">
                        내 정보 확인
                    </h4>
                    <div class="card">
                        <div class="card-body">
<%--                            <b><c:out value="${member.memberNickname}">님</b>--%>
                            <input type="button" class="submitButton" onclick="location.href='/member/update?memberIdx=${member.memberIdx}'" value="정보수정"/>
                            <input type="button" class="submitButton deleteButton" onclick="deleteMember('${member.memberIdx}')" value="회원탈퇴"/>
                        </div>
                    </div>
                </div>



<%--            <h1>${sessionScope.loginEmail}의 마이페이지 입니다.</h1>--%>
<%--            <a href="/member/update?memberIdx=${member.memberIdx}">회원 정보 수정</a><br>--%>
<%--            <a href="/member/profile?memberIdx=${member.memberIdx}">회원 프로필 수정</a><br>--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <th>이메일</th>--%>
<%--                    <td>${member.memberEmail}</td>--%>
<%--                </tr>--%>
<%--                &lt;%&ndash;    <tr>--%>
<%--                        <th>password</th>--%>
<%--                        <td>${member.memberPassword}</td>--%>
<%--                    </tr>&ndash;%&gt;--%>
<%--                <tr>--%>
<%--                    <th>닉네임</th>--%>
<%--                    <td>${member.memberNickname}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>성별</th>--%>
<%--                    <td>${member.memberGender}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>핸드폰번호</th>--%>
<%--                    <td>${member.memberPhonenumber}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>회원 분류</th>--%>
<%--                    <td>${member.memberGradeCode}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>학교명</th>--%>
<%--                    <td>${member.memberSchool}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>관심 과목</th>--%>
<%--                    <td>${member.memberSubjectCode}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>지역</th>--%>
<%--                    <td>${member.memberRegionCode}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>프로필 사진</th>--%>
<%--                    <td><img src="./image?fileName=${member.memberProfile}"/></td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--            <a href="/member/logout">로그아웃</a><br>--%>
<%--            <button onclick="deleteMember('${member.memberIdx}')">회원 탈퇴</button>--%>
<%--            <hr>--%>

        </div>
    </div>
</div>

<script>
    const deleteMember = (memberIdx) => {

        if (confirm("정말 삭제하시겠습니까?") == true) {
            console.log(memberIdx);
            location.href = "/member/delete?memberIdx=" + memberIdx;
        } else {
            return;
        }

    }
    const role_match = (studyMemRole) => {

    }
    $(document).ready(function() {
        var studyMemRole = '<c:out value="${member.studyMemRole}"/>';
        console.log("studyMemRole",studyMemRole);
    });


</script>
