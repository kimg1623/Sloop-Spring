<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<h1>가입 회원 목록 전체 보기</h1>
<table>
    <tr>
        <th>정렬번호</th>
        <th>이메일</th>
        <th>비밀번호</th>
        <th>닉네임</th>
        <th>가입 날짜</th>
        <th>핸드폰번호</th>
        <th>조회</th>
        <th>삭제</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.memberIdx}</td>
            <td>
                <a href="/member?memberIdx=${member.memberIdx}">${member.memberEmail}</a>
            </td>
            <td>${member.memberPassword}</td>
            <td>${member.memberNickname}</td>
            <td>${member.memberRegdate}</td>
            <td>${member.memberPhonenumber}</td>
            <td>
                <a href="/member?memberIdx=${member.memberIdx}">조회</a>
            </td>
            <td>
                <button onclick="deleteMember('${member.memberIdx}')">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<script>
    const deleteMember = (memberIdx) => {
        console.log(memberIdx);
        location.href = "/member/delete?memberIdx="+memberIdx;
    }
</script>
</html>
