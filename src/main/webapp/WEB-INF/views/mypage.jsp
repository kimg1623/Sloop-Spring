<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<h1>${sessionScope.loginEmail}의 마이페이지 입니다.</h1>
<a href="/member/update">회원 정보 수정</a><br>
<table>
    <tr>
        <th>정렬번호</th>
        <td>${member.memberIdx}</td>
    </tr>
    <tr>
        <th>이메일</th>
        <td>${member.memberEmail}</td>
    </tr>
    <tr>
        <th>password</th>
        <td>${member.memberPassword}</td>
    </tr>
    <tr>
        <th>닉네임</th>
        <td>${member.memberNickname}</td>
    </tr>
    <tr>
        <th>성별</th>
        <td>${member.memberGender}</td>
    </tr>
    <tr>
        <th>핸드폰번호</th>
        <td>${member.memberPhonenumber}</td>
    </tr>
    <tr>
        <th>회원 분류</th>
        <td>${member.memberGradeCode}</td>
    </tr>
    <tr>
        <th>학교명</th>
        <td>${member.memberSchool}</td>
    </tr>
    <tr>
        <th>관심 과목</th>
        <td>${member.memberSubjectCode}</td>
    </tr>
    <tr>
        <th>지역</th>
        <td>${member.memberRegionCode}</td>
    </tr>
</table>
</body>
</html>
