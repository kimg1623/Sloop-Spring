package kr.co.sloop.study.service.impl;


import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.repository.StudyGroupRepository;
import kr.co.sloop.study.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudyGroupServiceImpl implements StudyGroupService {

    @Autowired
    private final StudyGroupRepository studyGroupRepository;
    @Override
    public List<StudyGroupDTO> getAllStudyGroupList() {
        return studyGroupRepository.getAllStudyGroupList();
    }

    @Override
    public int insertNewStudyGroup(StudyGroupDTO studyGroupDTO) {
        return studyGroupRepository.insertNewStudyGroup(studyGroupDTO);
    }

    @Override
    public List<HashMap<String, Object>> getSecondCategoryRegionMap() {
        return studyGroupRepository.getSecondCategoryRegionMap();
    }

    @Override
    public StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode) {
        return studyGroupRepository.getStudyGroupByGroupCode(studyGroupCode);
    }
}
