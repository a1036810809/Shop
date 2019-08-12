package com.lzfmy.service;

import com.lzfmy.model.Orderitem;

import java.util.List;

public interface OrderItemService {
    void save(Orderitem orderitem);
    void delete(Orderitem orderitem);
    List<Orderitem> findByOid(Integer oid);
}
