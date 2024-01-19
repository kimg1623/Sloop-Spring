<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="/resources/css/style_post.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

<script>

    function onDelete(){
        if(confirm("정말로 폐쇄하시겠습니까?"))
            location.href='/study/${studyGroupCode}/delete';
        else;
    }

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
                    <ul class="nav nav-tabs" id="manageTab">
                        <li class="nav-item">
                            <a class="nav-link active" href="/study/${studyGroupCode}/manage/info">스터디 정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/study/${studyGroupCode}/manage/members">스터디 구성원</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!--board_title 끝 -->

            <form:form modelAttribute="StudyGroup" class="form-horizontal" method="post" action="/study/${StudyGroup.studyGroupCode}/manage/info">
                <!-- 스터디 그룹 index -->
                <form:hidden path="studyGroupIdx"/>
                <!-- 스터디그룹 코드 -->
                <form:hidden path="studyGroupCode"/>
                <div class="row mb-3">
                    <label for="studyGroupName" class="col-sm-3 col-form-label">스터디 이름</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupName" class="form-control" id="studyGroupName"/>
                    </div>
                </div>
                <fieldset class="row mb-3">
                    <label class="col-form-label col-sm-3">스터디 방식</label>
                    <div class="col-sm-8 option_flex">
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="1" id="studyGroupMethod1"/>
                            <label class="form-check-label" for="studyGroupMethod1">
                                오프라인
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="2" id="studyGroupMethod2"/>
                            <label class="form-check-label" for="studyGroupMethod2">
                                온라인
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="3" id="studyGroupMethod3"/>
                            <label class="form-check-label" for="studyGroupMethod3">
                                온/오프라인
                            </label>
                        </div>
                    </div>
                </fieldset>
                <div class="row mb-3">
                    <label for="studyGroupGradeCode" class="col-sm-3 col-form-label">학년</label>
                    <div class="col-sm-8">
                        <form:select path="studyGroupGradeCode" id="studyGroupGradeCode" class="form-select">
                            <form:option value="100">초등학교</form:option>
                            <form:option value="200">중학교</form:option>
                            <form:option value="300">고등학교</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupSubjectCode" class="col-sm-3 col-form-label">과목</label>
                    <div class="col-sm-8">
                        <form:select path="studyGroupSubjectCode" id="studyGroupSubjectCode" class="form-select">
                            <form:option value="101">국어</form:option>
                            <form:option value="102">수학</form:option>
                            <form:option value="103">영어</form:option>
                            <form:option value="104">사회</form:option>
                            <form:option value="105">과학</form:option>
                            <form:option value="106">기타</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupRegionCode" class="col-sm-3 col-form-label">과목</label>
                    <div class="col-sm-8">
                        <form:select path="studyGroupRegionCode" id="studyGroupRegionCode" class="form-select">
                            <form:option value="101">서울</form:option>
                            <form:option value="102">경기</form:option>
                            <form:option value="103">강원</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupNum" class="col-sm-3 col-form-label">모집 인원</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupNum" class="form-control" id="studyGroupNum"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupDuedate" class="col-sm-3 col-form-label">모집 마감일</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupDuedate" type="date" class="form-control" id="studyGroupDuedate"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupStartdate" class="col-sm-3 col-form-label">스터디 시작일</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupStartdate" type="date" class="form-control" id="studyGroupStartdate"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupIntrotxt" class="col-sm-3 col-form-label">스터디 소개글</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupIntrotxt" class="form-control" id="studyGroupIntrotxt"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="studyGroupOImg" class="col-sm-3 col-form-label">스터디 이미지</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupOImg" class="form-control" id="studyGroupOImg"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="infoFormBtn">
                        <input type="submit" class="btn_edit" value ="변경사항 저장"/>
                    </div>
                </div>
                <div class="alert-message">
                    <c:if test="${resultMessage ne null}">
                        ${resultMessage} - 5초 뒤 사라지게 css 적용 예정
                    </c:if>
                </div>
            </form:form>

            <div class="row mb-6">
                <div class="card group_info_card">
                    <div class="group_info_card_txt">
                        <h6>스터디 그룹 폐쇄하기</h6>
                        그룹을 폐쇄하면 모든 데이터가 사라집니다.
                    </div>
                    <div class="col-sm-3">

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>