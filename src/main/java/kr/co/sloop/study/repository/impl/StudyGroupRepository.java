package kr.co.sloop.study.repository.impl;

import kr.co.sloop.study.domain.StudyGroupDTO;

import java.util.List;

public interface StudyGroupRepository {
    List<StudyGroupDTO> getAllStudyGroupList();
}
