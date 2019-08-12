package com.lzfmy.service.impl;

import com.lzfmy.dao.ProductDao;
import com.lzfmy.model.Product;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List findAllHot() {
        return productDao.findAllHot();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List findAllNew() {
        return productDao.findAllNew();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Product findByPid(int pid) {
        return productDao.findByPid(pid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findCountCid(int cid) {
        return productDao.findCountCid(cid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findCountCsid(int csid) {
        return productDao.findCountCsid(csid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List findByPageCid(int cid, int begin, int limit) {
        return productDao.findByPageCid(cid,begin,limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List findByPageCsid(int csid, int begin, int limit) {
        return productDao.findByPageCsid(csid,begin,limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findCount() {
        return productDao.findCount();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Product> findByPage(int begin, int limit) {
        return productDao.findByPage(begin,limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void update(Product product) {
        productDao.update(product);
    }
}
