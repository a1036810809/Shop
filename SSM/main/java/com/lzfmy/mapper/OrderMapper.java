package com.lzfmy.mapper;

import com.lzfmy.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    @Select("select * from orders where uid = #{uid} order by ordertime desc")
    @ResultMap("com.lzfmy.mapper.OrderMapper.BaseResultMap")
    List<Order> findLastByUid(Integer uid);
    
    @Select("select count(*) from orders o,user u where o.uid=u.uid and u.uid = #{uid}")
    int findByUid(Integer uid);
    
    @Select("select * from orders o where o.uid = #{uid} order by ordertime desc limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.OrderMapper.BaseResultMap")
    List<Order> findByPageUid(@Param("uid") Integer uid,@Param("begin") Integer begin,@Param("limit") Integer limit);

    @Select("select * from orders o where o.state=#{state} limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.OrderMapper.BaseResultMap")
    List<Order> findByState(@Param("state") Integer state,@Param("begin") Integer begin,@Param("limit") Integer limit);

    @Select("select count(*) from orders")
    int findByCount();

    @Select("select * from orders order by ordertime desc limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.OrderMapper.BaseResultMap")
    List<Order> findByPage(@Param("begin") Integer begin,@Param("limit") Integer limit);
}