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
 <style>
   .no-underline{
      text-decoration:none;
   }
 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
<h2>스터디 그룹명: ${studyGroup.studyGroupName}</h2>
-----------------------------------
<br>
<div>
    <h2>메뉴</h2>
    <a href="/study/${studyGroup.studyGroupCode}/notice/${groupBoardIdxs[0].boardIdx}">${groupBoardIdxs[0].categoryName}</a><br>
    <a href="/study/${studyGroup.studyGroupCode}/assign/${groupBoardIdxs[1].boardIdx}">${groupBoardIdxs[1].categoryName}</a><br>
    <a href="/study/${studyGroup.studyGroupCode}/postforum/${groupBoardIdxs[2].boardIdx}">${groupBoardIdxs[2].categoryName}</a><br>
    <a href="/study/${studyGroup.studyGroupCode}/daily/${groupBoardIdxs[3].boardIdx}">${groupBoardIdxs[3].categoryName}</a><br>
    <a href="/study/${studyGroup.studyGroupCode}/manage/info">스터디 관리</a><br>
</div>
</body>
</html>