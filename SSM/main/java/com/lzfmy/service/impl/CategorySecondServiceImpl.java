package com.lzfmy.service.impl;

import com.lzfmy.mapper.CategorysecondMapper;
import com.lzfmy.model.Categorysecond;
import com.lzfmy.service.CategorySecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categorySecondService")
public class CategorySecondServiceImpl implements CategorySecondService {

    @Autowired
    private CategorysecondMapper categorysecondMapper;

    public int findCount() {
        return categorysecondMapper.findCount();
    }

    public List<Categorysecond> findByPage(Integer begin, Integer limit) {
        return categorysecondMapper.findByPage(begin,limit);
    }
    
    
    public List<Categorysecond> findByCid(Integer cid){
        return categorysecondMapper.findByCid(cid);
    }
    
    public void save(Categorysecond categorySecond) {
        categorysecondMapper.insertSelective(categorySecond);
    }

    
    public Categorysecond findByCsid(Integer csid) {
        return categorysecondMapper.selectByPrimaryKey(csid);
    }

    public void delete(Categorysecond categorySecond) {
        categorysecondMapper.deleteByPrimaryKey(categorySecond.getCsid());
    }
    
    public void update(Categorysecond categorySecond) {
        categorysecondMapper.updateByPrimaryKeySelective(categorySecond);
    }

    
    public List<Categorysecond> findAll() {
        return categorysecondMapper.findAll();
    }
}
