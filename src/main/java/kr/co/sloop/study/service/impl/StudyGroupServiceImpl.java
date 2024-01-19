package kr.co.sloop.study.service.impl;


import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.repository.StudyGroupRepository;
import kr.co.sloop.study.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean insertNewStudyGroup(StudyGroupDTO studyGroupDTO, String memberIdx) {
        int result = 0;
        // 그룹 생성
        result = studyGroupRepository.insertNewStudyGroup(studyGroupDTO);
        if(result != 1)
            return false;  // 실패

        // StudyGroupDTO 객체의 studyGroupIdx 속성값에 auto_increment PK 값이 이미 들어있음
        int studyGroupIdx = studyGroupDTO.getStudyGroupIdx();
        // 게시판 4개 생성
        result = studyGroupRepository.create4boards(studyGroupIdx);
        if (result < 1)
            return false; // 게시판 생성 실패

        // 스터디 그룹을 개설한 회원에게 ROLE_STUDY_LEADER 권한 부여
        result = studyGroupRepository.insertMemberForGrantLeader(studyGroupIdx, memberIdx);
        if(result != 1)
            return false;  // 실패

        // 성공
        return true;

    }

    @Override
    public List<HashMap<String, Object>> getSecondCategoryRegionMap() {
        return studyGroupRepository.getSecondCategoryRegionMap();
    }

    @Override
    public StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode) {
        return studyGroupRepository.getStudyGroupByGroupCode(studyGroupCode);
    }

    @Override
    public int updateStudyGroup(StudyGroupDTO studyGroupDTO) {
        return studyGroupRepository.updateStudyGroup(studyGroupDTO);
    }

    @Override
    public List<HashMap<String,String>> getBoardIdxsByGroupCode(String studyGroupCode) {
        return studyGroupRepository.getBoardIdxsByGroupCode(studyGroupCode);
    }

    @Override
    public int deleteGroupByGroupCode(String studyGroupCode) {
        return studyGroupRepository.deleteGroupByGroupCode(studyGroupCode);
    }

    public List<CategoryRegionDTO> getCategoryRegion2(){
        return studyGroupRepository.getCategoryRegion2();
    }

    @Override
    public String getGroupNameByGroupCode(String studyGroupCode) {
        return studyGroupRepository.getGroupNameByGroupCode(studyGroupCode);
    }

    @Override
    public boolean joinStudyGroup(String studyGroupIdx, int memberIdx) {
        int result = studyGroupRepository.joinStudyGroup(studyGroupIdx, memberIdx);

        if(result != 0){    // 성공
            return true;
        }else{  // 실패
            return false;
        }
    }

    @Override
    public void updateStudyGroupHits(String groupCode) {
        studyGroupRepository.updateStudyGroupHits(groupCode);
    }


    @Override
    public String getStudyMemRoleByMemberIdx(String loginMemberIdx, String studyGroupCode) {
        return studyGroupRepository.getStudyMemRoleByMemberIdx(loginMemberIdx, studyGroupCode);
    }

    @Override
    public List<Map<String, String>> getStudyGroupMembers(String studyGroupCode) {
        return studyGroupRepository.getStudyGroupMembers(studyGroupCode);
    }

    @Override
    public int updateStudyMemberRoleApprove(String memberIdx, String studyGroupIdx) {
        return studyGroupRepository.updateStudyMemberRoleApprove(memberIdx, studyGroupIdx);
    }
}
