package kr.co.sloop.study.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyGroupDTO {
    private int studyGroupIdx; //스터디그룹 index
    private String studyGroupCode; //스터디그룹 코드 랜덤값
    private String studyGroupName; //스터디그룹 이름
    private int studyGroupMethod; //스터디그룹 방식(1:온;2:오프;3:동시)
    private String studyGroupGradeCode;// 학년 카테고리 코드
    private String studyGroupSubjectCode; // 과목 카테고리 코드
    private String studyGroupRegionCode; //지역 카테고리 코드
    private int studyGroupNum; //모집인원
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date studyGroupDuedate; //모집 마감일
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date studyGroupStartdate; //스터디 시작일
    private String studyGroupIntrotxt; //소개글
    private String studyGroupOImg; //스터디 이미지 원본
    private String studyGroupSImg; //스터디 이미지 저장본
    private int studyGroupHits; // 스터디 모집글 조회수

    private String studyGroupGrade;// 학년 카테고리 이름
    private String studyGroupSubject; // 과목 카테고리 이름
    private String studyGroupRegion; //지역 카테고리 이름
}
