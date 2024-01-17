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

<%--    <h4 class="dailyWrite-title">공부인증 게시판(상세)</h4>--%>
    <div class="detailBox" align="center">

            <h1 class="detailTitle">${daily.postDailyTitle}</h1>
            <div class="detailInfo">
                <div class="detailNick">
                    <span class="detailNickInfo">${daily.memberNickname}</span>
                </div><!--detailNick-->
                <div class="detailCal">
                    ${empty daily.postDailyEditDate?daily.postDailyRegDate:daily.postDailyEditDate}
                </div><!--detailCal-->
                <div class="detailCal">
                    <p><b>조회수</b> : ${daily.postDailyHits}
                </div><!--detailHit-->
            </div><!--detailInfo 닉네임, 일자, 조회수-->

            <div class="detail-border"></div>

            <div class="detailContentWrap">
            <p>${daily.postDailyContents}
            </div><!--detailContentWrap-->

            <br>

    </div><!--detailBox-->

        <button class="writeList" onclick="listFn()">목록</button>
        <!--본인일때만 수정삭제 보이게 -->
        <c:if test="${daily.memberEmail == sessionScope.loginEmail}">
        <button class="writeUpdate" onclick="updateFn()">수정</button>
        <button class="writeDelete" onclick="deleteFn()">삭제</button>
        </c:if>







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