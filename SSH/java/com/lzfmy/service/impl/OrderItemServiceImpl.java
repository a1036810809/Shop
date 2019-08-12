package com.lzfmy.service.impl;

import com.lzfmy.dao.OrderItemDao;
import com.lzfmy.model.Orderitem;
import com.lzfmy.service.OrderItemService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("oderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    
    @Resource
    private OrderItemDao orderItemDao;

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(Orderitem orderitem){
        orderItemDao.save(orderitem);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void delete(Orderitem orderitem){
        orderItemDao.delete(orderitem);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<Orderitem> findByOid(Integer oid){
        return orderItemDao.findByOid(oid);
    }
}
