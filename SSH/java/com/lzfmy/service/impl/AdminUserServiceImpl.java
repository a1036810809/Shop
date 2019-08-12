package com.lzfmy.service.impl;

import com.lzfmy.dao.AdminUserDao;
import com.lzfmy.model.Adminuser;
import com.lzfmy.service.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Adminuser login(Adminuser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
