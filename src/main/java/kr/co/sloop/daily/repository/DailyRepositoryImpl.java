package kr.co.sloop.daily.repository;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.domain.PageDTO;
import kr.co.sloop.daily.repository.impl.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DailyRepositoryImpl implements DailyRepository {
    private final SqlSessionTemplate sql;

    // 공부인증 전체리스트 불러오기
    // 페이징-리스트
    @Override
    public List<DailyDTO> pageList(PageDTO pageDTO) {
        return sql.selectList("Daily.getAllDailyList",pageDTO);
    }

    // PageDTO 전체 글 개수 조회용
    public int boardCountByBoardIdx(PageDTO boardIdx) {
        return sql.selectOne("Daily.boardCountByBoardIdx",boardIdx);
    }

    // 공부인증 게시글 작성
    @Override
    public int dailyWrite(DailyDTO dailyDTO) {
        return sql.insert("Daily.dailyWrite", dailyDTO);
    }

    // post용
    @Override
    public int insertPost(DailyDTO dailyDTO){
        int result = sql.insert("Daily.insertPost", dailyDTO);
        return result;
    }

    // 상세보기
    @Override
    public DailyDTO findByPostIdx(int postIdx) {
        return sql.selectOne("Daily.findByPostIdx",postIdx);
    }

    // 수정하기
    @Override
    public int update(DailyDTO dailyDTO) {
        return sql.update("Daily.update",dailyDTO);
    }

    // 삭제하기
    @Override
    public void delete(int postIdx) {
        sql.delete("Daily.delete",postIdx);
    }

    // 조회수
    public void updateViewCnt(int postIdx) {
        sql.update("Daily.updateViewCnt",postIdx);
    }

}
