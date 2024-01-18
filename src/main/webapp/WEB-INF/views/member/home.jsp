<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${sessionScope.loginEmail}의 마이페이지 입니다.</h1>
<a href="/member/update?memberIdx=${member.memberIdx}">회원 정보 수정</a><br>
<a href="/member/profile?memberIdx=${member.memberIdx}">회원 프로필 수정</a><br>
<table>
    <tr>
        <th>이메일</th>
        <td>${member.memberEmail}</td>
    </tr>
    <%--    <tr>
            <th>password</th>
            <td>${member.memberPassword}</td>
        </tr>--%>
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
    <tr>
        <th>프로필 사진</th>
        <td><img src="${pageContext.request.contextPath}/resources/upload/${member.memberProfile}"/></td>
    </tr>
</table>
<a href="/member/logout">로그아웃</a><br>
<button onclick="deleteMember('${member.memberIdx}')">회원 탈퇴</button>
<hr>
<table>
    <tr>
        <th>스터디 그룹 번호</th>
        <th>스터디 그룹명</th>
        <th>스터디 그룹 내 권한</th>
    </tr>
<c:choose>
    <%-- 글이 존재하지 않을 때에는 글 목록 출력 X --%>
    <c:when test="${empty myStudy}">
        <tr>
            <td colspan="6">등록된 글이 없습니다.</td>
        </tr>
    </c:when>
    <%-- 글이 1개 이상 존재할 때에는 글 목록 출력 O --%>
    <c:otherwise>
        <c:forEach items="${myStudy}" var="member" varStatus="status">
        <tr>
            <td><c:out value="${member.studyGroupIdx}"/></td>
            <td><a href="/study/${member.studyGroupCode}"><c:out value="${member.studyGroupName}"/></a></td>
            <td><c:out value="${member.studyMemRole}"/></td>
        </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
</table>
</body>
<script>
    const deleteMember = (memberIdx) => {

        if (confirm("정말 삭제하시겠습니까?") == true){
            console.log(memberIdx);
            location.href = "/member/delete?memberIdx="+memberIdx;
        } else {
            return;
        }

    }
</script>
</html>
