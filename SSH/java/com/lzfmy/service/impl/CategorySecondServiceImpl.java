package com.lzfmy.service.impl;

import com.lzfmy.dao.CategorySecondDao;
import com.lzfmy.model.Categorysecond;
import com.lzfmy.service.CategorySecondService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categorySecondService")
public class CategorySecondServiceImpl implements CategorySecondService {

    @Resource
    private CategorySecondDao categorySecondDao;

    @Override
    public int findCount() {
        return categorySecondDao.findCount();
    }

    @Override
    public List<Categorysecond> findByPage(Integer begin, Integer limit) {
        return categorySecondDao.findByPage(begin,limit);
    }
    
    @Override
    public List<Categorysecond> findByCid(Integer cid){
        return categorySecondDao.findByCid(cid);
    }
    
    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(Categorysecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    @Override
    public Categorysecond findByCsid(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void delete(Categorysecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void update(Categorysecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    @Override
    public List<Categorysecond> findAll() {
        return categorySecondDao.findAll();
    }
}
