<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/style_main.css" rel="stylesheet">
</head>
<body>
    <div class="container-main">
        <div class="row">
            <c:forEach items="${studyGroupList}" var="studyGroup">
                <div class="col-sm-3">
                    <a href="/study/${studyGroup.studyGroupCode}">
                        <div class="card">
                            <img src="${contextPath}/resources/images/thumbnail_01.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${studyGroup.studyGroupName}</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <table border="1px solid">
        <tr>
            <th>정렬번호</th>
            <th>스터디그룹 이름</th>
            <th>스터디그룹 방식</th>
            <th>학년</th>
            <th>과목</th>
            <th>지역</th>
            <th>모집인원</th>
            <th>모집 마감일</th>
            <th>스터디 시작일</th>
            <th>조회수</th>
        </tr>
        <c:forEach items="${studyGroupList}" var="studyGroup">
            <tr>
                <td>${studyGroup.studyGroupIdx}</td>
                <td>
                    <a href="/study/${studyGroup.studyGroupCode}">${studyGroup.studyGroupName}</a>
                </td>
                <td>${studyGroup.studyGroupMethod}</td>
                <td>${studyGroup.studyGroupGradeCode}${studyGroup.studyGroupGrade}</td>
                <td>${studyGroup.studyGroupSubjectCode}${studyGroup.studyGroupSubject}</td>
                <td>${studyGroup.studyGroupRegionCode}${studyGroup.studyGroupRegion}</td>
                <td>${studyGroup.studyGroupNum}</td>
                <td>${studyGroup.studyGroupDuedate}</td>
                <td>${studyGroup.studyGroupStartdate}</td>
                <td>${studyGroup.studyGroupHits}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
