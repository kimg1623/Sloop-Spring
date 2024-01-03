<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="<c:url value="/src/main/webapp/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <title>공부인증 게시글 작성</title>
</head>
<body>
    <h4>공부인증 글쓰기</h4>

    <form action="/daily/write" method="post">
        <fieldset>
            <div class="form-group row">
                <label class="col-sm-2 control-label">제목</label>
                <div class="col-sm-3">
                    <input type="text" name="postDailyTitle" placeholder="제목" class="form-control" autofocus>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 control-label">내용</label>
                <div class="col-sm-5">
                    <textarea name="postDailyContents" cols="30" rows="10" placeholder="내용을 입력하세요" class="form-control"></textarea>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="글쓰기"/>
                </div>
            </div>
        </fieldset>
    </form>

    <button class="btn btn-primary" onclick="listFn()">목록</button>


</body>

<script>
    const listFn = () => {
        location.href = "/daily";
    }
</script>
</html>
