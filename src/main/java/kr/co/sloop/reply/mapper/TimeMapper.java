package kr.co.sloop.reply.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    @Select("SELECT CURRENT_DATE FROM dual")
    public String getTime();

    String getTime2();


}
