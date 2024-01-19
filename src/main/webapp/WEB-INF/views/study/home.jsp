<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <div class="box-size title_div_text">
                            <img class="study_group_home_img" src="/resources/images/study_home.png">
                        </div>
                    </div>
                    <div class="box-size">
                        <div class="card study_group_home_card">
                            <h3>
                                <c:out value="${studyGroup.studyGroupName}"/>
                            </h3>
                            그룹 멤버들의 스터디를 응원합니다!

                        </div>
                    </div>
                </div>
            </div>
            <!--board_title 끝 -->
        </div>
    </div>
</main>
