package com.lzfmy.service;

import com.lzfmy.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllHot();
    public List<Product> findAllNew();
    public Product findByPid(int pid);
    public int findCountCid(int cid);
    public int findCountCsid(int csid);
    public List<Product> findByPageCid(int cid, int begin, int limit);
    public List<Product> findByPageCsid(int csid, int begin, int limit);
    public int findCount();
    public List<Product> findByPage(int begin, int limit);
    public void save(Product product);
    public void delete(Product product);
    public void update(Product product);
}
