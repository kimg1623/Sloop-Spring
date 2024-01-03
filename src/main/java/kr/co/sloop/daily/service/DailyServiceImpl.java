package kr.co.sloop.daily.service;

import kr.co.sloop.daily.domain.DailyDTO;
import kr.co.sloop.daily.repository.DailyRepositoryImpl;
import kr.co.sloop.daily.service.impl.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyRepositoryImpl dailyRepository;

    //공부인증 전체리스트 불러오기
    @Override
    public List<DailyDTO> getAllDailyList() {
        return dailyRepository.getAllDailyList();
    }

    //공부인증 게시글 작성
    @Override
    public int dailyWrite(DailyDTO dailyDTO) {
        //임의
        dailyDTO.setMemberIdx(1);
        int result;
        result=dailyRepository.insertPost(dailyDTO);

       return dailyRepository.dailyWrite(dailyDTO);
    }



    //제목으로 글 검색
    @Override
    public List<DailyDTO> getDailyListByTitle(String postDailyTitle) {
        List<DailyDTO> dailyListByTitle = dailyRepository.getDailyListByTitle(postDailyTitle);
        return dailyListByTitle;
    }

    //상세보기
    @Override
    public DailyDTO findByPostIdx(int postIdx) {
        DailyDTO dailyByPostIdx = dailyRepository.findByPostIdx(postIdx);
        return dailyByPostIdx;
    }

    //수정하기
    @Override
    public void update(DailyDTO dailyDTO) {
        dailyRepository.update(dailyDTO);
    }

    //삭제
    @Override
    public void delete(int postIdx) {
        dailyRepository.delete(postIdx);
    }
}
