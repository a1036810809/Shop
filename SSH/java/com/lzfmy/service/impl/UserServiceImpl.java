package com.lzfmy.service.impl;

import com.lzfmy.dao.UserDao;
import com.lzfmy.model.User;
import com.lzfmy.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    //根据用户名查找用户
    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    //用户注册
    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public int findCount() {
        return userDao.findCount();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public List<User> findByPage(int begin, int limit) {
        return userDao.findByPage(begin, limit);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public User findByUid(Integer uid) {
        return userDao.findByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public void delete(User exitUser) {
        userDao.delete(exitUser);
    }
}
