package kr.co.sloop.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class MemberDTO {
    private int memberIdx;              // '회원 index',

    @NotNull(message = "이메일은 필수 입력값입니다")
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    private String memberEmail;         // '회원 이메일',

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$])(?=.*[0-9])[A-Za-z\\d!@#$]{8,16}$",
            message = "비밀번호는 8자리 이상 16자리 이하, 영어 대소문자 각각 한 개 이상, 숫자 한 개 이상, " +
                    "특수기호 !, @, #, $ 중 한 개 이상을 포함해야 합니다")
    private String memberPassword;      //'회원 비밀번호',

    @NotNull(message = "닉네임은 필수 입력값입니다")
    private String memberNickname;      // '회원 닉네임',

    private String memberGender;        //	'회원 성별',


    private Timestamp memberRegdate;         // '회원가입 날짜',

    @Pattern(regexp = "^0([0-9]{2,3})([0-9]{3,4})([0-9]{4})$",
            message = "- 기호를 제외한 숫자만 입력해주세요")
    private String memberPhonenumber;   // '회원 핸드폰번호',

    @NotNull(message = "학교명은 필수 입력값입니다")
    private String memberSchool;        // '회원 학교',
    @NotNull(message = "학년은 필수 입력값입니다")
    private String memberGradeCode;        // '학년 카테고리 코드',
    @NotNull(message = "관심과목은 필수 입력값입니다")
    private String memberSubjectCode;      // '과목 카테고리 코드',
    @NotNull(message = "지역은 필수 입력값입니다")
    private String memberRegionCode;       // '지역 카테고리 코드',
    private String authority;            // 회원 권한
    private String memberProfile;            // 프로필사진

    private String sessionIdx;

    /** Join column data*/

    private int studyGroupIdx;
    private String studyGroupCode;
    private String studyGroupName;
    private String studyMemRole;


}
