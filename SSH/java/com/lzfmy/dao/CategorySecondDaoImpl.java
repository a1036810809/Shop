package com.lzfmy.dao;

import com.lzfmy.model.Categorysecond;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Repository("categorySecondDao")
public class CategorySecondDaoImpl implements CategorySecondDao{

    @Resource
    private SessionFactory sessionFactory;

    //统计二级分类个数的方法
    @Override
    public int findCount() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select count(*) from Categorysecond";
        Query query = session.createQuery(hql);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }else {
            return 0;
        }
    }

    public List<Categorysecond> findByCid(Integer cid){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Categorysecond where cid=?0";
        Query query = session.createQuery(hql);
        query.setString(0,""+cid);
        List<Categorysecond> categoryseconds = query.list();
        return categoryseconds;
    }

    //分页查询二级分类的方法
    @Override
    public List<Categorysecond> findByPage(Integer begin, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Categorysecond order by csid desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<Categorysecond> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }else {
            return null;
        }
    }

    //二级分类的保存
    @Override
    public void save(Categorysecond categorysecond) {
        sessionFactory.getCurrentSession().save(categorysecond);
    }

    //根据二级分类id查询二级分类
    @Override
    public Categorysecond findByCsid(Integer csid) {
        return sessionFactory.getCurrentSession().get(Categorysecond.class,csid);
    }

    //删除二级分类
    @Override
    public void delete(Categorysecond categorysecond) {
        sessionFactory.getCurrentSession().delete(categorysecond);
    }

    //修改二级分类
    @Override
    public void update(Categorysecond categorysecond) {
        sessionFactory.getCurrentSession().update(categorysecond);
    }

    //查询所有二级分类
    @Override
    public List<Categorysecond> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Categorysecond";
        Query query = session.createQuery(hql);
        return (List<Categorysecond>) query.list();
    }
}
