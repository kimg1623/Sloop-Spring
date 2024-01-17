<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th>스터디 그룹 번호</th>
        <th>스터디 그룹명</th>
        <th>스터디 그룹 내 권한</th>
    </tr>
    <tr>
        <td>${memberDTO.studyGroupIdx}</td>
        <td>${memberDTO.studyGroupName}</td>
        <td>${memberDTO.studyMemRole}</td>
    </tr>
</table>

</body>
</html>
