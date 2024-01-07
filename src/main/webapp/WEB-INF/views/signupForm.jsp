<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <title>S-loop 회원가입</title>
</head>
<body>
<form action="/member/signup" method="post">
    <p>이 메 일 : <input type="email" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()">
                    <button type="button" name="check-Email" value="중복확인" onclick="emailCheck()">중복확인</button></p>
    <p id="check-result"></p>
    <p>비밀번호 : <input type="password" name="memberPassword" placeholder="비밀번호" required></p>
    <p>닉 네 임 : <input type="text" name="memberNickname" placeholder="닉네임" required></p>
    <p>성   별 :
        <input type="radio" name="memberGender" id="male" value="남자">
        <label for="male">남자</label>
        <input type="radio" name="memberGender" id="female" value="여자">
        <label for="female">여자</label>
    </p>
    <p>전화번호 : <input type="text" name="memberPhonenumber" placeholder="핸드폰번호" required></p>
    <p>회원대분류 :
        <select name="memberGradeCode" id="memberGradeCode" onchange="memberDivisionChange(this)" required>
            <option>선택하세요.</option>
            <option value="초등학생">초등학생</option>
            <option value="중학생">중학생</option>
            <option value="고등학생">고등학생</option>
            <%--<option value="univ">대학생</option>--%>
            <%--<option value="normal">일반인</option>--%>
        </select>
    </p>
    <p>회원소분류 :
        <select name="memberGradeCode" id="memberGradeCode_sub">
            <option value="choose">선택하세요.</option>
        </select></p>
    <p>학 교 명 : <input type="text" name="memberSchool" required></p>
    <p>관심 과목 :
        <input type="checkbox" name="memberSubjectCode" value="국어">국어
        <input type="checkbox" name="memberSubjectCode" value="영어">영어
        <input type="checkbox" name="memberSubjectCode" value="수학">수학
        <input type="checkbox" name="memberSubjectCode" value="사회">사회
        <input type="checkbox" name="memberSubjectCode" value="과학">과학
        <input type="checkbox" name="memberSubjectCode" value="기타">기타
    </p>
    <p>지역대분류 :
        <select name="memberRegionCode" id="memberRegionCode" onchange="memberSigugunChange(this)" required>
            <option>선택하세요.</option>
            <option value="900">서울특별시</option>
            <option value="200">경기도</option>
            <option value="1200">인천광역시</option>
            <option value="100">강원특별자치도</option>
            <option value="1700">충청북도</option>
            <option value="1600">충청남도</option>
            <option value="700">대전광역시</option>
            <option value="1000">세종특별자치시</option>
            <option value="1400">전라북도</option>
            <option value="1300">전라남도</option>
            <option value="500">광주광역시</option>
            <option value="400">경상북도</option>
            <option value="300">경상남도</option>
            <option value="800">부산광역시</option>
            <option value="600">대구광역시</option>
            <option value="1100">울산광역시</option>
            <option value="1500">제주특별자치도</option>
        </select></p>
    <p>지역소분류 :
        <select name="memberRegionCode" id="memberRegionCode_sub" required>
            <option value="choose">선택하세요.</option>
        </select></p>
    <input type="submit" value="회원가입">
</form>
</body>
<script type="text/javascript">
    // 이메일 입력값을 가져오고,
    // 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
    // 사용 가능 여부를 이메일 입력창 아래에 표시
    // document 관련된건 DOM 명령
    const emailCheck = () => {
        const email = document.getElementById("memberEmail").value;
        const checkResult = document.getElementById("check-result");
        console.log("입력한 이메일", email);
        $.ajax({
            // 요청방식: post, url: "email-check", 데이터: 이메일
            type: "post",
            url: "/member/email-check",
            data: {
                "memberEmail": email
            },
            success: function(res) {
                console.log("요청성공", res);
                if (res == "ok") {
                    console.log("사용가능한 이메일");
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용가능한 이메일";
                } else {
                    console.log("이미 사용중인 이메일");
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 사용중인 이메일";
                }
            },
            error: function(err) {
                console.log("에러발생", err);
            }
        });
    }
    /*대분류 선택시 소분류 다르게 표시하기*/

    function memberDivisionChange(e){

        var element = ["1학년","2학년","3학년","4학년","5학년","6학년"];
        var middle = ["1학년","2학년","3학년"];
        var high = ["1학년","2학년","3학년"];
        /*var univ = ["1학년","2학년","3학년","4학년"];*/
        /*var normal = ["직장인","취준생"];*/
        var target = document.getElementById("memberGradeCode_sub");

        if (e.value == "초등학생") var d = element;
        else if (e.value == "중학생") var d = middle;
        else if (e.value == "고등학생") var d = high;
        /*else if (e.value == "univ") var d = univ;*/
        /*else if (e.value == "normal") var d = normal;*/
        target.options.length = 0;

        for (x in d) {
            var opt = document.createElement("option");
            opt.value = d[x];
            opt.innerHTML = d[x];
            target.appendChild(opt);
        }
    }
    /*회원 지역 구분*/

    function memberSigugunChange(a){
        var seoul = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구",
            "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구",
            "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
        var gyeonggi = [ "수원시 장안구", "수원시 권선구", "수원시 팔달구", "수원시 영통구", "성남시 수정구", "성남시 중원구",
            "성남시 분당구", "의정부시", "안양시 만안구", "안양시 동안구", "부천시", "광명시", "평택시", "동두천시", "안산시 상록구",
            "안산시 단원구", "고양시 덕양구", "고양시 일산동구","고양시 일산서구", "과천시", "구리시", "남양주시", "오산시",
            "시흥시", "군포시", "의왕시", "하남시", "용인시 처인구", "용인시 기흥구", "용인시 수지구", "파주시", "이천시",
            "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군", "양평군" ];
        var incheon = ["계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];
        var gangwon = ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군",
            "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군" ];
        var chungcheongbuk = ["청주시 상당구", "청주시 서원구", "청주시 흥덕구", "청주시 청원구", "충주시", "제천시", "보은군",
            "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군" ];
        var chungcheongnam = ["천안시 동남구", "천안시 서북구", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시",
            "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"];
        var daejeon = ["대덕구", "동구", "서구", "유성구", "중구"];
        var sejong = ["세종특별자치시"];
        var jeollabuk = ["전주시 완산구", "전주시 덕진구", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군",
            "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"];
        var jeollanam = [ "목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군",
            "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군" ];
        var gwangju = ["광산구", "남구", "동구", "북구", "서구"];
        var gyeongsangbuk = ["포항시 남구", "포항시 북구", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시",
            "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군",
            "봉화군", "울진군", "울릉군"];
        var gyeongsangnam = ["창원시 의창구", "창원시 성산구", "창원시 마산합포구", "창원시 마산회원구", "창원시 진해구",
            "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군",
            "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"];
        var busan = ["강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구",
            "연제구", "영도구", "중구", "해운대구", "기장군" ];
        var daegu = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
        var ulsan = [ "남구", "동구", "북구", "중구", "울주군"];
        var jeju = ["서귀포시", "제주시"];

        var target = document.getElementById("memberRegionCode_sub");

        if (a.value == "900") var b = seoul;
        else if (a.value == "200") var b = gyeonggi;
        else if (a.value == "1200") var b = incheon;
        else if (a.value == "100") var b = gangwon;
        else if (a.value == "1700") var b = chungcheongbuk;
        else if (a.value == "1600") var b = chungcheongnam;
        else if (a.value == "700") var b = daejeon;
        else if (a.value == "1000") var b = sejong;
        else if (a.value == "1400") var b = jeollabuk;
        else if (a.value == "1300") var b = jeollanam;
        else if (a.value == "500") var b = gwangju;
        else if (a.value == "400") var b = gyeongsangbuk;
        else if (a.value == "300") var b = gyeongsangnam;
        else if (a.value == "800") var b = busan;
        else if (a.value == "600") var b = daegu;
        else if (a.value == "1100") var b = ulsan;
        else if (a.value == "1500") var b = jeju;
        target.options.length = 0;

        for (x in b) {
            var opt = document.createElement("option");
            opt.value = b[x];
            opt.innerHTML = b[x];
            target.appendChild(opt);

        }
    }
</script>
</html>