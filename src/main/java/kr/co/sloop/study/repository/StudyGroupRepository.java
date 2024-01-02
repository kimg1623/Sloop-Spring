package kr.co.sloop.study.repository;

import kr.co.sloop.study.domain.StudyGroupDTO;

import java.util.List;

public interface StudyGroupRepository {
    List<StudyGroupDTO> getAllStudyGroupList();

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO);
}
