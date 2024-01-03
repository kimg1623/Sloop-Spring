package kr.co.sloop.daily.repository;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.repository.impl.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DailyRepositoryImpl implements DailyRepository {

    private final SqlSessionTemplate sql;

    //공부인증 전체리스트 불러오기
    public List<DailyDTO> getAllDailyList(){
        return sql.selectList("Daily.getAllDailyList");
    }

    //공부인증 게시글 작성
    public int dailyWrite(DailyDTO dailyDTO) {
        return sql.insert("Daily.dailyWrite",dailyDTO);
    }

    //post용
    public int insertPost(DailyDTO dailyDTO){
        int result = sql.insert("Daily.insertPost", dailyDTO);
        return result;
    }


    //제목으로 글 검색
    public List<DailyDTO> getDailyListByTitle(String postDailyTitle) {
        return sql.selectOne("Daily.getDailyListByTitle",postDailyTitle);
    }

    //상세보기
    @Override
    public DailyDTO findByPostIdx(int postIdx) {
        return sql.selectOne("Daily.findByPostIdx",postIdx);
    }

    //수정하기
    @Override
    public void update(DailyDTO dailyDTO) {
        sql.update("Daily.update",dailyDTO);
    }

    //삭제하기
    @Override
    public void delete(int postIdx) {
        sql.delete("Daily.delete",postIdx);
    }
}
