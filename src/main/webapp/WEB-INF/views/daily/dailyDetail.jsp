<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
     <title>상세정보</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<h4>공부인증 게시판(상세)</h4>
<div class="row" align="left">
    <div class="col-md-12">
        <h3>${daily.postDailyTitle}</h3>

        <p><b>닉네임</b> : ${daily.memberNickname}
        <p><b>일자</b> : ${empty daily.postDailyEditDate?daily.postDailyRegDate:daily.postDailyEditDate}</p>

        <p><b>글내용</b> : ${daily.postDailyContents}

        <p><b>조회수</b> : ${daily.postDailyHits}

            <br>
        <p>

            <!--본인일때만 수정삭제 보이게 -->
            <c:if test="${daily.memberEmail == sessionScope.loginEmail}">
            <button class="btn btn-primary" onclick="updateFn()">수정</button>
            <button class="btn btn-primary" onclick="deleteFn()">삭제</button>
            </c:if>
            <button class="btn btn-primary" onclick="listFn()">목록</button>
    </div>
</div>


</body>
<script>
    //목록
    const listFn = () => {
        location.href = "/daily";
    }
    //글수정
    const updateFn = () => {
        const postIdx = '${daily.postIdx}';
        location.href = "/daily/update?postIdx=" + postIdx;
    }
    //글삭제
    const deleteFn = () => {
        const postIdx = '${daily.postIdx}';
        location.href = "/daily/delete?postIdx=" + postIdx;
    }


</script>
</html>