package com.lzfmy.mapper;

import com.lzfmy.model.Categorysecond;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.w3c.dom.ls.LSInput;

import java.util.List;

public interface CategorysecondMapper {
    int deleteByPrimaryKey(Integer csid);

    int insert(Categorysecond record);

    int insertSelective(Categorysecond record);

    Categorysecond selectByPrimaryKey(Integer csid);

    int updateByPrimaryKeySelective(Categorysecond record);

    int updateByPrimaryKey(Categorysecond record);
    
    @Select("select count(*) from categorysecond")
    int findCount();
    
    @Select("select * FROM categorysecond where cid=#{cid}")
    @ResultMap("com.lzfmy.mapper.CategorysecondMapper.BaseResultMap")
    List<Categorysecond> findByCid(Integer cid);
    
    @Select("select * from categorysecond order by csid desc limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.CategorysecondMapper.BaseResultMap")
    List<Categorysecond> findByPage(@Param("begin") Integer begin,@Param("limit") Integer limit);
    
    @Select("select * from categorysecond")
    @ResultMap("com.lzfmy.mapper.CategorysecondMapper.BaseResultMap")
    List<Categorysecond> findAll();
}