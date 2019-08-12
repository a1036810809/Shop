package com.lzfmy.mapper;

import com.lzfmy.model.Adminuser;
import org.apache.ibatis.annotations.Select;

public interface AdminuserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Adminuser record);

    int insertSelective(Adminuser record);

    Adminuser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Adminuser record);

    int updateByPrimaryKey(Adminuser record);

    @Select("select * from adminuser where username = #{username} and password = #{password}")
    Adminuser login(Adminuser adminuser);
}