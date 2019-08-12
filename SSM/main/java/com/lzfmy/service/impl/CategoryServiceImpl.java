package com.lzfmy.service.impl;

import com.lzfmy.mapper.CategoryMapper;
import com.lzfmy.model.Category;
import com.lzfmy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    
    public List findAllCategory() {
        return categoryMapper.findAllCategory();
    }

    
    public void save(Category category) {
        categoryMapper.insertSelective(category);
    }

    
    public Category findByCid(Integer cid) {
        return categoryMapper.selectByPrimaryKey(cid);
    }

    
    public void delete(Category category) {
        categoryMapper.deleteByPrimaryKey(category.getCid());
    }

    
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
