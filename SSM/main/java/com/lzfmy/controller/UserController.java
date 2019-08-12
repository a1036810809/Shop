package com.lzfmy.controller;

import com.lzfmy.model.User;
import com.lzfmy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController{
    @Resource
    private UserService userService;
    private String basePath = "/WEB-INF/jsp/";

    //跳转到注册界面
    @RequestMapping("/registPage")
    public String registPage() {
        return basePath+"regist";
    }

    //ajax进行异步校验用户名执行的方法
    @RequestMapping("/findByName")
    public void findByName(HttpServletResponse response,String username) throws IOException {
        User exitUser = userService.findByName(username);
        //获得response对象，向页面输出
        response.setContentType("text/html;charset=UTF-8");
        if (exitUser != null) {
            response.getWriter().println("<font color='red'>用户名已存在</font>");
        } else {
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }
        return ;
    }

    //用户注册
    @RequestMapping("/regist")
    public String regist(HttpSession session, Model model,String checkcode,User user) {
        String code = (String) session.getAttribute("checkcode");
        if (!code.equalsIgnoreCase(checkcode)) {
            model.addAttribute("time",3);
            model.addAttribute("url","user/registPage");
            model.addAttribute("msg","验证码错误，请重新注册！");
            return basePath+"msg";
        } else {
            userService.save(user);
            model.addAttribute("time",2);
            model.addAttribute("url","user/registPage");
            model.addAttribute("msg","注册成功，请去邮箱激活！");
            return basePath+"msg";
        }
    }

    //用户激活
    @RequestMapping("/active")
    public String active(String code,Model model) {
        //根据激活码查找用户
        User existUser = userService.findByCode(code);
        if (existUser == null) {
            model.addAttribute("time",3);
            model.addAttribute("url","user/registPage");
            model.addAttribute("msg","未知错误！");
        }else {
            existUser.setState(1);
            existUser.setCode(null);
            userService.update(existUser);
            model.addAttribute("time",2);
            model.addAttribute("url","user/loginPage");
            model.addAttribute("msg","激活成功请去登录！");
        }
        return basePath+"msg";
    }

    //跳转到登录界面
    @RequestMapping("/loginPage")
    public String loginPage() {
        return basePath+"login";
    }

    //登录
    @RequestMapping("/login")
    public String login(String checkcode, Model model,HttpSession session,User user) {
        String code = (String) session.getAttribute("checkcode");
        if (!code.equalsIgnoreCase(checkcode)){
            model.addAttribute("time",3);
            model.addAttribute("url","user/loginPage");
            model.addAttribute("msg","验证码错误，请重新登录！");
            return basePath+"msg";
        } else {
            User existUser = userService.login(user);
            if (existUser == null) {
                model.addAttribute("time",3);
                model.addAttribute("url","user/loginPage");
                model.addAttribute("msg","登录失败，用户名或密码不正确！");
                return basePath+"msg";
            } else {
                //登陆成功
                //将用户信息存到session中
                session.setAttribute("existUser",existUser);
                return "redirect:/index/index";
            }
        }
    }

    //用户的退出 session的销毁
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("existUser");
        return basePath+"login";
    }
    
    @RequestMapping("findPasswordPage")
    public String findPasswordPage(){
        return basePath+"repassword";
    }
    
    @RequestMapping("findPassword")
    public String findPassword(User user,Model model){
        User exUser = userService.findByName(user.getUsername());
        if (exUser==null){
            model.addAttribute("time",3);
            model.addAttribute("url","user/findPasswordPage");
            model.addAttribute("msg","用户名不存在！");
            return basePath+"msg";
        }
        if (!exUser.getPhone().equals(user.getPhone()) || !exUser.getEmail().equals(user.getEmail())){
            model.addAttribute("time",3);
            model.addAttribute("url","user/findPassword");
            model.addAttribute("msg","邮箱或电话输入错误!");
            return basePath+"msg";
        }
        exUser.setPassword(user.getPassword());
        userService.update(exUser);
        model.addAttribute("time",2);
        model.addAttribute("url","user/loginPage");
        model.addAttribute("msg","修改成功！请去登录！");
        return basePath+"msg";
    }
}
