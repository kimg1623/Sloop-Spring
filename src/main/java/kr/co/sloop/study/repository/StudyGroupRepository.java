package kr.co.sloop.study.repository;

import kr.co.sloop.study.domain.StudyGroupDTO;

import java.util.HashMap;
import java.util.List;

public interface StudyGroupRepository {
    List<StudyGroupDTO> getAllStudyGroupList();

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO);

    List<HashMap<String, Object>> getSecondCategoryRegionMap();

    StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode);
}
