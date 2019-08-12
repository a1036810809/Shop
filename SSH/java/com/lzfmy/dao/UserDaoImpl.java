package com.lzfmy.dao;

import com.lzfmy.Utils.UUIDUtils;
import com.lzfmy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
    @Resource
    private SessionFactory sessionFactory;
    
    
    //根据用户名查找用户
    @Override
    public User findByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User where username=?0";
        Query query = session.createQuery(hql);
        query.setString(0,username);
        List<User> users = query.list();
        if (users != null &&  users.size()>0) {
            return users.get(0);
        } else
            return null;
    }

    @Override
    public User findByCode(String code) {
        String hql = "from User where code = ?0";    
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,code);
        List<User> user = query.list();
        if (user != null && user.size() > 0){
            return user.get(0);
        } else
            return null;
    }

    //用户注册
    @Override
    public void save(User user) {
        user.setState(1);//0代表用户未激活   1代表用户已经激活
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setCode(code);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User login(User user) {
        String hql = "from User where username = ?0 and password = ?1 and state = ?2";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,user.getUsername());
        query.setString(1,user.getPassword());
        query.setString(2,"1");
        List<User> users = query.list();
        if (users != null && users.size() > 0) {
            return users.get(0);
        }else
            return null;
    }

    //查找用户数量
    @Override
    public int findCount() {
        String hql = "select count(*) from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //对用户进行分页查询
    @Override
    public List<User> findByPage(int begin, int limit) {
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(limit);
        List<User> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    //通过uid查询用户
    @Override
    public User findByUid(Integer uid) {
        return sessionFactory.getCurrentSession().get(User.class,uid);
    }

    //删除用户
    @Override
    public void delete(User exitUser) {
        sessionFactory.getCurrentSession().delete(exitUser);
    }
}
