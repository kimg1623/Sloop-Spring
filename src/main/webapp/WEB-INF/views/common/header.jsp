<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
<table border=0  width="100%">
  <tr>
     <td width="15%">
		<a href="/">
			<img src="${contextPath}/resources/images/logo2.png"  />
		</a>
     </td>

      <td width="70%">
      </td>

     
     <td>
       <!-- <a href="#"><h3>로그인</h3></a> -->
       <c:choose>
          <c:when test="${sessionScope.loginMemberNickname != null}">
            <h3>환영합니다. ${sessionScope.loginMemberNickname}님!</h3>
            <a href="/member/logout"><h3>로그아웃</h3></a>
          </c:when>
          <c:otherwise>
	        <a href="/member/login"><h3>로그인</h3></a>
	      </c:otherwise>
	   </c:choose>     
     </td>
  </tr>
</table>


</body>
</html>