package com.lzfmy.controller;

import com.lzfmy.model.Adminuser;
import com.lzfmy.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 *后台登录
 */
@Controller
@RequestMapping("/adminUser")
public class AdminUserController{
    @Resource
    private AdminUserService adminUserService;
    
    @RequestMapping("/login")
    public String login(Adminuser adminuser, Model model, HttpSession session) {
        Adminuser exitAdminUser = adminUserService.login(adminuser);
        if (exitAdminUser == null) {
            //登录失败
            model.addAttribute("msg","亲！您的用户名或者密码错误！");
            return "/admin/index";
        }else {
            //登陆成功
            session.setAttribute("exitAdminUser",exitAdminUser);
            return "/admin/home";
        }
    }
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/admin/index";
    }

}
