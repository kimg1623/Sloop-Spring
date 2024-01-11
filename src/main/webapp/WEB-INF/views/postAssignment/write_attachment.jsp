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
        // 기본 실행 함수
        $(function () {
            /* 파일 업로드 */
            // input file 파일 첨부시 fileCheck 함수 실행
            $("#attachments").on("change", fileCheck);



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


        <input type="submit" value="작성하기">
    </form:form>


    <!-- 첨부파일 -->
    <!-- 첨부파일 업로드 버튼 -->
    <div class="uploadAttaachmentDiv">
        <input type="file" name="attachments" id="attachments" multiple>
    </div>
    <!-- 업로드된 첨부파일 미리보기 -->
    <div class="uploadAttachmentResult">
        <ul></ul>
    </div>

    <button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
    <input id="input_file" multiple="multiple" type="file" style="display:none;">
</div>
</body>
</html>
