package com.lzfmy.mapper;

import com.lzfmy.model.Orderitem;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderitemMapper {
    int deleteByPrimaryKey(Integer itemid);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(Integer itemid);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
    
    @Select("select * FROM orderitem where oid=#{oid}")
    @ResultMap("com.lzfmy.mapper.OrderitemMapper.BaseResultMap")
    List<Orderitem> findByOid(Integer oid);
}