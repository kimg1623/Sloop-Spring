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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <!-- ckeditor -->
    <script src="/resources/ckeditor/ckeditor.js"></script>
    <!-- date time picker -->
    <link rel="stylesheet" href="/resources/dateTimePicker/datetimepicker-master/jquery.datetimepicker.css"/>
    <script src="/resources/dateTimePicker/datetimepicker-master/jquery.js"></script>
    <script src="/resources/dateTimePicker/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>

    <script>
        // date time picker 선택할 수 있는 시각
        let allowTimesList = [
            '00:00', '00:10', '00:20', '00:30', '00:40', '00:50',
            '01:00', '01:10', '01:20', '01:30', '01:40', '01:50',
            '02:00', '02:10', '02:20', '02:30', '02:40', '02:50',
            '03:00', '03:10', '03:20', '03:30', '03:40', '03:50',
            '04:00', '04:10', '04:20', '04:30', '04:40', '04:50',
            '05:00', '05:10', '05:20', '05:30', '05:40', '05:50',
            '06:00', '06:10', '06:20', '06:30', '06:40', '06:50',
            '07:00', '07:10', '07:20', '07:30', '07:40', '07:50',
            '08:00', '08:10', '08:20', '08:30', '08:40', '08:50',
            '09:00', '09:10', '09:20', '09:30', '09:40', '09:50',
            '10:00', '10:10', '10:20', '10:30', '10:40', '10:50',
            '11:00', '11:10', '11:20', '11:30', '11:40', '11:50',
            '12:00', '12:10', '12:20', '12:30', '12:40', '12:50',
            '13:00', '13:10', '13:20', '13:30', '13:40', '13:50',
            '14:00', '14:10', '14:20', '14:30', '14:40', '14:50',
            '15:00', '15:10', '15:20', '15:30', '15:40', '15:50',
            '16:00', '16:10', '16:20', '16:30', '16:40', '16:50',
            '17:00', '17:10', '17:20', '17:30', '17:40', '17:50',
            '18:00', '18:10', '18:20', '18:30', '18:40', '18:50',
            '19:00', '19:10', '19:20', '19:30', '19:40', '19:50',
            '20:00', '20:10', '20:20', '20:30', '20:40', '20:50',
            '21:00', '21:10', '21:20', '21:30', '21:40', '21:50',
            '22:00', '22:10', '22:20', '22:30', '22:40', '22:50',
            '23:00', '23:10', '23:20', '23:30', '23:40', '23:50'
        ];

        // date time picker 기본 설정
        function DatetimepickerDefaults(opts) {
            return $.extend({}, {
                locale: 'kr',
                format: 'Y-m-d H:i',
                inline: false,
                sideBySide: true,
                allowTimes: allowTimesList
            }, opts);
        };

        // 기본 실행 함수
        $(function () {
            /* datetimepicker */
            // allowTimesList 목록 중에서 현재 시각보다 크거나 같은 가장 가까운 시각을 지정한다.
            let today = new Date();
            let year = today.getFullYear();
            let month = today.getMonth() + 1;
            month = String(month).padStart(2, "0");
            let date = today.getDate();
            date = String(date).padStart(2, "0");

            let defaultEndDate = year + "-" + month+ "-" + date;
            let hor = today.getHours();
            let min = Math.ceil(today.getMinutes()/10) * 10; // 현재 분을 10의 자리에서 올림
            // 60분을 넘어가면 안 됨
            min = min % 60;
            if(min == 0){
                hor = hor + 1;
            }
            hor = String(hor).padStart(2, "0"); // 0 padding
            min = String(min).padStart(2, "0"); // 0 padding
            let time = hor+":"+min; // 현재 시각
            console.log(defaultEndDate + " " + time);
            // 과제 마감일시 date time picker
            // 최소 날짜와 시간을 현재 시각으로 설정한다.
            $('#assignmentEndDate_input').datetimepicker(
                DatetimepickerDefaults({
                    minDate: 0,
                    defaultTime: time,
                    formatTime:'H:i'
                })
            );

            // 과제 마감일시 기본 설정
            document.getElementById("assignmentEndDate_input").value = defaultEndDate + " " + time;
            // $('#assignmentEndDate_input').value(defaultEndDate + " " + time);


            /* 파일 업로드 */
            // 업로드 첨부파일 미리보기 영역
            let uploadAttachmentResult = $(".uploadAttachmentResult ul");

            // json 데이터를 받아서 해당 파일의 이름을 페이지에 출력한다.
            function showUploadedFile(uploadAttachmentResultsArray){
                let str = "";

                $(uploadAttachmentResultsArray).each(function(i, obj){
                    str += "<li>" + obj.fileName + "</li>";
                });

                // 업로드 첨부파일 미리보기 영역에 파일 이름을 출력한다.
                uploadAttachmentResult.append(str);
            };

            // 파일 업로드 버튼을 누르면 파일이 서버로 업로드 된다.
            $("#uploadAttachmentBtn").on("click", function (e){
                let formData = new FormData();
                let inputFile = $("input[name='uploadFile']");
                let files = inputFile[0].files;

                console.log(files);

                /* add filedate to formata */
                for(let i = 0; i< files.length; i++){
                    if(!checkExtension(files[i].name, files[i].size)) return false;

                    formData.append("uploadFile", files[i]);
                }

                $.ajax({
                    url: '/uploadAjaxAction',
                    processData: false,
                    contentType: false,
                    data: formData,
                    type: 'POST',
                    success: function (){
                        console.log();
                        alert("Uploaded");
                    },
                    error: function(){
                        console.log();
                        alert("error");
                    }
                });
            });
        });

        /*
        // 과제 시작일시
        $('#assignmentBeginDate_input').datetimepicker({
            locale: 'ko',
            format: 'YYYY-MM-DD HH:mm',
            inline: false,
            sideBySide: true,
            allowTimes: allowTimesList,
            minDate: 0,
            onShow: function (ct) {
                this.setOptions({
                    maxDate: jQuery('#assignmentEndDate_input').val() ? jQuery('#assignmentEndDate_input').val() : false
                })
            }
        });
         */
        // 과제 마감일시
        /*
        $('#assignmentEndDate_input').datetimepicker({
                locale: 'ko',
                format: 'YYYY-MM-DD HH:mm',
                inline: false,
                sideBySide: true,
                allowTimes: allowTimesList,
                onShow: function (ct) {
                    this.setOptions({
                        minDate: jQuery('#assignmentBeginDate_input').val() ? jQuery('#assignmentBeginDate_input').val() : false
                    })
                }
            }
        );
         */
    </script>

</head>
<body>

<!-- 글 작성 화면 -->
<div class="mb-3">
    <%--@elvariable id="postAssignmentDTO" type="kr.co.sloop.postAssignment.domain.PostAssignmentDTO"--%>
    <form:form modelAttribute="postAssignmentDTO" method="post" action="/postassignment/write" enctype="multipart/form-data">
        <!-- 글 제목 -->
        <p><form:input path="postAssignmentTitle" autofocus="true" placeholder="제목"/></p>
        <p><form:errors path="postAssignmentTitle"/></p>

        <!-- 글 내용 (에디터) -->
        <p><form:textarea path="postAssignmentContents" class="form-control" id="postAssignmentContents"
                          name="postAssignmentContents" rows="3"></form:textarea></p>
        <p><form:errors path="postAssignmentContents"/></p>
        <script>
            var ckeditor_config = {
                width: "100%",
                height: "400px",
                image_previewText: '',
                resize_enabled: false,
                enterMode: CKEDITOR.ENTER_BR,
                shiftEnterMode: CKEDITOR.ENTER_P,
                filebrowserUploadUrl: "/postassignment/upload-image"
            };

            CKEDITOR.replace("postAssignmentContents", ckeditor_config);

            // 이미지 업로드가 끝나고 실행하는 함수
            CKEDITOR.on('dialogDefinition', function (ev) {
                // Take the dialog name and its definition from the event data.
                let dialogName = ev.data.name;
                let dialogDefinition = ev.data.definition;

                let uploadTab = dialogDefinition.getContents('Upload');
                let uploadButton = uploadTab.get('uploadButton');

                uploadButton['filebrowser']['onSelect'] = function (fileUrl, errorMessage) {

                }

            });
        </script>

        <!-- 과제 -->
        <%--
        <!-- 과제 시작일시 -->
        <label for="assignmentBeginDate">시작일시</label>
        <div id="assignmentBeginDate">
            <input id="assignmentBeginDate_input" class="assignmentBeginDate_input" type="text"/>
        </div>
        --%>
        <!-- 과제 마감일시 -->
        <label for="assignmentEndDate">마감일시</label>
        <div id="assignmentEndDate">
            <form:input path="assignmentEndDateString" id="assignmentEndDate_input" class="assignmentEndDate_input" type="text"/>
        </div>
        <!-- <input type="button" onclick="console.log($('#assignmentEndDate_input').val())"> -->

        <!-- 과제 만점 점수 설정 -->
        <p><form:input path="assignmentScore" type="number" id="assignmentScore" name="assignmentScore"
                       rows="3"></form:input></p>
        <p><form:errors path="assignmentScore"/></p>

        <!-- 과제 대상 설정 -->


        <!-- 작성하기 버튼 -->
        <input type="submit" value="작성하기">
    </form:form>


    <!-- 첨부파일 -->
    <!-- 첨부파일 업로드 버튼 -->
    <div class="uploadAttaachmentDiv">
        <input type="file" name="attachments" multiple>
    </div>
    <!-- 업로드된 첨부파일 미리보기 -->
    <div class="uploadAttachmentResult">
        <ul></ul>
    </div>
</div>
</body>
</html>
