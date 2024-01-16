<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>스터디 개설</title>
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
                        $('select[name=selectOption2_region]').html(sItemHtml);

                        $('select[name=selectOption2_region] option').each(function(idx, item) {
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

                $('select[name=selectOption2_region] option').each(function(idx, item) {
                    if ($(this).data('class') == classVal || $(this).val() == '') { // 2단계 option의 data-class값과 1단계 value값이 같다면
                        $(this).show(); // option 보이기
                    } else {
                        $(this).hide(); // option 숨기기
                    }
                });

            })

        })()
    </script>
</head>
<body>

    <h1 class="display-3">스터디 개설</h1>


    <div class="container">
        <form:form modelAttribute="StudyGroupDTO" class="form-horizontal" method="post" action="/study/add">
            <fieldset>
                <legend>${addTitle}</legend> <!-- 모델 속성의 이름 addTitle 출력 -->
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
                        <form:textarea path="studyGroupRegionCode" cols="50" rows="2" class="form-control"/>
                    </div>
                </div>
                <!-- ajax로 동적 select 박스 구현 test -->
                <div class="form-group row">
                    <label>option1</label>
                    <select name="selectOption1_region"></select>
                    <label>option2</label>
                    <select name="selectOption2_region"></select>
                </div>
                <!-- ajax로 동적 select 박스 구현 test 끝-->

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
                        <input type="submit" class="btn btn-primary" value ="등록"/>
                    </div>
                </div>
            </fieldset>
        </form:form>
    </div>
</body>
</html>