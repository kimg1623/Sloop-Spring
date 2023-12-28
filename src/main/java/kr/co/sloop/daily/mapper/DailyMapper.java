package kr.co.sloop.daily.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DailyMapper {
    @Select("SELECT CURRENT_DATE FROM dual")
    public String getTime();

    String getTime2();


}
