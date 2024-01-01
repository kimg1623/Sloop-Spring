package kr.co.sloop.study.mapper;

import kr.co.sloop.study.domain.CategoryGradeDTO;
import kr.co.sloop.study.domain.StudyGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudyGroupMapper {
    List<StudyGroupDTO> getAllStudyGroupList();
    List<CategoryGradeDTO> getAllGradeName();

}
