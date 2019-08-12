package com.lzfmy.dao;

import com.lzfmy.model.Orderitem;

import java.util.List;

public interface OrderItemDao {
    void save(Orderitem orderitem);
    void delete(Orderitem orderitem);
    List<Orderitem> findByOid(Integer oid);
}
