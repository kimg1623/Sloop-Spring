<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 2024/01/09
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>과제 게시판: 게시글 작성</title>
    <!-- jquery cdn -->
    <!-- <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script> -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <!-- ckeditor -->
    <script src="/resources/ckeditor/ckeditor.js"></script>
    <!-- date picker cdn -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <script>
        $(function() {
            $('#assignmentBeginDate').datepicker();
            $('#assignmentEndDate').datepicker();
            /*
            $(".datepicker").setDefaults({
                dateFormat: 'yy-mm-dd' // Input Display Format 변경
                ,showOtherMonths: true // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
                ,showMonthAfterYear: true // 년도 먼저 나오고, 뒤에 월 표시
                ,changeYear: true // 콤보박스에서 년 선택 가능
                ,changeMonth: true // 콤보박스에서 월 선택 가능
                ,showOn: "both" // button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
                ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" // 버튼 이미지 경로       ,buttonImageOnly: true // 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                ,buttonText: "선택" // 버튼에 마우스 갖다 댔을 때 표시되는 텍스트
                ,yearSuffix: "년" // 달력의 년도 부분 뒤에 붙는 텍스트
                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] // 달력의 월 부분 텍스트
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 달력의 월 부분 Tooltip 텍스트
                ,dayNamesMin: ['일','월','화','수','목','금','토'] // 달력의 요일 부분 텍스트
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] // 달력의 요일 부분 Tooltip 텍스트
                ,minDate: "-1Y" // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                ,maxDate: "+1Y" // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
            });
            */


            $('#assignmentBeginDate').datepicker('setDate', 'today');
            $('#assignmentEndDate').datepicker('setDate', '+7D');
        });

    </script>

    <!-- <link rel="stylesheet" href="/resources/datepicker/style.css"> -->

  <!-- <script src="ckeditor.js"></script> -->
</head>
<body>

<!-- 글 작성 화면 -->
<div class="mb-3">
    <%--@elvariable id="postAssignmentDTO" type="kr.co.sloop.postAssignment.domain.PostAssignmentDTO"--%>
    <form:form modelAttribute="postAssignmentDTO" method="post" action="/postassignment/write">
        <!-- 글 제목 -->
        <p><form:input path="postAssignmentTitle" autofocus="true" placeholder="제목"/></p>
        <p><form:errors path="postAssignmentTitle"/> </p>

        <!-- 글 내용 (에디터) -->
        <p><form:textarea path="postAssignmentContents" class="form-control" id="postAssignmentContents" name="postAssignmentContents" rows="3"></form:textarea></p>
        <p><form:errors path="postAssignmentContents"/> </p>
        <script>
            var ckeditor_config = {
                width: "100%",
                height:"400px",
                image_previewText: '',
                resize_enabled : false,
                enterMode : CKEDITOR.ENTER_BR,
                shiftEnterMode : CKEDITOR.ENTER_P,
                filebrowserUploadUrl : "/postassignment/upload-image"
        };

        CKEDITOR.replace("postAssignmentContents", ckeditor_config);

        // 이미지 업로드가 끝나고 실행하는 함수
        CKEDITOR.on( 'dialogDefinition', function( ev ) {
            // Take the dialog name and its definition from the event data.
            let dialogName = ev.data.name;
            let dialogDefinition = ev.data.definition;

            let uploadTab = dialogDefinition.getContents( 'Upload' );
            let uploadButton = uploadTab.get('uploadButton');

            uploadButton['filebrowser']['onSelect'] = function( fileUrl, errorMessage ) {

            }

        });
        </script>

        <!-- 첨부파일 -->


        <!-- 과제 -->
        <!-- 과제 시작일시 -->
        <input type="text" name="assignmentBeginDate" id="assignmentBeginDate" class="datepicker"> - <input type="text" name="assignmentEndDate" id="assignmentEndDate" class="datepicker"><br/>
        <input type="button" name="cancelBtn" id="cancelBtn" value="취소"><br/>
        <!-- 과제 마감일시 -->

        <!-- 과제 만점 점수 설정 -->
        <p><form:input path="assignmentScore" type="number" id="assignmentScore" name="assignmentScore" rows="3"></form:input></p>
        <p><form:errors path="assignmentScore"/> </p>

        <!-- 과제 대상 설정 -->


        <!-- 작성하기 버튼 -->
        <input type="submit" value="작성하기">
    </form:form>
</div>



</body>
</html>
