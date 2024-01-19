package kr.co.sloop.study.service;

import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StudyGroupService {
    List<StudyGroupDTO> getAllStudyGroupList();

    boolean insertNewStudyGroup(StudyGroupDTO studyGroupDTO, String memberIdx);

    List<HashMap<String, Object>> getSecondCategoryRegionMap();

    // List<Option> findOption1(String option1);

    StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode);

    int updateStudyGroup(StudyGroupDTO studyGroupDTO);

    List<HashMap<String,String>> getBoardIdxsByGroupCode(String studyGroupCode);

    int deleteGroupByGroupCode(String studyGroupCode);

    List<CategoryRegionDTO> getCategoryRegion2();

    String getGroupNameByGroupCode(String studyGroupCode);

    boolean joinStudyGroup(String studyGroupIdx, int memberIdx);

    void updateStudyGroupHits(String groupCode);

    String getStudyMemRoleByMemberIdx(String loginMemberIdx, String studyGroupCode);

    List<Map<String, String>> getStudyGroupMembers(String studyGroupCode);

    int updateStudyMemberRoleApprove(String memberIdx, String studyGroupIdx);
}
