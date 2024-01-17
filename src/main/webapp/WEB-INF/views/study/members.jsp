<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="/resources/css/style_post.css" rel="stylesheet">
<style>
    .alert-message{
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
<script>
</script>

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
                            <a class="nav-link" aria-current="page" href="/study/${StudyGroup.studyGroupCode}/manage/info">스터디 정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/study/${StudyGroup.studyGroupCode}/manage/members">스터디 구성원</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!--board_title 끝 -->


                <form:form modelAttribute="StudyGroup" class="form-horizontal" method="post" action="/study/${StudyGroup.studyGroupCode}/manage/info">
                    <fieldset>
                        <legend>${addTitle}</legend> <!-- 모델 속성의 이름 addTitle 출력 -->
                        <!-- 스터디 그룹 index -->
                        <p><form:hidden path="studyGroupIdx"/></p>
                        <!-- 스터디그룹 코드 -->
                        <p><form:hidden path="studyGroupCode"/></p>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">스터디 이름</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupName" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">스터디 방식</label>
                            <div class="col-sm-3">
                                <form:radiobutton path="studyGroupMethod" value="1" /> 온라인
                                <form:radiobutton path="studyGroupMethod" value="2" /> 오프라인
                                <form:radiobutton path="studyGroupMethod" value="3" /> 온/오프라인
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">학년</label>
                            <div class="col-sm-3">
                                <form:select path="studyGroupGradeCode">
                                    <form:option value="100">초등학교</form:option>
                                    <form:option value="200">중학교</form:option>
                                    <form:option value="300">고등학교</form:option>
                                </form:select>

                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">과목</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupSubjectCode" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">지역</label>
                            <div class="col-sm-5">
                                <form:input path="studyGroupRegionCode" cols="50" rows="2" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">모집인원</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupNum" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">모집마감일</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupDuedate" type="date" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">스터디 시작일</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupStartdate" type="date" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label">소개글</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupIntrotxt" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-2 control-label">스터디 이미지</label>
                            <div class="col-sm-3">
                                <form:input path="studyGroupOImg" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-offset-2 col-sm-10" >
                                <input type="submit" class="btn btn-primary" value ="변경사항 저장"/>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
                <div class="alert-message">
                    <c:if test="${resultMessage ne null}">
                        ${resultMessage} - 5초 뒤 사라지게 css 적용 예정
                    </c:if>
                </div>
                <div>
                    스터디 그룹 폐쇄하기 <input type="button" value="그룹 폐쇄" onclick="onDelete()">
                </div>

        </div>
    </div>
</main>