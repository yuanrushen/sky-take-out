package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User selectByOpenId(String openid);

    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long userId);

    @Select("select count(id) from user where DATE(create_time) = #{date} GROUP BY DATE(create_time)")
    Integer getnewuser(Map map);
    @Select("select count(id) from user where DATE(create_time) < #{date} GROUP BY DATE(create_time)")
    Integer getolduser(Map map);
}
