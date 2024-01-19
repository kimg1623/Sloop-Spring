<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>

<link href="/resources/css/style_main.css" rel="stylesheet">
<link href="/resources/css/style_post.css" rel="stylesheet">
<link href="/resources/css/style_studygroup_daily.css" rel="stylesheet">


<!-- main 페이지의 컨텐츠 부분 시작 -->
<div class="container-main">
    <div class="container-studyGroup">
        <div class="box-size contents_wrapper">
            <!--board_title 시작 -->
            <div class="box-size board_title">
                <div class="box-size title_contents">
                    <div class="box-size">
                        <div class="box-size title_div_text">
                            <h3 class="title_text">
                                회원정보수정
                            </h3>
                        </div>

                    </div>
                </div>
            </div>


            <%--@elvariable id="memberDTO" type="kr.co.sloop.member.domain.MemberDTO"--%>
            <form:form action="/member/update" method="post" modelAttribute="memberDTO">
                <!-- hidden -->
                <!-- 작성자 idx -->
                <form:hidden path="memberIdx"/>

                <div class="row mb-3 signup_row">
                    <label for="memberEmail" class="col-sm-2 col-form-label">이메일</label>
                    <div class="col-sm-8">
                        <form:input path="memberEmail" class="form-control" readonly="true"/>
                    </div>
                </div>

                <div class="row mb-3 signup_row">
                    <label for="memberNickname" class="col-sm-2 col-form-label">닉네임</label>
                    <div class="col-sm-8">
                        <form:input path="memberNickname" class="form-control" readonly="true"/>
                    </div>
                </div>
                <%--
                <p>비밀번호 : <form:input path="memberPassword" type="password" placeholder="비밀번호" required="true" /></p>
                --%>


                <fieldset class="row mb-3">
                    <label class="col-form-label col-sm-2">성별</label>
                    <div class="col-sm-8 option_flex">
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="memberGender" id="male" value="남자" />
                            <label class="form-check-label" for="male">
                                남자
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="memberGender" id="female" value="여자" />
                            <label class="form-check-label" for="female">
                                여자
                            </label>
                        </div>
                    </div>
                </fieldset>

                <%--전화번호--%>
                <div class="row mb-3">
                    <label for="memberPhonenumber" class="col-sm-2 col-form-label">전화번호</label>
                    <div class="col-sm-8">
                        <form:input path="memberPhonenumber" class="form-control" type="text" placeholder="핸드폰번호" required="true"/>
                    </div>
                    <form:errors path="memberPhonenumber" cssStyle="color: red"/>
                </div>

                <p id="check-result3"></p>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">회원대분류</label>
                    <div class="col-sm-8">
                        <form:select path="memberGradeCode" class="form-select">
                            <form:option value="">선택하세요.</form:option>
                            <form:option value="초등학생">초등학생</form:option>
                            <form:option value="중학생">중학생</form:option>
                            <form:option value="고등학생">고등학생</form:option>
                        </form:select>
                    </div>
                </div>
                <%--<p>회원소분류 :
                    <form:select name="memberGradeCode" id="memberGradeCode_sub" path="memberGradeCode">
                        <option value="choose">선택하세요.</option>
                    </form:select></p>--%>
                <div class="row mb-3">
                    <label for="memberSchool" class="col-sm-2 col-form-label">학교명</label>
                    <div class="col-sm-8">
                        <form:input path="memberSchool" class="form-control" />
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">관심 과목</label>
                    <div class="col-sm-8">
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="국어"/>
                            <label class="form-check-label">국어</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="영어"/>
                            <label class="form-check-label">영어</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="수학"/>
                            <label class="form-check-label">수학</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="사회"/>
                            <label class="form-check-label">사회</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="과학"/>
                            <label class="form-check-label">과학</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox path="memberSubjectCode" value="기타"/>
                            <label class="form-check-label">기타</label>
                        </div>
                    </div>
                    <form:errors path="memberSubjectCode" cssStyle="color: red"/>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">지역대분류</label>
                    <div class="col-sm-8">
                        <form:select path="memberRegionCode" class="form-select">
                            <form:option value="">선택하세요.</form:option>
                            <form:option value="서울특별시">서울특별시</form:option>
                            <form:option value="경기도">경기도</form:option>
                            <form:option value="인천광역시">인천광역시</form:option>
                            <form:option value="강원특별자치도">강원특별자치도</form:option>
                            <form:option value="충청북도">충청북도</form:option>
                            <form:option value="충청남도">충청남도</form:option>
                            <form:option value="대전광역시">대전광역시</form:option>
                            <form:option value="세종특별자치시">세종특별자치시</form:option>
                            <form:option value="전라북도">전라북도</form:option>
                            <form:option value="전라남도">전라남도</form:option>
                            <form:option value="광주광역시">광주광역시</form:option>
                            <form:option value="경상북도">경상북도</form:option>
                            <form:option value="경상남도">경상남도</form:option>
                            <form:option value="부산광역시">부산광역시</form:option>
                            <form:option value="대구광역시">대구광역시</form:option>
                            <form:option value="울산광역시">울산광역시</form:option>
                            <form:option value="제주특별자치도">제주특별자치도</form:option>
                        </form:select>
                    </div>
                </div>
                <%--<p>지역소분류 :
                    <select name="memberRegionCode" id="memberRegionCode_sub" required>
                        <option value="choose">선택하세요.</option>
                    </select></p>--%>
                <input type="submit" class="submitButton" value="수정">
            </form:form>

        </div>
    </div>
</div>


<script>
    /*const update = () => {
        const passwordDB = '${memberDTO.memberPassword}';
        const password = document.getElementById("memberPassword").value;
        if (passwordDB == password) {
            document.updateForm.submit();
            /!*location.href = "/member/update";*!/
        } else {
            alert("비밀번호가 일치하지 않습니다!");
        }
    }*/

    // document 관련된건 DOM 명령
    const phoneNumbCheck = () => {
        const phoneNumb = document.getElementById("memberPhonenumber").value;
        const checkResult3 = document.getElementById("check-result3");
        console.log("입력한 전화번호", phoneNumb);

        let regex3 = new RegExp('0([0-9]{2,3})([0-9]{3,4})([0-9]{4})');
        if (phoneNumb === "" || !regex3.test(phoneNumb)) { // 형식에 맞지 않거나 빈 문자열이라면
            checkResult3.style.color = "red"
            checkResult3.innerHTML = "전화번호를 형식에 맞게 기입하세요.(공백X)"
        } else { // 형식에 맞을때
            $.ajax({

                type: "post",
                url: "/member/phoneNumb-check",
                data: {
                    "memberPhonenumber": phoneNumb
                },
                success: function (res) {
                    console.log("요청성공", res);
                    if (res == "ok") {
                        console.log("사용가능한 전화번호");
                        checkResult3.style.color = "green";
                        checkResult3.innerHTML = "사용가능한 전화번호";
                    } else {
                        console.log("이미 사용중인 전화번호");
                        checkResult3.style.color = "red";
                        checkResult3.innerHTML = "이미 사용중인 전화번호";
                    }
                },
                error: function (err) {
                    console.log("에러발생", err);
                }
            });
        }
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

        if (a.value == "서울특별시") var b = seoul;
        else if (a.value == "경기도") var b = gyeonggi;
        else if (a.value == "인천광역시") var b = incheon;
        else if (a.value == "강원특별자치도") var b = gangwon;
        else if (a.value == "충청북도") var b = chungcheongbuk;
        else if (a.value == "충청남도") var b = chungcheongnam;
        else if (a.value == "대전광역시") var b = daejeon;
        else if (a.value == "세종특별자치시") var b = sejong;
        else if (a.value == "전라북도") var b = jeollabuk;
        else if (a.value == "전라남도") var b = jeollanam;
        else if (a.value == "광주광역시") var b = gwangju;
        else if (a.value == "경상북도") var b = gyeongsangbuk;
        else if (a.value == "경상남도") var b = gyeongsangnam;
        else if (a.value == "부산광역시") var b = busan;
        else if (a.value == "대구광역시") var b = daegu;
        else if (a.value == "울산광역시") var b = ulsan;
        else if (a.value == "제주특별자치도") var b = jeju;
        target.options.length = 0;

        for (x in b) {
            var opt = document.createElement("option");
            opt.value = b[x];
            opt.innerHTML = b[x];
            target.appendChild(opt);

        }
    }
</script>