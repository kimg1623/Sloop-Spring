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
        // 선택할 수 있는 시간
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

        function DatetimepickerDefaults(opts) {
            return $.extend({}, {
                locale: 'kr',
                format: 'Y-M-D H:m',
                inline: false,
                sideBySide: true,
                allowTimes: allowTimesList
            }, opts);
        };

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

        $(function () {
            // 과제 시작일시
            $('#assignmentBeginDate_input').datetimepicker(
                DatetimepickerDefaults({
                    minDate: 0,
                    onShow: function (ct) {
                        this.setOptions({
                            maxDate: jQuery('#assignmentEndDate_input').val() ? jQuery('#assignmentEndDate_input').val() : false
                        })
                    }
                })
            );


            // 과제 마감일시
            $('#assignmentEndDate_input').datetimepicker(
                DatetimepickerDefaults({
                    onShow: function (ct) {
                        this.setOptions({
                            minDate: jQuery('#assignmentBeginDate_input').val() ? jQuery('#assignmentBeginDate_input').val() : 0
                        })
                    }
                })
            );


        });
    </script>

</head>
<body>

<!-- 글 작성 화면 -->
<div class="mb-3">
    <%--@elvariable id="postAssignmentDTO" type="kr.co.sloop.postAssignment.domain.PostAssignmentDTO"--%>
    <form:form modelAttribute="postAssignmentDTO" method="post" action="/postassignment/write">
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

        <!-- 첨부파일 -->


        <!-- 과제 -->
        <!-- 과제 시작일시 -->
        <label for="assignmentBeginDate">시작일시</label>
        <div id="assignmentBeginDate">
            <input id="assignmentBeginDate_input" class="assignmentBeginDate_input" type="text"/>
        </div>

        <!-- 과제 마감일시 -->
        <label for="assignmentEndDate">마감일시</label>
        <div id="assignmentEndDate">
            <input id="assignmentEndDate_input" class="assignmentEndDate_input" type="text"/>
        </div>

        <!-- 과제 만점 점수 설정 -->
        <p><form:input path="assignmentScore" type="number" id="assignmentScore" name="assignmentScore"
                       rows="3"></form:input></p>
        <p><form:errors path="assignmentScore"/></p>

        <!-- 과제 대상 설정 -->


        <!-- 작성하기 버튼 -->
        <input type="submit" value="작성하기">
    </form:form>
</div>


</body>
</html>
