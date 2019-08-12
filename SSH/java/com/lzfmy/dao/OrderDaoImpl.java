package com.lzfmy.dao;

import com.lzfmy.model.Order;
import com.lzfmy.model.Orderitem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
    
    public Order findLastByUid(int uid){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Order o where uid = ?0 order by ordertime desc";
        Query query = session.createQuery(hql);
        query.setString(0,""+uid);
        List<Order> order = query.list();
        if (order != null && order.size() > 0){
            return order.get(0);
        }
        return null;
    }
    
    //订单个数统计
    @Override
    public int findByUid(int uid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Order o,User u where o.uid=u.uid and u.uid = ?0 ";
        Query query = session.createQuery(hql);
        query.setString(0,""+uid);
        List<Long> longs = query.list();
        if (longs != null && longs.size() > 0){
            return longs.get(0).intValue();
        }
        return 0;
    }

    //我的订单的查询
    @Override
    public List<Order> findByPageUid(int uid, int begin, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Order o where o.uid = ?0 order by ordertime desc";
        Query query = session.createQuery(hql);
        query.setString(0,""+uid);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Order> orders = query.list();
        if (orders != null && orders.size() > 0){
            return orders;
        }
        return null;
    }

    @Override
    public List<Order> findByState(int state,int begin,int limit){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Order o where o.state=?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+state);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Order> orders = query.list();
        if (orders != null && orders.size() > 0){
            return orders;
        }
        return null;
        
    }

    @Override
    public Order findByOid(int oid) {
        return (Order) sessionFactory.getCurrentSession().get(Order.class,oid);
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    //统计订单个数
    @Override
    public int findByCount() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Order";
        Query query = session.createQuery(hql);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //带分页查询
    @Override
    public List<Order> findByPage(int begin, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Order order by ordertime desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Order> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
    

    //根据订单id查询订单项
    @Override
    public List<Orderitem> findOrderItem(Integer oid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select oi from Orderitem oi,Order o where oi.oid=o.oid and o.oid = ?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+oid);
        List<Orderitem> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
}
