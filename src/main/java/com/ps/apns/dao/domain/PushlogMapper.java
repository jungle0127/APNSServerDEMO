package com.ps.apns.dao.domain;

import com.ps.apns.dao.model.Pushlog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PushlogMapper {
    @Delete({
        "delete from pushlog",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pushlog (id, token, ",
        "content, send_time)",
        "values (#{id,jdbcType=BIGINT}, #{token,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{sendTime,jdbcType=TIMESTAMP})"
    })
    int insert(Pushlog record);

    int insertSelective(Pushlog record);

    @Select({
        "select",
        "id, token, content, send_time",
        "from pushlog",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Pushlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Pushlog record);

    @Update({
        "update pushlog",
        "set token = #{token,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "send_time = #{sendTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Pushlog record);
}