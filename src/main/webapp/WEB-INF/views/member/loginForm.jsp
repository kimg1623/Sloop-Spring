<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<form action="/member/login" method="post">
    <input type="text" name="memberEmail" placeholder="이메일"><br>
    <input type="password" name="memberPassword" placeholder="비밀번호"><br>
    <input type="submit" value="로그인">
</form>
<a href="/member/signup">회원가입하러가기</a>
</body>
</html>
