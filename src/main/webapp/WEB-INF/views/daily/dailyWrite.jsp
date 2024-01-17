<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">

<script>
    // 현재 페이지 URL 가져오기
    const currentURL = window.location.href;

    // URL에서 studyGroupCode와 boardIdx 파라미터 추출하기
    const urlParams = new URLSearchParams(currentURL);
    const studyGroupCode = urlParams.get('studyGroupCode');
    const boardIdx = urlParams.get('boardIdx');

    console.log("studyGroupCode:", studyGroupCode);
    console.log("boardIdx:", boardIdx);

    // 이전 URL 생성 예시
    const previousURL = `/study/${studyGroupCode}/daily/${boardIdx}`;

    console.log("Previous URL:", previousURL);

</script>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

    <h4 class="dailyWrite-title">공부인증 글쓰기</h4>

    <form action="/study/${studyGroupCode}/daily/${boardIdx}/write" method="post">
        <fieldset>
            <div class="daily_title">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text" id="addon-wrapping">제목</span>
                    <input type="text" name="postDailyTitle" placeholder="제목" class="form-control" aria-describedby="addon-wrapping" required autofocus>
                </div><!--input-group flex-nowrap-->
            </div>

            <div>
                <div class="input-group">
                    <span class="input-group-text">내용</span>
                    <textarea name="postDailyContents" cols="30" rows="10" placeholder="내용을 입력하세요" required class="form-control"></textarea>
                </div>
            </div>

            <section class="writeSection">
                <button  onclick="listFn()" class="writeList">목록</button>
                <input type="submit"  value="글쓰기" class="dailyWriteButton_write" />
            </section>

        </fieldset>
    </form>
    <div class="fileUploadForm">
        <div class="container">
            <form name="dataForm" id="dataForm" onsubmit="return registerAction()">
                <button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 선택</button>
                <input id="input_file" multiple="multiple" type="file" style="display:none;">
                <span style="font-size:10px;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
                <button type="submit" style="border: 1px solid #ddd; outline: none;">Upload</button>
                <div class="data_file_txt" id="data_file_txt" style="margin:40px;">
                    <span class="Addfile">첨부 파일</span>
                    <br />
                    <div id="articlefileChange">
                    </div>
                </div>
            </form>
        </div>
    </div><!--fileUploadForm-->
</main>



    <script>
        const listFn = () => {
            location.href = previousURL+"/list";
        }

        //     파일업로드
        $(document).ready(function()
            // input file 파일 첨부시 fileCheck 함수 실행
        {
            $("#input_file").on("change", fileCheck);
        });

        /**
         * 첨부파일로직
         */
        $(function () {
            $('#btn-upload').click(function (e) {
                e.preventDefault();
                $('#input_file').click();
            });
        });

        // 파일 현재 필드 숫자 totalCount랑 비교값
        var fileCount = 0;
        // 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
        var totalCount = 1;
        // 파일 고유넘버
        var fileNum = 0;
        // 첨부파일 배열
        var content_files = new Array();

        function fileCheck(e) {
            var files = e.target.files;

            // 파일 배열 담기
            var filesArr = Array.prototype.slice.call(files);

            // 파일 개수 확인 및 제한
            if (fileCount + filesArr.length > totalCount) {
                $.alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
                return;
            } else {
                fileCount = fileCount + filesArr.length;
            }

            // 각각의 파일 배열담기 및 기타
            filesArr.forEach(function (f) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    content_files.push(f);
                    // "/resources/images/postAssignment/minus.png" [***] 사진 잘 변경하기 (중요)
                    $('#articlefileChange').append(
                        '<div id="file' + fileNum + '" onclick="fileDelete(\'file' + fileNum + '\')">'
                        + '<font style="font-size:12px">' + f.name + '</font>'
                        + '<img src="/resources/images/thumbnail_01.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>'
                        + '<div/>'
                    );
                    fileNum ++;
                };
                reader.readAsDataURL(f);
            });
            console.log(content_files);
            //초기화 한다.
            $("#input_file").val("");
        }

        // 파일 부분 삭제 함수
        function fileDelete(fileNum){
            var no = fileNum.replace(/[^0-9]/g, "");
            content_files[no].is_delete = true;
            $('#' + fileNum).remove();
            fileCount --;
            console.log(content_files);
        }

        /*
         * 폼 submit 로직
         */
        function registerAction(){

            var form = $("form")[0];
            var formData = new FormData(form);
            for (var x = 0; x < content_files.length; x++) {
                // 삭제 안한것만 담아 준다.
                if(!content_files[x].is_delete){
                    formData.append("article_file", content_files[x]);
                }
            }
            /*
            * 파일업로드 multiple ajax처리
            */
            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: previousURL+"/file-upload",
                data : formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(JSON.parse(data)['result'] == "OK"){
                        alert("파일업로드 성공");
                    } else
                        alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
                },
                error: function (xhr, status, error) {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            });
            return false;
        }
    </script>
