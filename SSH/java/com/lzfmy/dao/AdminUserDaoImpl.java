package com.lzfmy.dao;

import com.lzfmy.model.Adminuser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("adminUserDao")
public class AdminUserDaoImpl implements AdminUserDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Adminuser login(Adminuser adminUser) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Adminuser where username = ?0 and password = ?1";
        Query query = session.createQuery(hql);
        query.setString(0,adminUser.getUsername());
        query.setString(1,adminUser.getPassword());
        List<Adminuser> adminUsers = query.list();
        if (adminUsers!=null && adminUsers.size()>0) {
            return adminUsers.get(0);
        }else{
            return null;
        }
    }
}
