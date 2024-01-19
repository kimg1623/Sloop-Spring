<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: thegreatjy
  Date: 2024/01/09
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/css/style_post.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_reply.css" rel="stylesheet">

<script src="/resources/js/reply.js"></script>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--post button 영역 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <a href="javascript:location.href=document.referrer;" class="back_arrow">
                            <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 448 512"
                                 color="808080" cursor="pointer" height="20" width="20"
                                 xmlns="http://www.w3.org/2000/svg" style="color: rgb(128, 128, 128);">
                                <path d="M257.5 445.1l-22.2 22.2c-9.4 9.4-24.6 9.4-33.9 0L7 273c-9.4-9.4-9.4-24.6 0-33.9L201.4 44.7c9.4-9.4 24.6-9.4 33.9 0l22.2 22.2c9.5 9.5 9.3 25-.4 34.3L136.6 216H424c13.3 0 24 10.7 24 24v32c0 13.3-10.7 24-24 24H136.6l120.5 114.8c9.8 9.3 10 24.8.4 34.3z"></path>
                            </svg>
                        </a>
                    </div>
                    <div class="box-size">
                        <div id="writeBtn" name="writeBtn">
                            <button class="btn_list" onclick="listFn()">목록</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--post button 영역 끝 -->

            <!-- post 제목 영역 시작 -->
            <div class="post-top">
                <div class="post_content_title post_content_title_assign">
                    <c:out value="${postAssignmentDTO.postAssignmentTitle}"/>
                    <div class="post_assign_score">
                        만점: <c:out value="${postAssignmentDTO.assignmentScore}"/>
                    </div>
                </div>
                <div class="post_content_title_bottom">
                    <div class="post_content_userAndDate">
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                출제자
                            </div>
                        </div>
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                <c:out value="${postAssignmentDTO.memberNickname}"/>
                            </div>
                        </div>
                    </div>
                    <div class="post_content_seperator"></div>
                    <div class="post_content_userAndDate">
                        <div class="post_content_user">
                            <div class="post_content_userName post_content_endDate">
                                마감일
                            </div>
                        </div>
                        <div class="post_content_user">
                            <div class="post_content_userName post_content_endDate">
                                <fmt:formatDate value="${postAssignmentDTO.assignmentEndDate}"
                                                pattern="yyyy-MM-dd"></fmt:formatDate>
                            </div>
                        </div>
                    </div>
                    <div class="post_content_seperator"></div>
                    <div class="post_content_userAndDate">
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                등록일
                            </div>
                        </div>
                        <div class="post_content_user">
                            <div class="post_content_userName">
                                <fmt:formatDate value="${postAssignmentDTO.postAssignmentRegDate}"
                                                pattern="yyyy-MM-dd"></fmt:formatDate>
                            </div>
                            <!-- 수정일시가 존재한다면 작성일 옆에 (edited) 표시 -->
                            <c:if test="${not empty postAssignmentDTO.postAssignmentRegDate}">
                                <c:out value="(edited)"/>
                            </c:if>
                        </div>
                    </div>
                    <div class="post_content_seperator"></div>
                    <div class="post_content_view">
                        <img src="/resources/images/eye.png" alt="views" class="view_img">
                        <c:out value="${postAssignmentDTO.postAssignmentHits}"/>
                    </div>
                </div>
            </div>
            <!-- post 제목 영역 끝 -->

            <!-- post 내용 시작 -->
            <div class="post_content_contents">
                <c:out value="${postAssignmentDTO.postAssignmentContents}" escapeXml="false"/>
            </div>
            <div class="post_content_attach">
                <ul class="list-group" style="margin-bottom: 15px;">
                    <li class="list-group-item list-group-item-dark">
                        첨부파일
                    </li>
                    <c:forEach var="attachmentDTO" items="${postAssignmentDTO.attachmentDTOList}">
                        <li class="list-group-item">
                            <a href="<c:url value='/attachment/download/${attachmentDTO.attachmentName}'/>">
                                <c:out value="${attachmentDTO.attachmentOrgName}"/>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- post 내용 끝 -->
            <p hidden="true">${postAssignmentDTO.memberIdx}</p>

            <div class="box-size buttonList">
            <div class="box-size">
                <div name="writeBtn">
                    <%-- 게시물 작성자 이메일과 session에 저장된 로그인 된 이메일이 동일할 경우에만 수정, 삭제 버튼 출력 --%>
                    <c:if test="${sessionScope.loginEmail != null && postAssignmentDTO.memberEmail == sessionScope.loginEmail}">
                        <input type="button" class="btn_update"
                               onclick="updateFn('${postAssignmentDTO.postIdx}')" value="수정"/>
                        <input type="button" class="btn_delete"
                               onclick="deleteFn('${postAssignmentDTO.postIdx}')" value="삭제"/>
                    </c:if>
                </div>
            </div>
            </div>



            <!-- 댓글 입력 폼 -->
            <div class="replyInput_replytitle">
                댓글
            </div>
            <div class="replyInput_content_box">
                <input class="replyInput_content" type="text" id="replyContents"
                       placeholder="댓글을 입력해주세요.">
            </div>
            <div class="replyButton_wrap">
                <button class="replyButton" id="reply-write-btn"
                        onclick="replyWrite('${postAssignmentDTO.postIdx}', '${sessionScope.loginMemberIdx}')">댓글 등록
                </button>
            </div>
            <br>

            <!-- 댓글 목록 -->
            <div id="reply-list">
            </div>


            <script>
                /* 게시글 */
                // 목록으로 돌아가기
                const listFn = () => {
                    location.href = "./list";
                };

                // 글 수정하기
                const updateFn = (postIdx) => {
                    location.href = "./update?postIdx=" + postIdx;
                };

                // 글 삭제하기
                const deleteFn = (postIdx) => {
                    // location.href = "/postassignment/delete?postIdx=" + postIdx;

                    if (confirm("삭제하시겠습니까?") == true) {
                        // true는 확인버튼을 눌렀을 때 코드 작성
                        location.href = "./delete?postIdx=" + postIdx;
                    } else {
                        // false는 취소버튼을 눌렀을 때, 취소됨
                        console.log("취소되었습니다");
                    }
                };

                /* 댓글 */
                // 자동 실행 함수
                $(document).ready(function () {
                    let postIdx = '${postAssignmentDTO.postIdx}';
                    let loginMemberIdx = '${sessionScope.loginMemberIdx}';
                    // 댓글 목록 출력
                    loadReplyList(postIdx, loginMemberIdx);
                });

            </script>
        </div>
    </div>
</main>
