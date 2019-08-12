package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.User;
import com.lzfmy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台用户管理的Action类
 *
 */
@Controller
@RequestMapping("/userAdmin")
public class UserAdminController{
	// 模型驱动使用的类
	private User user = new User();
	
	// 注入用户的Service
	@Resource
	private UserService userService;
	
	@RequestMapping("/findAllByPage")
	public String findAllByPage(Integer page, Model model){
		PageBean<User> pageBean = new PageBean<>();
		//设置当前页
		pageBean.setPage(page);
		//System.out.println(page);
		//设置每页的记录
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录
		int totalCount = 0;
		totalCount =userService.findCount();
		//System.out.println(totalCount);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page - 1) * limit;
		List<User> users = userService.findByPage(begin,limit);
		pageBean.setList(users);
		model.addAttribute("pageBean",pageBean);
		model.addAttribute("page",page);
		return "/admin/user/list";
	}
	
	// 后台用户的删除
	@RequestMapping("/delete")
	public String delete(Integer uid){
		User existUser = userService.findByUid(uid);
		userService.delete(existUser);
		return "redirect:/userAdmin/findAllByPage?page=1";
	}
	
	// 后台用户的编辑
	@RequestMapping("/edit")
	public String edit(Integer uid,Model model){
		user = userService.findByUid(uid);
		model.addAttribute("model",user);
		return "/admin/user/edit";
	}
	
	// 后台用户的修改:
	@RequestMapping("/update")
	public String update(User user){
		userService.update(user);
		return "redirect:/userAdmin/findAllByPage?page=1";
	}
}
