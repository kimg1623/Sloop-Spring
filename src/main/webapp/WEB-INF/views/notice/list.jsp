<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<!-- 글 작성하기 버튼 -->
<div id="writeBtn" name="writeBtn">
    <input type="button" value="글쓰기" onclick="writeBtn()"/>
</div>

<table>
    <!-- 글 속성 -->
    <thead>
    <tr>
        <th>번호</th>
        <th>분류</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
    </tr>
    </thead>


    <!-- 글 본문 -->
    <tbody>
    <div id="tbody" name="tbody">
        <c:choose>
            <%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
            <c:when test="${empty noticeList}">
                <tr>
                    <td colspan="6">등록된 글이 없습니다.</td>
                </tr>
            </c:when>
            <%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
            <c:otherwise>
                <c:forEach items="${noticeList}" var="notice" varStatus="status">
                    <tr class="postforum_${notice.postIdx}">
                            <%-- <td><c:out value="${status.index + 1}"/></td> --%>
                        <td><c:out value="${notice.postIdx}"/></td>
                        <td><c:out value="${notice.categoryPostName}"/></td>
                        <td><a href="/notice/detail?postIdx=${notice.postIdx}"><c:out value="${notice.postNoticeTitle}"/></a></td>
                            <%-- <td><c:out value="${postForum.postForumTitle}"/></td> --%>
                        <td><c:out value="${notice.memberNickname}"/></td>
                        <td><c:out value="${notice.postNoticeRegDate}"/></td>
                        <td><c:out value="${notice.postNoticeHits}"/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    </tbody>

    <!-- 페이징 + 검색 -->
    <tfoot>
    <div id="tfoot" name="tfoot">
        <%-- 글이 1개 이상 존재할 때만 페이징 출력 --%>
        <c:if test="${not empty noticeList}">
            <%-- 페이징 --%>
            <tr>
                <td colspan="6">
                    <div id="paging" name="paging">
                            <%-- [이전] --%>
                        <c:choose>
                            <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
                            <c:when test="${noticeSearchDTO.page<=1}">
                                <span>[이전]</span>
                            </c:when>
                            <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
                            <c:otherwise>
                                <a href="/notice/list?page=${noticeSearchDTO.page-1}&searchType=${noticeSearchDTO.searchType}&keyword=${noticeSearchDTO.keyword}">[이전]</a>
                            </c:otherwise>
                        </c:choose>
                            <%-- 페이징 번호 --%>
                        <c:forEach begin="${noticeSearchDTO.beginningPage}" end="${noticeSearchDTO.endingPage}" var="i" step="1">
                            <c:choose>
                                <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
                                <c:when test="${i == noticeSearchDTO.page}">
                                    <span>${i}</span>
                                </c:when>

                                <c:otherwise>
                                    <a href="/notice/list?page=${i}&searchType=${noticeSearchDTO.searchType}&keyword=${noticeSearchDTO.keyword}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                            <%-- [다음] --%>
                        <c:choose>
                            <c:when test="${noticeSearchDTO.page>=noticeSearchDTO.maxPage}">
                                <span>[다음]</span>
                            </c:when>
                            <c:otherwise>
                                <a href="/notice/list?page=${noticeSearchDTO.page+1}&searchType=${noticeSearchDTO.searchType}&keyword=${noticeSearchDTO.keyword}">[다음]</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:if>

        <%-- 검색 --%>
        <tr>
            <td colspan="6">
                <%-- 검색 --%>
                <div id="search" name="search">
                    <select name="searchType">
                        <option value="1" <c:if test="${noticeSearchDTO.searchType == 0 || noticeSearchDTO.searchType == 1}">selected</c:if>>제목</option>
                        <option value="2" <c:if test="${noticeSearchDTO.searchType == 2}">selected</c:if>>내용</option>
                        <option value="3" <c:if test="${noticeSearchDTO.searchType == 3}">selected</c:if>>제목+내용</option>
                        <option value="4" <c:if test="${noticeSearchDTO.searchType == 4}">selected</c:if>>작성자</option>
                    </select>

                    <input type="text" name="keyword" value="${noticeSearchDTO.keyword}" />
                    <input type="button" onclick="searchBtn()" value="검색"/>
                </div>
            </td>
        </tr>
    </div>
    </tfoot>
</table>




<script>
    // 문서가 완전히 로드된 후에 스크립트를 실행하기 위해 jQuery의 document.ready() 함수를 사용
    $(document).ready(function() {
        $('table tbody tr').click(function() {
            const hrefValue = $(this).find('a').attr('href');
            window.location.href = hrefValue;
        });
    });

    // 글 작성하기 버튼
    writeBtn = () => {
        location.href = "/notice/write";
    };

    // 검색하기 버튼
    searchBtn = () => {
        let searchType = document.getElementsByName("searchType")[0].value;
        let keyword =  document.getElementsByName("keyword")[0].value;
        keyword = keyword.trim(); // 앞뒤 공백 제거
        /*
        console.log(searchType);
        console.log(keyword);
         */

        // 검색어가 공백인 경우 alert
        if(keyword === "" || keyword.length == 0){
            alert("검색어를 입력해 주세요.");
            return false;
        }

        location.href = '/notice/list?page=1&searchType=' + searchType + '&keyword=' + keyword;
    };
</script>
</main>