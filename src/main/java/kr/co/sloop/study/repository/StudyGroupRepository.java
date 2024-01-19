package kr.co.sloop.study.repository;

import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StudyGroupRepository {
    List<StudyGroupDTO> getAllStudyGroupList();

    int insertNewStudyGroup(StudyGroupDTO studyGroupDTO);

    int create4boards(int studyGroupIdx);

    int insertMemberForGrantLeader(int studyGroupIdx, String memberIdx);

    List<HashMap<String, Object>> getSecondCategoryRegionMap();

    StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode);

    int updateStudyGroup(StudyGroupDTO studyGroupDTO);

    List<HashMap<String,String>> getBoardIdxsByGroupCode(String studyGroupCode);

    int deleteGroupByGroupCode(String studyGroupCode);

    List<CategoryRegionDTO> getCategoryRegion2();

    String getGroupNameByGroupCode(String studyGroupCode);

    int joinStudyGroup(String studyGroupIdx, int memberIdx);

    void updateStudyGroupHits(String groupCode);

    String getStudyMemRoleByMemberIdx(String loginMemberIdx, String studyGroupCode);

    List<Map<String, String>> getStudyGroupMembers(String studyGroupCode);

    int updateStudyMemberRoleApprove(String memberIdx, String studyGroupIdx);
}
