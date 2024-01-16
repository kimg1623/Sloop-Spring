<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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


    <script>
        //목록
        const listFn = () => {
            location.href = previousURL+"/list";
        }
        //글수정
        const updateFn = () => {
            const postIdx = '${daily.postIdx}';
            location.href = previousURL+"/update?postIdx=" + postIdx;
        }
        //글삭제
        const deleteFn = () => {
            const postIdx = '${daily.postIdx}';
            location.href = previousURL+"/delete?postIdx=" + postIdx;
        }


    </script>

</main>