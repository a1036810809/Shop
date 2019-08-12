package com.lzfmy.mapper;

import com.lzfmy.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByName(String username);
    
    @Select("select * from user where code = #{code}")
    User findByCode(String code);
    
    @Select("select * from user where username = #{username} and password = #{password} and state = 1")
    User login(User user);
    
    @Select("select count(*) from user")
    int findCount();
    
    @Select("select * from user limit #{begin},#{limit}")
    List<User> findByPage(@Param("begin") int begin,@Param("limit") int limit);
}