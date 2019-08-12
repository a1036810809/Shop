package com.lzfmy.service.impl;

import com.lzfmy.mapper.UserMapper;
import com.lzfmy.model.User;
import com.lzfmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //根据用户名查找用户
    
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    
    public User findByCode(String code) {
        return userMapper.findByCode(code);
    }

    //用户注册
    
    public void save(User user) {
        userMapper.insertSelective(user);
    }

    
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    
    public User login(User user) {
        return userMapper.login(user);
    }

    
    public int findCount() {
        return userMapper.findCount();
    }

    
    public List<User> findByPage(int begin, int limit) {
        return userMapper.findByPage(begin, limit);
    }

    
    public User findByUid(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    
    public void delete(User exitUser) {
        userMapper.deleteByPrimaryKey(exitUser.getUid());
    }
}
