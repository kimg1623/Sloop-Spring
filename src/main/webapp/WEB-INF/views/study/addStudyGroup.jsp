<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="/resources/css/style_post.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script>
        (function (){
            let categoryRegionList_option;
            let sClassHtml = '<option value="">선택하세요.</option>';
            let sItemHtml = '<option value="">선택하세요.</option>';

            // 서버로 댓글 데이터 전송
            $.ajax({
                type: "PUT",
                url: "/study/add",
                dataType: "json",
                success: function(categoryRegionList) { // 지역 카테고리 JSONArray로 response
                    console.log(categoryRegionList);

                    $.each(categoryRegionList, function(index, item){ // categoryRegionList 반복문 돌리기
                        if(item.categoryRegionTier == 1){ // 1단계 카테고리라면 sClassHtml = selectOption1_region의 option 으로 하기
                            sClassHtml += "<option value="+item.categoryRegionCode+">"+item.categoryRegionName+"</option>";
                        }
                        else if(item.categoryRegionTier == 2){ // 2단계 카테고리라면 sItemHtml = selectOption2_region의 option 으로 하기
                            sItemHtml += "<option value="+item.categoryRegionCode+" data-class="+item.categoryRegionParent+">"+item.categoryRegionName+"</option>";
                        }

                        $('select[name=selectOption1_region]').html(sClassHtml);
                        $('select[name=studyGroupRegionCode]').html(sItemHtml);

                        $('select[name=studyGroupRegionCode] option').each(function(idx, item) {
                            if ($(this).val() == '') return true;
                            $(this).hide();
                        })
                    })
                },
                error: function () {
                    alert("카테고리 불러오기 실패");
                    console.log("카테고리 불러오기 실패");
                }
            });

            $(document).on('change', 'select[name=selectOption1_region]', function() {
                const classVal = $(this).val(); // 선택한 option의 value

                $('select[name=studyGroupRegionCode] option').each(function(idx, item) {
                    if ($(this).data('class') == classVal || $(this).val() == '') { // 2단계 option의 data-class값과 1단계 value값이 같다면
                        $(this).show(); // option 보이기
                    } else {
                        $(this).hide(); // option 숨기기
                    }
                });

            })

        })()
    </script>

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
                            스터디 개설
                        </h3>
                    </div>
                    <div class="box-size title_div_contents">
                        개설할 스터디 그룹의 정보를 입력해주세요.<br>
                        <b>스터디 그룹 이름을 제외</b>한 모든 정보는 변경이 가능합니다.
                    </div>
                </div>
            </div>
        </div>
        <!--board_title 끝 -->

        <form:form modelAttribute="StudyGroupDTO" class="form-horizontal" method="post" action="/study/add">
            <fieldset>
                <legend>${addTitle}</legend> <!-- 모델 속성의 이름 addTitle 출력 -->
                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">스터디 이름</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupName" class="form-control"/>
                    </div>
                </div>

                <fieldset class="row mb-3">
                    <label class="col-form-label col-sm-3">스터디 방식</label>
                    <div class="col-sm-8 option_flex">
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="1" />
                            <label class="form-check-label">
                                온라인
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="2" />
                            <label class="form-check-label">
                                오프라인
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="studyGroupMethod" value="3" />
                            <label class="form-check-label">
                                온/오프라인
                            </label>
                        </div>
                    </div>
                </fieldset>

                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">학년</label>
                    <div class="col-sm-8">
                        <form:select path="studyGroupGradeCode" class="form-select">
                            <form:option value="101">초등1학년</form:option>
                            <form:option value="102">초등2학년</form:option>
                            <form:option value="103">초등3학년</form:option>
                            <form:option value="104">초등4학년</form:option>
                            <form:option value="105">초등5학년</form:option>
                            <form:option value="106">초등6학년</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">과목</label>
                    <div class="col-sm-8">
                        <form:select path="studyGroupSubjectCode" class="form-select">
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
                    <label class="col-sm-3 col-form-label">지역</label>
                    <div class="col-md-4">
                        <select class="form-select" name="selectOption1_region"></select>
                    </div>
                    <div class="col-md-4">
                        <select class="form-select" name="studyGroupRegionCode"></select>
                    </div>
<%--                    <form:hidden path="studyGroupRegionCode" />--%>
                </div>
                <!-- ajax로 동적 select 박스 구현 test 끝-->

                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">모집인원</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupNum" class="form-control"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">모집마감일</label>
                    <div class="col-sm-3">
                        <form:input path="studyGroupDuedate" type="date" class="form-control"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">스터디 시작일</label>
                    <div class="col-sm-3">
                        <form:input path="studyGroupStartdate" type="date" class="form-control"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">소개글</label>
                    <div class="col-sm-8">
                        <form:input path="studyGroupIntrotxt" class="form-control"/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-3 col-form-label">스터디 이미지</label>
                    <div class="col-sm-3">
                        <form:input path="studyGroupOImg" class="form-control"/>
                    </div>
                </div>
                <section class="writeSection">
                        <input type="submit" class="addStudyButton" value ="등록"/>
                </section>
            </fieldset>
        </form:form>
    </div>
    </div>
</div>
