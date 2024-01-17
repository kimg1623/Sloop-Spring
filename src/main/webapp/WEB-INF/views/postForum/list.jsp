<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_post.css" rel="stylesheet">
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>


<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <div class="box-size title_div_text">
                            <h3 class="title_text">
                                자유게시판
                            </h3>
                        </div>
                    </div>
                    <div class="box-size">
                        <div id="writeBtn" name="writeBtn">
                            <input type="button" class="btn_write" value="글쓰기" onclick="writeBtn()"/>
                        </div>
                    </div>
                </div>
            </div>
            <!--board_title 끝 -->

            <c:choose>
                <%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
                <c:when test="${empty postForumDTOList}">
                    <tr>
                        <td colspan="6">등록된 글이 없습니다.</td>
                    </tr>
                </c:when>
                <%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
                <c:otherwise>
                    <c:forEach items="${postForumDTOList}" var="postForum" varStatus="status">
                        <a href="./detail?postIdx=${postForum.postIdx}">
                            <div class="card">
                                <div class="card-body post-card">
                                    <div class="card-category">
                                        <c:out value="${postForum.categoryPostName}"/>
                                    </div>
                                    <h5 class="post-card-title">
                                        <c:out value="${postForum.postForumTitle}"/>
                                    </h5>
                                    <div class="card-bottom">
                                        <div class="bottom_userAndDate">
                                            <div class="bottom_user">
                                                <div class="bottom_userName">
                                                    <c:out value="${postForum.memberNickname}"/>
                                                </div>
                                            </div>
                                            <div class="bottom_seperator"></div>
                                            <div class="bottom_registeredDate">
                                                <fmt:formatDate value="${postForum.postForumRegDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </div>
                                        </div>
                                        <div class="bottom_view">
                                            <img src="/resources/images/eye.png" alt="views" class="view_img">
                                            <c:out value="${postForum.postForumHits}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


    <table>
        <!-- 페이징 + 검색 -->
        <tfoot>
        <div id="tfoot" name="tfoot">
            <%-- 글이 1개 이상 존재할 때만 페이징 출력 --%>
            <c:if test="${not empty postForumDTOList}">
            <%-- 페이징 --%>
            <tr>
                <td colspan="6">
                    <div id="paging" name="paging">
                            <%-- [이전] --%>
                        <c:choose>
                            <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
                            <c:when test="${searchDTO.page<=1}">
                                <span>[이전]</span>
                            </c:when>
                            <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
                            <c:otherwise>
                                <a href="./list?page=${searchDTO.page-1}&searchType=${searchDTO.searchType}&keyword=${searchDTO.keyword}">[이전]</a>
                            </c:otherwise>
                        </c:choose>
                            <%-- 페이징 번호 --%>
                        <c:forEach begin="${searchDTO.beginningPage}" end="${searchDTO.endingPage}" var="i" step="1">
                            <c:choose>
                                <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
                                <c:when test="${i == searchDTO.page}">
                                    <span>${i}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="./list?page=${i}&searchType=${searchDTO.searchType}&keyword=${searchDTO.keyword}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                            <%-- [다음] --%>
                        <c:choose>
                            <c:when test="${searchDTO.page>=searchDTO.maxPage}">
                                <span>[다음]</span>
                            </c:when>
                            <c:otherwise>
                                <a href="./list?page=${searchDTO.page+1}&searchType=${searchDTO.searchType}&keyword=${searchDTO.keyword}">[다음]</a>
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
                                        <option value="1"
                                                <c:if test="${searchDTO.searchType == 0 || searchDTO.searchType == 1}">selected</c:if>>
                                            제목
                                        </option>
                                        <option value="2" <c:if test="${searchDTO.searchType == 2}">selected</c:if>>내용</option>
                                        <option value="3" <c:if test="${searchDTO.searchType == 3}">selected</c:if>>제목+내용
                                        </option>
                                        <option value="4" <c:if test="${searchDTO.searchType == 4}">selected</c:if>>작성자</option>
                                    </select>

                                    <input type="text" name="keyword" value="${searchDTO.keyword}"/>
                                    <input type="button" onclick="searchBtn()" value="검색"/>
                                </div>
                            </td>
                        </tr>
                    </div>
                    </tfoot>
                </table>
        </div>
    </div>
</main>


<script>
    // 문서가 완전히 로드된 후에 스크립트를 실행하기 위해 jQuery의 document.ready() 함수를 사용
    $(document).ready(function() {

    });

    // 글 작성하기 버튼
    writeBtn = () => {
        location.href = "./write";
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

        location.href = './list?page=1&searchType=' + searchType + '&keyword=' + keyword;
    };
</script>