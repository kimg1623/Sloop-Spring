<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보수정</title>
</head>
<body>
<form action="/member/update" method="post" name="updateForm">
    정렬번호 : <input type="text" name="memberIdx" value="${member.memberIdx}" readonly><br>
    이메일 : <input type="text" name="memberEmail" value="${member.memberEmail}" readonly><br>
    비밀번호 : <input type="text" name="memberPassword" id="memberPassword" autofocus><br>
    닉네임 : <input type="text" name="memberNickname" value="${member.memberNickname}" readonly><br>
    성별 : <input type="radio" name="memberGender" value="${member.memberGender}" readonly><br>
    핸드폰 번호: <input type="text" name="memberPhonenumber" value="${member.memberPhonenumber}"><br>
    회원 분류 : <input type="text" name="memberGradeCode" value="${member.memberGradeCode}"><br>
    학교명 : <input type="text" name="memberSchool" value="${member.memberSchool}"><br>
    관심 과목 : <input type="checkbox" name="memberGradeCode" value="${member.memberSubjectCode}"><br>
    지역 : <input type="text" name="memberRegionCode" value="${member.memberRegionCode}"><br>
    <input type="button" value="수정" onclick="update()">

</form>
</div>
</body>
<script>
    const update = () => {
        const passwordDB = '${member.memberPassword}';
        const password = document.getElementById("memberPassword").value;
        if (passwordDB == password) {
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!");
        }
    }
</script>
</html>