package kr.co.sloop.study.repository.impl;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.CategoryRegionDTO;
import kr.co.sloop.study.domain.CategorySubjectDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.mapper.StudyGroupMapper;
import kr.co.sloop.study.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class StudyGroupRepositoryImpl implements StudyGroupRepository {

    private final StudyGroupMapper studyGroupMapper;

    // 카테고리 코드:이름 저장
    private final Map<String, String> categoryGradeMap;
    private final Map<String, String> categorySubjectMap;
    private final Map<String, String> categoryRegionMap;

    @Override
    public List<StudyGroupDTO> getAllStudyGroupList() {
        List<StudyGroupDTO> studyGroupList = studyGroupMapper.getAllStudyGroupList();
        getALlCategory();
        for(StudyGroupDTO studyGroup : studyGroupList){
            studyGroup.setStudyGroupGrade(categoryGradeMap.get(studyGroup.getStudyGroupGradeCode()));
            studyGroup.setStudyGroupSubject(categorySubjectMap.get(studyGroup.getStudyGroupSubjectCode()));
            studyGroup.setStudyGroupRegion(categoryRegionMap.get(studyGroup.getStudyGroupRegionCode()));
        }
        return studyGroupList;
    }

    @Override
    public int insertNewStudyGroup(StudyGroupDTO studyGroupDTO) {
        int result = studyGroupMapper.insertNewStudyGroup(studyGroupDTO);
        return result;
    }

    // 스터디 그룹 생성 후 기본 게시판 4개 생성
    @Override
    public int create4boards(int studyGroupIdx) {
        return studyGroupMapper.create4boards(studyGroupIdx);
    }

    // 스터디 그룹 생성 후 해당 회원에게 ROLE_STUDY_LEADER 권한 부여
    @Override
    public int insertMemberForGrantLeader(int studyGroupIdx, String memberIdx) {

        Map<String, String> studyGroupAndMemberIdx = new HashMap<String, String>();
        studyGroupAndMemberIdx.put("studyGroupIdx",String.valueOf(studyGroupIdx));
        studyGroupAndMemberIdx.put("memberIdx",memberIdx);
        return studyGroupMapper.insertMemberForGrantLeader(studyGroupAndMemberIdx);
    }

    @Override
    public List<HashMap<String, Object>> getSecondCategoryRegionMap() {
        return null;
    }

    @Override
    public StudyGroupDTO getStudyGroupByGroupCode(String studyGroupCode) {
        StudyGroupDTO studyGroup =  studyGroupMapper.selectStudyGroupByGroupCode(studyGroupCode);
        studyGroup.setStudyGroupGrade(categoryGradeMap.get(studyGroup.getStudyGroupGradeCode()));
        studyGroup.setStudyGroupSubject(categorySubjectMap.get(studyGroup.getStudyGroupSubjectCode()));
        studyGroup.setStudyGroupRegion(categoryRegionMap.get(studyGroup.getStudyGroupRegionCode()));
        return studyGroup;
    }

    @Override
    public int updateStudyGroup(StudyGroupDTO studyGroupDTO) {
        return studyGroupMapper.updateStudyGroup(studyGroupDTO);
    }

    @Override
    public List<HashMap<String,String>> getBoardIdxsByGroupCode(String studyGroupCode) {
        return studyGroupMapper.getBoardIdxsByGroupCode(studyGroupCode);
    }

    @Override
    public int deleteGroupByGroupCode(String studyGroupCode) {
        return studyGroupMapper.deleteGroupByGroupCode(studyGroupCode);
    }


    // 생성자에서 실행하도록 변경하기 fix 필요!
    private void getALlCategory() {
        getCategoryGrade();
        getCategorySubject();
        getCategoryRegion();
    }

    // 학교학년 카테고리 테이블을 불러와서 categoryGradeMap 필드에 저장
    public void getCategoryGrade() {
        List<CategoryGradeDTO> categoryGradeList;
        categoryGradeList = studyGroupMapper.getAllGradeName();
        for(CategoryGradeDTO i : categoryGradeList)
            categoryGradeMap.put(i.getCategoryGradeCode(),i.getCategoryGradeName());
    }

    // 과목 카테고리 테이블을 불러와서 categorySubjectMap 필드에 저장
    public void getCategorySubject() {
        List<CategorySubjectDTO> categorySubjectList;
        categorySubjectList = studyGroupMapper.getAllSubjectName();
        for(CategorySubjectDTO i : categorySubjectList)
            categorySubjectMap.put(i.getCategorySubjectCode(),i.getCategorySubjectName());
    }

    // 지역 카테고리 테이블을 불러와서 categorySubjectMap 필드에 저장
    public void getCategoryRegion() {
        List<CategoryRegionDTO> categoryRegionList;
        categoryRegionList = studyGroupMapper.getAllRegionName();
        for(CategoryRegionDTO i : categoryRegionList)
            categoryRegionMap.put(i.getCategoryRegionCode(),i.getCategoryRegionName());
    }
    // 지역 카테고리 테이블을 불러와서 categorySubjectMap 필드에 저장
    public List<CategoryRegionDTO> getCategoryRegion2() {
        List<CategoryRegionDTO> categoryRegionList = studyGroupMapper.getAllRegionName();
        return categoryRegionList;
    }

    @Override
    public String getGroupNameByGroupCode(String studyGroupCode) {
        return studyGroupMapper.getGroupNameByGroupCode(studyGroupCode);
    }

    @Override
    public int joinStudyGroup(String studyGroupIdx, int memberIdx) {
        return studyGroupMapper.joinStudyGroup(studyGroupIdx, memberIdx);
    }

    @Override
    public void updateStudyGroupHits(String groupCode) {
        studyGroupMapper.updateStudyGroupHits(groupCode);
    }


    @Override
    public String getStudyMemRoleByMemberIdx(String loginMemberIdx, String studyGroupCode) {
        return studyGroupMapper.getStudyMemRoleByMemberIdx(loginMemberIdx, studyGroupCode);
    }

    @Override
    public List<Map<String, String>> getStudyGroupMembers(String studyGroupCode) {
        return studyGroupMapper.getStudyGroupMembers(studyGroupCode);
    }

    @Override
    public int updateStudyMemberRoleApprove(String memberIdx, String studyGroupIdx) {
        return studyGroupMapper.updateStudyMemberRoleApprove(memberIdx, studyGroupIdx);
    }
}
