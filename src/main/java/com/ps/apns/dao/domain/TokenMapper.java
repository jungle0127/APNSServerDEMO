package com.ps.apns.dao.domain;

import com.ps.apns.dao.model.Token;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TokenMapper {
    @Delete({
        "delete from token",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into token (id, token, ",
        "active)",
        "values (#{id,jdbcType=BIGINT}, #{token,jdbcType=VARCHAR}, ",
        "#{active,jdbcType=TINYINT})"
    })
    int insert(Token record);

    int insertSelective(Token record);

    @Select({
        "select",
        "id, token, active",
        "from token",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Token selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Token record);

    @Update({
        "update token",
        "set token = #{token,jdbcType=VARCHAR},",
          "active = #{active,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Token record);
}