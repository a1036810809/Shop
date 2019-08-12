package com.lzfmy.service;

import com.lzfmy.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategory();
    public void save(Category category);
    public Category findByCid(Integer cid);
    public void delete(Category category);
    public void update(Category category);
}
