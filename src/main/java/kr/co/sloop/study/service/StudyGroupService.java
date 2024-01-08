package kr.co.sloop.study.service;

import kr.co.sloop.study.domain.StudyGroupDTO;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;

public interface StudyGroupService {
    List<StudyGroupDTO> getAllStudyGroupList();

    boolean insertNewStudyGroup(StudyGroupDTO studyGroupDTO);

    List<HashMap<String, Object>> getSecondCategoryRegionMap();

    // List<Option> findOption1(String option1);

    StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode);

    int updateStudyGroup(StudyGroupDTO studyGroupDTO);
}
