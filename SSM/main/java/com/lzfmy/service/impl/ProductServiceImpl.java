package com.lzfmy.service.impl;

import com.lzfmy.mapper.ProductMapper;
import com.lzfmy.model.Product;
import com.lzfmy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    
    public List<Product> findAllHot() {
        return productMapper.findAllHot();
    }

    
    public List<Product> findAllNew() {
        return productMapper.findAllNew();
    }

    
    public Product findByPid(int pid) {
        return productMapper.selectByPrimaryKey(pid);
    }

    
    public int findCountCid(int cid) {
        return productMapper.findCountCid(cid);
    }

    
    public int findCountCsid(int csid) {
        return productMapper.findCountCsid(csid);
    }

    
    public List<Product> findByPageCid(int cid, int begin, int limit) {
        return productMapper.findByPageCid(cid,begin,limit);
    }

    
    public List<Product> findByPageCsid(int csid, int begin, int limit) {
        return productMapper.findByPageCsid(csid,begin,limit);
    }

    
    public int findCount() {
        return productMapper.findCount();
    }

    
    public List<Product> findByPage(int begin, int limit) {
        return productMapper.findByPage(begin,limit);
    }

    
    public void save(Product product) {
        productMapper.insertSelective(product);
    }

    
    public void delete(Product product) {
        productMapper.deleteByPrimaryKey(product.getPid());
    }

    
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }
}
