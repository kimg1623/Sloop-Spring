package kr.co.sloop.study.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StudyGroupDTO {
    // 공작소에는 Sample까지만 있지만 뒤에 DTO 붙여서 작업해주세요.
    private int studygrpIdx; //스터디그룹 index
    private String studygrpCode; //스터디그룹 코드 랜덤값
    private String studygrpName; //스터디그룹 이름
    private int studygrpMethod; //스터디그룹 방식(1:온;2:오프;3:동시)
    private String studygrpGradeCode;// 학년 카테고리 코드
    private String studygrpSubjectCode; // 과목 카테고리 코드
    private String studygrpRegionCode; //지역 카테고리 코드
    private int studygrpNum; //모집인원
    private Date studygrpDuedate; //모집 마감일
    private Date studygrpStartdate; //스터디 시작일
    private String studygrpIntrotxt; //소개글
    private String studygrpOImg; //스터디 이미지 원본
    private String studygrpSImg; //스터디 이미지 저장본
    private int studygrpHits; // 스터디 모집글 조회수
}
