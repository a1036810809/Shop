package com.lzfmy.action;

import com.lzfmy.model.Adminuser;
import com.lzfmy.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 *后台登录
 */
@Repository("adminUserAction")
public class AdminUserAction extends ActionSupport implements ModelDriven<Adminuser> {
    //模型驱动使用的对象
    private Adminuser adminUser = new Adminuser();
    @Resource
    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Override
    public Adminuser getModel() {
        return adminUser ;
    }

    //后台登录
    public String login() {
        Adminuser exitAdminUser = adminUserService.login(adminUser);
        if (exitAdminUser == null) {
            //登录失败
            this.addActionError("亲！您的用户名或者密码错误！");
            return "loginFail";
        }else {
            //登陆成功
            ServletActionContext.getRequest().getSession().setAttribute("exitAdminUser",exitAdminUser);
            return "loginSuccess";
        }
    }
    
    public String loginPage(){
        return "Login";
    }

}
