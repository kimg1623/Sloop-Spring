package kr.co.sloop.sample;

import kr.co.sloop.sample.mapper.TimeMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TimeMapperTests {

    @Autowired
    private TimeMapper timeMapper; // mapper 인터페이스

    @Test
    public void testGetTime() { // mapper 인터페이스에 애너테이션으로 삽입한 쿼리문 테스트
        log.info(timeMapper.getClass().getName());
        log.info(timeMapper.getTime());
    }

    @Test
    public void testGetTime2() { // resources/mappers에 xml 파일로 지정한 쿼리문 테스트
        log.info("getTime2");
        log.info(timeMapper.getTime2());
    }
}
