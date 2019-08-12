package com.lzfmy.dao;

import com.lzfmy.model.Orderitem;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("orderItemDao")
public class OrderItemDaoImpl implements OrderItemDao {
    @Resource
    private SessionFactory sessionFactory;
    
    public void save(Orderitem orderitem){
        sessionFactory.getCurrentSession().save(orderitem);
    }
    
    public void delete(Orderitem orderitem){
        sessionFactory.getCurrentSession().delete(orderitem);
    }
    
    public List<Orderitem> findByOid(Integer Oid){
        String hql = "FROM Orderitem where oid=?0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,""+Oid);
        List<Orderitem> orderitems = query.list();
        if (orderitems != null && orderitems.size() > 0){
            return orderitems;
        }else
            return null;
        
    }
}
