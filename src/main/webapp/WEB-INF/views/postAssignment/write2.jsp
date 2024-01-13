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
    <title>과제 게시판: 게시글 작성 _ 첨부파일 기능 구현</title>
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
    <!-- bootstrap cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <script>
        // input file 파일 첨부시 fileCheck 함수 실행
        $(document).ready(function(){
            $("#input_file").on("change", fileCheck);
        });

        // 파일 현재 필드 숫자 totalCount랑 비교값
        let fileCount = 0;
        // 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
        let totalCount = 10;
        // 파일 고유넘버
        let fileNum = 0;
        // 첨부파일 배열
        let content_files = new Array();

        // 파일 개수와 크기 검토 및 화면에 출력(업로드한 파일 목록 영역에)
        function fileCheck(e) {
            let files = e.target.files;

            // 파일 배열 담기
            let filesArr = Array.prototype.slice.call(files);

            // 파일 개수 확인 및 제한
            if (fileCount + filesArr.length > totalCount) {
                alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
                return;
            } else {
                fileCount = fileCount + filesArr.length;
            }

            // 각각의 파일 크기 확인과 제한 후, 배열 담기
            filesArr.forEach(function (f) {
                let reader = new FileReader();
                reader.onload = function (e) {
                    // 파일 크기 검사 (25MB 미만만 업로드 가능)
                    if(f.size >= 2500000){
                        alert("25MB 미만 파일만 첨부할 수 있습니다.");
                        return;
                    }

                    content_files.push(f);

                    // 화면에 출력
                    $('#articlefileChange').append(
                        '<div id="file' + fileNum + '" onclick="fileDelete(\'file' + fileNum + '\')">'
                        + '<font style="font-size:12px">' + f.name + '</font>'
                        + '<img src="/resources/images/postAssignment/minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>'
                        + '<div/>'
                    );
                    fileNum ++;
                };
                reader.readAsDataURL(f);
            });
            // 파일 선택 버튼을 초기화 한다.
            $("#input_file").val("");
        };


        // 파일 부분 삭제 함수
        function fileDelete(fileNum){
            let no = fileNum.replace(/[^0-9]/g, "");
            content_files[no].is_delete = true;
            $('#' + fileNum).remove();
            fileCount --;
        };

        // 파일 업로드
        function registerAction(){
            let status = false;

            let form = $("form")[0];
            let formData = new FormData(form);
            for (let x = 0; x < content_files.length; x++) {
                // 삭제 안 한 것만 담아 준다.
                if(!content_files[x].is_delete){
                    formData.append("article_file", content_files[x]);
                }
            }

            // 파일업로드 multiple ajax처리

            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "/postassignment/uploadAttachmentsUsingAjax",
                data : formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(JSON.parse(data)['result'] == "TYPEERROR"){
                        alert("허용되는 확장자는 xls,xlsx,txt,png,jpg,jpeg,html,htm,mpg,mp4,mp3,pdf,zip 입니다.");
                        status = false;
                        return false;
                    }else {
                        status = true;
                        return true;
                    }
                },
                error: function (data) {
                    alert("서버 오류로 지연되고 있습니다. 잠시 후 다시 시도해 주시기 바랍니다.");
                    status = false;
                    return false;
                }
            });

            return status;
        };
    </script>

</head>
<body>

<!-- 글 작성 화면 -->
<div class="mb-3">
    <%--@elvariable id="postAssignmentDTO" type="kr.co.sloop.postAssignment.domain.PostAssignmentDTO"--%>
    <form:form modelAttribute="postAssignmentDTO" method="post" onsubmit="return registerAction();" action="/postassignment/write" enctype="multipart/form-data">
        <!-- 글 제목 -->
        <p><form:input path="postAssignmentTitle" autofocus="true" placeholder="제목"/></p>
        <p><form:errors path="postAssignmentTitle"/></p>

        <!-- 첨부 파일 추가 -->
        <input id="input_file" multiple="multiple" type="file" class="form-control">
        <span style="font-size:10px; color: gray;">※ 첨부파일은 개당 25MB, 총 125MB까지 업로드 가능합니다.</span>

        <!-- 업로드한 파일 목록 -->
        <div class="data_file_txt" id="data_file_txt" style="margin:40px;">
            <span>첨부 파일</span>
            <br />
            <div id="articlefileChange">
            </div>
        </div>
        <input type="submit" value="작성하기">
    </form:form>

</div>
</body>
</html>
