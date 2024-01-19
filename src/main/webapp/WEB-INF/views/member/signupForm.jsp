<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                회원가입
                            </h3>
                        </div>
                        <div class="box-size title_div_contents">
                            슬룹의 회원으로 가입하고, 원하는 스터디 그룹에 참여해보세요.
                        </div>
                    </div>
                </div>
            </div>


            <%-- form:errors 가 span 으로 나오는 출력문과 / <p> 태그로 유효성검사 나오는건 조장님 컨트롤하기 편하신거로 선택해서 쓰시면 될거 같아요--%>
            <%--@elvariable id="memberDTO" type="kr.co.sloop.member.domain.MemberDTO"--%>
            <form:form action="/member/signup" method="post" modelAttribute="memberDTO">
                <%--이메일--%>
                <div class="row mb-3 signup_row">
                    <label for="memberEmail" class="col-sm-2 col-form-label">이메일</label>
                    <div class="col-sm-7">
                        <form:input path="memberEmail" class="form-control" id="memberEmail"
                                    placeholder="name@example.com" required="true"/>
                            <%--에러 문구--%>
                        <form:errors path="memberEmail" cssStyle="color: red" cssClass="check-result"/>
                        <div id="check-result" class="check-result"></div>
                    </div>
                    <form:button type="button" id="checkButton1" class="checkButton" value="중복확인"
                                 onclick="emailCheck()">중복확인</form:button>
                </div>

                <%--비밀번호--%>
                <div class="row mb-3 signup_row">
                    <label for="memberPassword" class="col-sm-2 col-form-label">비밀번호</label>
                    <div class="col-sm-7">
                        <form:input path="memberPassword" class="form-control" type="password" id="memberPassword"
                                    required="true"/>
                            <%--에러 문구--%>
                        <form:errors path="memberPassword" cssStyle="color: red" cssClass="check-result"/>
                        <div id="check-result" class="check-result">영어 대소문자,숫자,특수문자(!,@,#,$)를 각각 한 개 이상을 반드시 포함(8~16자)
                        </div>
                    </div>
                </div>

                <%--닉네임--%>
                <div class="row mb-3 signup_row">
                    <label for="memberNickname" class="col-sm-2 col-form-label">닉네임</label>
                    <div class="col-sm-7">
                        <form:input path="memberNickname" class="form-control" id="memberNickname" placeholder=""
                                    required="true"/>
                            <%--에러 문구--%>
                        <form:errors path="memberNickname" cssStyle="color: red" cssClass="check-result"/>
                        <div id="check-result2" class="check-result">영문 대소문자,숫자,밑줄(_) 중 하나 이상 포함(2~19자, 공백불가)</div>
                    </div>
                    <form:button type="button" id="checkButton2" class="checkButton" value="중복확인"
                                 onclick="nicknameCheck()">중복확인</form:button>
                </div>

                <%--성별--%>
                <fieldset class="row mb-3">
                    <label class="col-form-label col-sm-2">성별</label>
                    <div class="col-sm-7 option_flex">
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="memberGender" value="male"/>
                            <label class="form-check-label">
                                남자
                            </label>
                        </div>
                        <div class="form-check form-option-custom">
                            <form:radiobutton path="memberGender" value="female"/>
                            <label class="form-check-label">
                                여자
                            </label>

                        </div>
                    </div>
                        <%--에러 문구--%>
                    <form:errors path="memberGender" cssStyle="color: red" cssClass="check-result"/>
                </fieldset>


                <%--전화번호--%>
                <div class="row mb-3 signup_row">
                    <label for="memberPhonenumber" class="col-sm-2 col-form-label">전화번호</label>
                    <div class="col-sm-7">
                        <form:input path="memberPhonenumber" class="form-control" id="memberPhonenumber"
                                    placeholder="공백이나 '-'없이 숫자 11자리만 입력하세요." required="true"/>
                            <%--에러 문구--%>
                        <form:errors path="memberPhonenumber" cssStyle="color: red" cssClass="check-result"/>
                        <div id="check-result3" class="check-result">공백이나 '-'없이 숫자 11자리만 입력하세요.</div>
                    </div>
                    <form:button type="button" id="checkButton3" class="checkButton" value="중복확인"
                                 onclick="phoneNumbCheck()">중복확인</form:button>
                </div>

                <%--회원분류--%>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">학년</label>
                    <div class="col-sm-7">
                        <form:select path="memberGradeCode" class="form-select" onchange="memberDivisionChange(this)"
                                     required="true">
                            <form:option value="초등학생">초등학교</form:option>
                            <form:option value="중학생">중학교</form:option>
                            <form:option value="고등학생">고등학교</form:option>
                        </form:select>
                    </div>
                    <form:errors path="memberGradeCode" cssStyle="color: red"/>
                </div>
                <%--<p>회원소분류 :
                    <form:select name="memberGradeCode" id="memberGradeCode_sub" path="memberGradeCode">
                        <option value="choose">선택하세요.</option>
                    </form:select></p>--%>

                <%--학 교 명--%>
                <div class="row mb-3">
                    <label for="memberSchool" class="col-sm-2 col-form-label">학교명</label>
                    <div class="col-sm-7">
                        <form:input path="memberSchool" class="form-control" id="memberSchool" placeholder=""
                                    required="true"/>
                            <%--에러 문구--%>
                        <form:errors path="memberSchool" cssStyle="color: red" cssClass="check-result"/>
                    </div>
                </div>

                <%--관심 과목--%>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">관심 과목</label>
                    <div class="col-sm-8">
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="국어"
                                           id="inlineCheckbox1"/>
                            <label class="form-check-label" for="inlineCheckbox1">국어</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="영어"
                                           id="inlineCheckbox2"/>
                            <label class="form-check-label" for="inlineCheckbox2">영어</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="수학"
                                           id="inlineCheckbox3"/>
                            <label class="form-check-label" for="inlineCheckbox3">수학</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="사회"
                                           id="inlineCheckbox4"/>
                            <label class="form-check-label" for="inlineCheckbox4">사회</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="과학"
                                           id="inlineCheckbox5"/>
                            <label class="form-check-label" for="inlineCheckbox5">과학</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:checkbox cssClass="form-check-input" path="memberSubjectCode" value="기타"
                                           id="inlineCheckbox6"/>
                            <label class="form-check-label" for="inlineCheckbox6">기타</label>

                        </div>
                    </div>
                    <form:errors path="memberSubjectCode" cssStyle="color: red"/>
                </div>

                <%--지역--%>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">지역</label>
                    <div class="col-sm-7">
                        <form:select path="memberRegionCode" class="form-select" onchange="memberSigugunChange(this)"
                                     required="true">
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
                    <form:errors path="memberRegionCode" cssStyle="color: red"/>
                </div>

                <input type="submit" class="submitButton" value="회원가입"/>
            </form:form>
        </div>
    </div>
</div>


<script type="text/javascript">

    /** 이메일 유효성 검사 JavaScript & AJAX & JSON */
        // 이메일 입력값을 가져오고,
        // 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
        // 사용 가능 여부를 이메일 입력창 아래에 표시
        // document 관련된건 DOM 명령
    const emailCheck = () => {
            const email = document.getElementById("memberEmail").value;
            const checkResult = document.getElementById("check-result");
            const checkButton1 = document.getElementById("checkButton1");
            console.log("입력한 이메일", email);
            // 이메일 정규 표현식 초기화
            let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');

            if (email === "" || !regex.test(email)) { // 형식에 맞지 않거나 빈 문자열이라면
                checkResult.style.color = "red"
                checkResult.innerHTML = "이메일을 형식에 맞게 입력하세요.(공백X)"
            } else { // 형식에 맞을때
                $.ajax({
                    // 요청방식: post, url: "email-check", 데이터: 이메일
                    type: "post",
                    url: "/member/email-check",
                    data: {
                        "memberEmail": email
                    },
                    success: function (res) {
                        console.log("요청성공", res);
                        if (res == "ok") {          // service 단에서 "ok" or "no" 를 반환
                            console.log("사용가능한 이메일");
                            checkResult.style.color = "green";
                            checkResult.innerHTML = "사용가능한 이메일";
                            checkButton1.style.backgroundColor = "#b9b9b9";
                            checkButton1.style.color = "#000";

                        } else {
                            console.log("이미 사용중인 이메일");
                            checkResult.style.color = "red";
                            checkResult.innerHTML = "이미 사용중인 이메일";
                        }
                    },
                    error: function (err) {
                        console.log("에러발생", err);
                    }

                });
            }
        }

    /** 닉네임 중복검사도 이메일 유효성과 마찬가지로 동일한 형태로 진행 */
    const nicknameCheck = () => {
        const nickname = document.getElementById("memberNickname").value;
        const checkResult2 = document.getElementById("check-result2");
        const checkButton2 = document.getElementById("checkButton2");
        console.log("입력한 닉네임", nickname);

        let regex2 = new RegExp('[a-zA-Z][a-zA-Z0-9_]{2,19}');
        if (nickname === "" || !regex2.test(nickname)) { // 형식에 맞지 않거나 빈 문자열이라면
            checkResult2.style.color = "red"
            // checkResult2.innerHTML = "닉네임을 형식에 맞게 기입하세요.(공백X)"
        } else { // 형식에 맞을때
            $.ajax({

                type: "post",
                url: "/member/nickname-check",
                data: {
                    "memberNickname": nickname
                },
                success: function (res) {
                    console.log("요청성공", res);
                    if (res == "ok") {
                        console.log("사용가능한 닉네임");
                        checkResult2.style.color = "green";
                        checkResult2.innerHTML = "사용가능한 닉네임";
                        checkButton2.style.backgroundColor = "#b9b9b9";
                        checkButton2.style.color = "#000";
                    } else {
                        console.log("이미 사용중인 이메일");
                        checkResult2.style.color = "red";
                        checkResult2.innerHTML = "이미 사용중인 닉네임";
                    }
                },
                error: function (err) {
                    console.log("에러발생", err);
                }
            });
        }
    }
    const phoneNumbCheck = () => {
        const phoneNumb = document.getElementById("memberPhonenumber").value;
        const checkResult3 = document.getElementById("check-result3");
        const checkButton3 = document.getElementById("checkButton3");
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
                        checkButton3.style.backgroundColor = "#b9b9b9";
                        checkButton3.style.color = "#000";
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

    function memberDivisionChange(e) {

        var element = ["1학년", "2학년", "3학년", "4학년", "5학년", "6학년"];
        var middle = ["1학년", "2학년", "3학년"];
        var high = ["1학년", "2학년", "3학년"];
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
            var opt = document.createElement("form:option");
            opt.value = d[x];
            opt.innerHTML = d[x];
            target.appendChild(opt);
        }
    }

    /*회원 지역 구분*/

    function memberSigugunChange(a) {
        var seoul = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구",
            "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구",
            "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
        var gyeonggi = ["수원시 장안구", "수원시 권선구", "수원시 팔달구", "수원시 영통구", "성남시 수정구", "성남시 중원구",
            "성남시 분당구", "의정부시", "안양시 만안구", "안양시 동안구", "부천시", "광명시", "평택시", "동두천시", "안산시 상록구",
            "안산시 단원구", "고양시 덕양구", "고양시 일산동구", "고양시 일산서구", "과천시", "구리시", "남양주시", "오산시",
            "시흥시", "군포시", "의왕시", "하남시", "용인시 처인구", "용인시 기흥구", "용인시 수지구", "파주시", "이천시",
            "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군", "양평군"];
        var incheon = ["계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];
        var gangwon = ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군",
            "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"];
        var chungcheongbuk = ["청주시 상당구", "청주시 서원구", "청주시 흥덕구", "청주시 청원구", "충주시", "제천시", "보은군",
            "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군"];
        var chungcheongnam = ["천안시 동남구", "천안시 서북구", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시",
            "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"];
        var daejeon = ["대덕구", "동구", "서구", "유성구", "중구"];
        var sejong = ["세종특별자치시"];
        var jeollabuk = ["전주시 완산구", "전주시 덕진구", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군",
            "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"];
        var jeollanam = ["목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군",
            "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"];
        var gwangju = ["광산구", "남구", "동구", "북구", "서구"];
        var gyeongsangbuk = ["포항시 남구", "포항시 북구", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시",
            "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군",
            "봉화군", "울진군", "울릉군"];
        var gyeongsangnam = ["창원시 의창구", "창원시 성산구", "창원시 마산합포구", "창원시 마산회원구", "창원시 진해구",
            "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군",
            "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"];
        var busan = ["강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구",
            "연제구", "영도구", "중구", "해운대구", "기장군"];
        var daegu = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
        var ulsan = ["남구", "동구", "북구", "중구", "울주군"];
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
            var opt = document.createElement("form:option");
            opt.value = b[x];
            opt.innerHTML = b[x];
            target.appendChild(opt);

        }
    }
</script>
