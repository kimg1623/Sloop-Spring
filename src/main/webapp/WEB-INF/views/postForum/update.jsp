<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 1/1/24
  Time: 8:08PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<link href="/resources/css/style_post.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <script src="/resources/ckeditor/ckeditor.js"></script>


<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
        <!--post button 영역 시작 -->
        <div class="box-size board_title">
            <div class="box-size title_contents">
                <div class="box-size">
                    <a href="javascript:location.href=document.referrer;" class="back_arrow">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 448 512" color="808080" cursor="pointer" height="20" width="20" xmlns="http://www.w3.org/2000/svg" style="color: rgb(128, 128, 128);">
                            <path d="M257.5 445.1l-22.2 22.2c-9.4 9.4-24.6 9.4-33.9 0L7 273c-9.4-9.4-9.4-24.6 0-33.9L201.4 44.7c9.4-9.4 24.6-9.4 33.9 0l22.2 22.2c9.5 9.5 9.3 25-.4 34.3L136.6 216H424c13.3 0 24 10.7 24 24v32c0 13.3-10.7 24-24 24H136.6l120.5 114.8c9.8 9.3 10 24.8.4 34.3z"></path>
                        </svg>
                    </a>
                </div>
            </div>
        </div>
        <!--post button 영역 끝 -->
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="post_content_title_bottom">
                    <div class="box-size title_contents">
                        <div class="box-size">
                            <div class="box-size title_div_text">
                                <h3 class="title_text">
                                    자유게시판 수정
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--board_title 끝 -->

<!-- 글 작성 화면 -->
    <%--@elvariable id="postForumDTO" type="kr.co.sloop.postForum.domain.PostForumDTO"--%>
    <form:form modelAttribute="postForumDTO" method="post" action="./update">
    <div class="CategoryList">
        <!-- 카테고리 -->
        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostDaily" name="categoryPostIdx" value="1" />
        <label for="categoryPostDaily">일상</label>

        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostHobby" name="categoryPostIdx" value="2" />
        <label for="categoryPostDaily">취미</label>

        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostWorry" name="categoryPostIdx" value="3" />
        <label for="categoryPostDaily">고민</label>

        <form:radiobutton class="form-check-input" path="categoryPostIdx" id="categoryPostCareer" name="categoryPostIdx" value="4"/>
        <label for="categoryPostDaily">진로</label>

        <!-- hidden -->
        <!-- 작성자 idx -->
        <p><form:hidden path="memberIdx"/></p>
        <!-- 작성자 email -->
        <p><form:hidden path="memberEmail"/></p>
        <!-- 작성자 닉네임 -->
        <p><form:hidden path="memberNickname"/></p>
        <!-- postIdx -->
        <p><form:hidden path="postIdx"/></p>

        <!-- 글 제목 -->
        <div class="daily_title">
        <%-- <p><form:input path="postForumTitle" onfocus="this" value="<c:out  value='${postForumDTO.postForumTitle}'/>" /></p> --%>
        <p><form:input class="form-control" required="true" path="postForumTitle" autofocus="true" /></p>
        <p><form:errors path="postForumTitle"/> </p>
        </div>

        <!-- 글 내용 (에디터) -->
        <p><form:textarea required="true" path="postForumContents" class="form-control" id="postForumContents" name="postForumContents" rows="3"></form:textarea></p>
        <p><form:errors path="postForumContents"/> </p>
        <script>
            var ckeditor_config = {
                width: "100%",
                height:"400px",
                image_previewText: '미리보기',
                resize_enabled : false,
                enterMode : CKEDITOR.ENTER_BR,
                shiftEnterMode : CKEDITOR.ENTER_P,
                filebrowserUploadUrl : "./upload-image"
            };

            CKEDITOR.replace("postForumContents", ckeditor_config);

            //이미지 업로드가 끝나고 실행하는 함수
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

        <!-- 작성하기 버튼 -->
        <section class="writeSection">
        <input class="btn_update" type="submit" value="수정"/>
        </section>
    </form:form>
</div>
        </div>
    </div>
</main>
