<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>스터디 그룹 전체 리스트 보기</h1>
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
