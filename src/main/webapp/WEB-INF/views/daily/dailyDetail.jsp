<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/src/main/webapp/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <title>상세정보</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<h4>공부인증 게시판(상세)</h4>
<div class="row" align="left">
    <div class="col-md-12">
        <h3>${daily.postDailyTitle}</h3>

        <p><b>memberIdx</b> : ${daily.memberIdx}

        <p><b>작성일자</b> : ${daily.postDailyRegDate}
        <p><b>닉네임</b> : ${daily.memberNickname}
        <p><b>글내용</b> : ${daily.postDailyContents}

        <p><b>조회수</b> : ${daily.postDailyHits}

        <br>
        <p>

<%--        <a href="/daily/update?postIdx=" + postIdx; class="btn btn-primary">수정</a>--%>
        <button class="btn btn-primary" onclick="updateFn()">글수정</button>
        <button class="btn btn-primary" onclick="deleteFn()">글삭제</button>
        <button class="btn btn-primary" onclick="listFn()">목록</button>
    </div>
</div>


</body>
<script>
    const listFn = () => {
        const page = '${page}';

        location.href = "/daily";
    }
    const updateFn = () => {
        const postIdx = '${daily.postIdx}';
        location.href = "/daily/update?postIdx=" + postIdx;
    }
    const deleteFn = () => {
        const postIdx = '${daily.postIdx}';
        location.href = "/daily/delete?postIdx=" + postIdx;
    }


</script>
</html>