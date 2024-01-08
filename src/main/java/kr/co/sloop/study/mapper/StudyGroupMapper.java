package kr.co.sloop.study.mapper;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.CategorySubjectDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudyGroupMapper {
    List<StudyGroupDTO> getAllStudyGroupList();
    List<CategoryGradeDTO> getAllGradeName(); // 학교학년 카테고리 이름(코드) 불러오기
    List<CategorySubjectDTO> getAllSubjectName(); // 과목 카테고리 이름(코드) 불러오기
    List<CategoryRegionDTO> getAllRegionName(); // 지역 카테고리 이름(코드) 불러오기

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO); // 새 스터디 그룹 등록하기
    int create4boards(int studyGroupIdx); // 스터디그룹 개설 시 게시판 4개 생성

    StudyGroupDTO selectStudyGroupByGroupCode(String studyGroupCode); // 스터디 그룹 code로 불러오기

    int updateStudyGroup(StudyGroupDTO studyGroupDTO); // 스터디 그룹 정보 수정


    List<HashMap<String,String>> getBoardIdxsByGroupCode(int studyGroupIdx); // 스터디 그룹 코드로 게시판 idx 가져오기

    int deleteGroupByGroupCode(String studyGroupCode); // 스터디 그룹 코드로 스터디 그룹 삭제
}
