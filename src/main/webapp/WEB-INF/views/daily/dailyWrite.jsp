<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>공부인증 게시글 작성</title>
</head>
<body>
    <h4>공부인증 글쓰기</h4>

    <form action="/daily/write" method="post">
        <fieldset>
            <div>
                <label class="col-sm-2 control-label">제목</label>
                <div>
                    <input type="text" name="postDailyTitle" placeholder="제목" class="form-control" autofocus>
                </div>
            </div>

            <div>
                <label class="col-sm-2 control-label">내용</label>
                <div>
                    <textarea name="postDailyContents" cols="30" rows="10" placeholder="내용을 입력하세요" class="form-control"></textarea>
                </div>
            </div>

            <form name="fileForm" action="/daily/write/file" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="text" name="src" />
                <input type="submit" value="파일저장" />
            </form>
            <!--다중일때 multiple="multiple"-->

            <div>
                <div>
                    <input type="submit"  value="글쓰기"/>
                </div>
            </div>
        </fieldset>
    </form>

    <button  onclick="listFn()">목록</button>


</body>

<script>
    const listFn = () => {
        location.href = "/daily";
    }
</script>
</html>
