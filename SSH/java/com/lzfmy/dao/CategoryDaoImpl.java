package com.lzfmy.dao;

import com.lzfmy.model.Category;
import com.lzfmy.model.Categorysecond;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao{

    @Resource
    private SessionFactory sessionFactory;

    //查询所有一级分类
    @Override
    public List findAllCategory() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Category";
        Query query = session.createQuery(hql);
        List<Category> categories = query.list();
        return categories;
    }

    //保存一级分类
    @Override
    public void save(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    //根据cid查询一级分类
    @Override
    public Category findByCid(Integer cid) {
        return sessionFactory.getCurrentSession().get(Category.class,cid);
    }

    //删除一级分类
    @Override
    public void delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

    //修改一级分类
    @Override
    public void update(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }
}
