<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="/resources/css/style_post.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<style>
    .alert-message {
        animation-name: fadeOut;
        animation-delay: 5000ms;
    }

    @keyframes fadeOut {
        from {
            opacity: 100%;
        }
        to {
            opacity: 0%;
        }
    }
</style>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <div class="box-size title_div_text">
                            <h3 class="title_text">
                                스터디 관리
                            </h3>
                        </div>
                    </div>
                </div>
                <p style="text-align: left">
                    스터디 정보를 수정하거나 구성원을 관리할 수 있습니다.
                </p>
                <div class="studygroup_tabmenu">
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page"
                               href="/study/${studyGroupCode}/manage/info">스터디 정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/study/${studyGroupCode}/manage/members">스터디
                                구성원</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!--board_title 끝 -->

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${studyMembers}" var="studyMember" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td>${studyMember.memberNickname}</td>
                        <c:choose>
                            <c:when test="${studyMember.studyMemRole eq 'ROLE_STUDY_LEADER'}">
                                <td>그룹 리더</td>
                            </c:when>
                            <c:when test="${studyMember.studyMemRole eq 'ROLE_STUDY_MEMBER'}">
                                <td>그룹 멤버</td>
                                <td>
                                    <input type="button" class="btn_reject" value="강제 탈퇴" onclick="onDeleteMember(${studyMember.memberIdx},${studyMember.studyGroupIdx})">
                                </td>
                            </c:when>
                            <c:when test="${studyMember.studyMemRole eq 'ROLE_STUDY_STANDBY'}">
                                <td style="color: #007fff">가입 대기</td>
                                <td>
                                    <input type="submit" class="btn_approve" value ="승인" onclick="onApproveMember(${studyMember.memberIdx},${studyMember.studyGroupIdx})">
                                    <input type="button" class="btn_reject" value="거절" onclick="onDeleteMember(${studyMember.memberIdx},${studyMember.studyGroupIdx})">
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
<script>
    onApproveMember = (memberIdx, studyGroupIdx) => {
        var obj = new Object();
        obj.memberIdx = memberIdx;
        obj.studyGroupIdx = studyGroupIdx;
        var jsonData = JSON.stringify(obj);

        $.ajax({
            type: "POST",
            url: "./members/role",
            data: JSON.stringify ({
                memberIdx : memberIdx,
                studyGroupIdx : studyGroupIdx
            }),
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
                alert("권한 변경 성공");
                console.log("권한 변경 성공");
                console.log(data);
                location.reload(true);
            },
            error: function () {
                alert("권한 변경 실패");
                console.log("권한 변경 실패");
                location.reload(true);
            }
        });
    }
</script>
