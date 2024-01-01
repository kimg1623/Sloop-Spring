package kr.co.sloop.study.repository;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.mapper.StudyGroupMapper;
import kr.co.sloop.study.repository.impl.StudyGroupRepository;
import lombok.NoArgsConstructor;
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
    private final Map<String, String> categoryGradeMap;

    @Override
    public List<StudyGroupDTO> getAllStudyGroupList() {
        List<StudyGroupDTO> studyGroupList = studyGroupMapper.getAllStudyGroupList();
        getCategoryGrade();
        for(StudyGroupDTO studyGroup : studyGroupList){
            studyGroup.setStudyGroupGrade(categoryGradeMap.get(studyGroup.getStudyGroupGradeCode()));
        }
        return studyGroupList;
    }

    public void getCategoryGrade() {
        List<CategoryGradeDTO> categoryGradeList;
        categoryGradeList = studyGroupMapper.getAllGradeName();
        for(CategoryGradeDTO i : categoryGradeList)
            categoryGradeMap.put(i.getCategoryGradeCode(),i.getCategoryGradeName());
    }
}
