package com.lzfmy.service.impl;

import com.lzfmy.mapper.OrderMapper;
import com.lzfmy.model.Order;
import com.lzfmy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        orderMapper.insertSelective(order);
    }

    @Override
    public Order findLastByUid(int uid){
        return orderMapper.findLastByUid(uid).get(0);
    }
    
    @Override
    public int findByUid(int uid) {
        return orderMapper.findByUid(uid);

    }

    @Override
    public List<Order> findByPageUid(int uid, int begin, int limit) {
        return orderMapper.findByPageUid(uid, begin, limit);
    }

    @Override
    public List<Order> findByState(int state, int begin, int limit) {
        return orderMapper.findByState(state, begin, limit);
    }

    @Override
    public Order findByOid(int oid) {
        return orderMapper.selectByPrimaryKey(oid);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int findByCount() {
        return orderMapper.findByCount();
    }

    @Override
    public List<Order> findByPage(int begin, int limit) {
        return orderMapper.findByPage(begin, limit);
    }
}
