package com.lzfmy.service;

import com.lzfmy.model.Categorysecond;

import java.util.List;

public interface CategorySecondService {
    public int findCount();
    public List<Categorysecond> findByPage(Integer begin, Integer limit);
    public void save(Categorysecond categorySecond);
    public Categorysecond findByCsid(Integer csid);
    public void delete(Categorysecond categorySecond);
    public void update(Categorysecond categorySecond);
    public List<Categorysecond> findAll();
    public List<Categorysecond> findByCid(Integer cid);
}
