package kr.co.sloop.study.repository;

import kr.co.sloop.study.domain.StudyGroupDTO;
import kr.co.sloop.study.repository.impl.StudyGroupRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j
public class StudyGroupRepositoryImpl implements StudyGroupRepository {

    private final SqlSessionTemplate sql;
    @Override
    public List<StudyGroupDTO> getAllStudyGroupList() {
        return sql.selectList("kr.co.sloop.study.mapper.StudyGroupMapper.getAllStudyGroupList");
    }
}
