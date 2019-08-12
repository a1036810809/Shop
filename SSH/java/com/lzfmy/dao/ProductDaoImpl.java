package com.lzfmy.dao;

import com.lzfmy.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List findAllHot() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Product p where is_hot=1 order by pdate desc";
        Query query = session.createQuery(hql);
        query.setMaxResults(10);
        List<Product> products = query.list();
        return products;
    }

    @Override
    public List findAllNew() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Product p order by pdate desc";
        Query query = session.createQuery(hql);
        query.setMaxResults(10);
        List<Product> products = query.list();
        return products;
    }

    //根据商品id查询商品
    @Override
    public Product findByPid(int pid) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class,pid);
    }

    //通过一级目录id查询商品数量
    @Override
    public int findCountCid(int cid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Product p,Categorysecond cs,Category c where p.csid=cs.csid and cs.cid=c.cid and c.cid=?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+cid);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //通过二级目录id查询商品数量
    @Override
    public int findCountCsid(int csid) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Product p where p.csid = ?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+csid);
        List<Long> longs = query.list();
        if (longs != null && longs.size() > 0) {
            return longs.get(0).intValue();
        }
        return 0;
    }

    //根据一级分类id查询商品集合
    @Override
    public List findByPageCid(int cid, int begin, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select p from Product p,Categorysecond cs,Category c where p.csid=cs.csid and cs.cid=c.cid and c.cid = ?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+cid);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Product> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    //根据二级分类id查询商品集合
    @Override
    public List findByPageCsid(int csid, int begin, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select p from Product p where p.csid = ?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+csid);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Product> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    //统计商品个数
    @Override
    public int findCount() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Product";
        Query query = session.createQuery(hql);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //带有分页查询商品
    @Override
    public List<Product> findByPage(int begin, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Product order by pdate desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Product> products = query.list();
        if (products != null && products.size() > 0) {
            return products;
        }
        return null;
    }

    //保存商品
    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    //删除商品
    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    //更新商品
    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }


}
