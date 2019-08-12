package com.lzfmy.service.impl;

import com.lzfmy.mapper.AdminuserMapper;
import com.lzfmy.model.Adminuser;
import com.lzfmy.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminuserMapper adminuserMapper;

    @Override
    public Adminuser login(Adminuser adminUser) {
        return adminuserMapper.login(adminUser);
    }
}
