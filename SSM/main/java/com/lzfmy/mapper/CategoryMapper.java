package com.lzfmy.mapper;

import com.lzfmy.model.Category;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    @Select("select * from category")
    @ResultMap("com.lzfmy.mapper.CategoryMapper.BaseResultMap")
    List<Category> findAllCategory();
}