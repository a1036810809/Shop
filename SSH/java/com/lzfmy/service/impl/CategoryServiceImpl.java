package com.lzfmy.service.impl;

import com.lzfmy.dao.CategoryDao;
import com.lzfmy.model.Category;
import com.lzfmy.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List findAllCategory() {
        return categoryDao.findAllCategory();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Category findByCid(Integer cid) {
        return categoryDao.findByCid(cid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void update(Category category) {
        categoryDao.update(category);
    }
}
