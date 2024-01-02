package kr.co.sloop.study.service;

import kr.co.sloop.study.domain.StudyGroupDTO;

import java.util.List;

public interface StudyGroupService {
    List<StudyGroupDTO> getAllStudyGroupList();

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO);
}
