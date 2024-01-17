<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<%--@elvariable id="memberDTO" type="kr.co.sloop.member.domain.MemberDTO"--%>
<form:form action="/member/login" method="post" modelAttribute="memberDTO" >
    <p>아이디 : <form:input path="memberEmail" type="email" placeholder="이메일" required="true" /></p>
    <form:errors path="memberEmail" cssStyle="color: red"/>

    <p>비밀번호 : <form:input path="memberPassword" type="password" placeholder="비밀번호" required="true" /></p>
    <form:errors path="memberPassword" cssStyle="color: red"/>

    <input type="submit" value="로그인"/>
</form:form>
<a href="/member/signup">회원가입하러가기</a>
</body>
</html>


