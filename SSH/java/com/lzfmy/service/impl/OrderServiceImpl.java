package com.lzfmy.service.impl;

import com.lzfmy.dao.OrderDao;
import com.lzfmy.model.Order;
import com.lzfmy.model.Orderitem;
import com.lzfmy.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Order findLastByUid(int uid){
        return orderDao.findLastByUid(uid);
    }
    
    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findByUid(int uid) {
        return orderDao.findByUid(uid);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Order> findByPageUid(int uid, int begin, int limit) {
        return orderDao.findByPageUid(uid, begin, limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Order> findByState(int state, int begin, int limit) {
        return orderDao.findByState(state, begin, limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Order findByOid(int oid) {
        return orderDao.findByOid(oid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findByCount() {
        return orderDao.findByCount();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Order> findByPage(int begin, int limit) {
        return orderDao.findByPage(begin, limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Orderitem> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }
}
