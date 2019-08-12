package com.lzfmy.service.impl;

import com.lzfmy.mapper.OrderitemMapper;
import com.lzfmy.model.Orderitem;
import com.lzfmy.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("oderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    
    @Autowired
    private OrderitemMapper orderitemMapper;

    @Override
    public void save(Orderitem orderitem){
        orderitemMapper.insertSelective(orderitem);
    }

    @Override
    public void delete(Orderitem orderitem){
        orderitemMapper.deleteByPrimaryKey(orderitem.getItemid());
    }

    @Override
    public List<Orderitem> findByOid(Integer oid){
        return orderitemMapper.findByOid(oid);
    }
}
