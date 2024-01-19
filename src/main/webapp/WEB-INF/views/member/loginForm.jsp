<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link href="/resources/css/style_login.css" rel="stylesheet">
</head>
<body>

<div class="wrapper fadeInDown">
    <div class="form-wrapper">
        <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active"> LOG IN </h2>

        <%--@elvariable id="memberDTO" type="kr.co.sloop.member.domain.MemberDTO"--%>
        <form:form action="/member/login" method="post" modelAttribute="memberDTO" >
            <p><form:input path="memberEmail" type="email" placeholder="이메일" class="fadeIn second" required="true" autofocus="autofocus"/></p>
            <form:errors path="memberEmail" cssStyle="color: red"/>

            <p><form:input path="memberPassword" type="password" placeholder="비밀번호" class="fadeIn third" required="true" /></p>
            <form:errors path="memberPassword" cssStyle="color: red"/>

            <input type="submit" class="fadeIn fourth" value="로그인"/>
        </form:form>
        <div id="formFooter">
            <a class="underlineHover" href="/member/signup">회원가입</a>
        </div>

    </div>
</div>
</div>

</body>
</html>


