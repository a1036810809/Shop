package com.lzfmy.service;

import com.lzfmy.model.Order;
import com.lzfmy.model.Orderitem;

import java.util.List;

public interface OrderService {
    public void save(Order order);
    public int findByUid(int uid);
    public List<Order> findByPageUid(int uid, int begin, int limit);
    public Order findByOid(int oid);
    public void update(Order order);
    public int findByCount();
    public List<Order> findByPage(int begin, int limit);
    public List<Orderitem> findOrderItem(Integer oid);
    public List<Order> findByState(int state,int begin,int limit);
    public Order findLastByUid(int uid);
}
