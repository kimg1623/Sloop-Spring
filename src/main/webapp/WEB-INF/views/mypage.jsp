<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<h1>${sessionScope.loginEmail}의 마이페이지 입니다.</h1>
<a href="/member/update">회원 정보 수정</a>
</body>
</html>
