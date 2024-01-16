<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/css/style_login.css" rel="stylesheet">
    <title>로그인</title>
</head>
<body>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active"> Sign In </h2>

        <!-- Login Form -->
        <form action="/member/login" method="post">
            <input type="text" id="login" class="fadeIn second" name="memberEmail" placeholder="Email">
            <input type="text" id="password" class="fadeIn third" name="memberPassword" placeholder="password">
            <br><br>
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Password -->
        <div id="formFooter">
            <a class="underlineHover" href="/member/signup">Sign Up</a>
        </div>

    </div>
</div>

</body>
</html>





<%--<form action="/member/login" method="post">--%>
<%--    <input type="text" name="memberEmail" placeholder="이메일"><br>--%>
<%--    <input type="password" name="memberPassword" placeholder="비밀번호"><br>--%>
<%--    <input type="submit" value="로그인">--%>
<%--</form>--%>
<%--<a href="/member/signup">회원가입하러가기</a>--%>