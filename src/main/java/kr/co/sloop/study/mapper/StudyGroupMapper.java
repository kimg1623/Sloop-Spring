package kr.co.sloop.study.mapper;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.CategorySubjectDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudyGroupMapper {
    List<StudyGroupDTO> getAllStudyGroupList();
    List<CategoryGradeDTO> getAllGradeName(); // 학교학년 카테고리 이름(코드) 불러오기
    List<CategorySubjectDTO> getAllSubjectName(); // 과목 카테고리 이름(코드) 불러오기
    List<CategoryRegionDTO> getAllRegionName(); // 지역 카테고리 이름(코드) 불러오기

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO); // 새 스터디 그룹 등록하기
}
