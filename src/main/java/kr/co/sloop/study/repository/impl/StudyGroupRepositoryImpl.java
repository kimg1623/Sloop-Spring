package kr.co.sloop.study.repository.impl;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.CategorySubjectDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.mapper.StudyGroupMapper;
import kr.co.sloop.study.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j
public class StudyGroupRepositoryImpl implements StudyGroupRepository {

    private final SqlSessionTemplate sql;
    @Autowired
    private final StudyGroupMapper studyGroupMapper;

    // 카테고리 코드:이름 저장
    private final Map<String, String> categoryGradeMap;
    private final Map<String, String> categorySubjectMap;
    private final Map<String, String> categoryRegionMap;

    @Override
    public List<StudyGroupDTO> getAllStudyGroupList() {
        List<StudyGroupDTO> studyGroupList = studyGroupMapper.getAllStudyGroupList();
        getALlCategory();
        for(StudyGroupDTO studyGroup : studyGroupList){
            studyGroup.setStudyGroupGrade(categoryGradeMap.get(studyGroup.getStudyGroupGradeCode()));
            studyGroup.setStudyGroupSubject(categorySubjectMap.get(studyGroup.getStudyGroupSubjectCode()));
            studyGroup.setStudyGroupRegion(categoryRegionMap.get(studyGroup.getStudyGroupRegionCode()));
        }
        return studyGroupList;
    }

    @Override
    public int insertNewStudyGroup(StudyGroupDTO studyGroupDTO) {
        int result = studyGroupMapper.insertNewStudyGroup(studyGroupDTO);
        return result;
    }

    // 생성자에서 실행하도록 변경하기 fix 필요!
    private void getALlCategory() {
        getCategoryGrade();
        getCategorySubject();
        getCategoryRegion();
    }

    // 학교학년 카테고리 테이블을 불러와서 categoryGradeMap 필드에 저장
    public void getCategoryGrade() {
        List<CategoryGradeDTO> categoryGradeList;
        categoryGradeList = studyGroupMapper.getAllGradeName();
        for(CategoryGradeDTO i : categoryGradeList)
            categoryGradeMap.put(i.getCategoryGradeCode(),i.getCategoryGradeName());
    }

    // 과목 카테고리 테이블을 불러와서 categorySubjectMap 필드에 저장
    public void getCategorySubject() {
        List<CategorySubjectDTO> categorySubjectList;
        categorySubjectList = studyGroupMapper.getAllSubjectName();
        for(CategorySubjectDTO i : categorySubjectList)
            categorySubjectMap.put(i.getCategorySubjectCode(),i.getCategorySubjectName());
    }

    // 지역 카테고리 테이블을 불러와서 categorySubjectMap 필드에 저장
    public void getCategoryRegion() {
        List<CategoryRegionDTO> categoryRegionList;
        categoryRegionList = studyGroupMapper.getAllRegionName();
        for(CategoryRegionDTO i : categoryRegionList)
            categoryRegionMap.put(i.getCategoryRegionCode(),i.getCategoryRegionName());
    }
}
