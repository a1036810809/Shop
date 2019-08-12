package com.lzfmy.controller;

import com.lzfmy.model.Category;
import com.lzfmy.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台一级分类管理的Action
 */
@Controller
@RequestMapping("/adminCategory")
public class AdminCategoryController{
    //模型驱动使用的类
    private Category category = new Category();
    @Resource
    private CategoryService categoryService;
    
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        //查询所有一级分类
        List<Category> cList = categoryService.findAllCategory();
        //将集合数据显示到页面上--值栈
        model.addAttribute("cList",cList);
        return "/admin/category/list";
    }

    //后台保存一级分类
    @RequestMapping("/save")
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/adminCategory/findAll";
    }

    //后台删除一级分类的方法
    @RequestMapping("/delete")
    public String delete(Integer cid) {
        //接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类，必须现根据id查询，在进行删除
        category = categoryService.findByCid(cid);
        //删除
        categoryService.delete(category);
        return "redirect:/adminCategory/findAll";
    }

    //后台编辑一级分类的方法
    @RequestMapping("/edit")
    public String edit(Integer cid,Model model) {
        //根据一级分类的管理查询一级分类
        category = categoryService.findByCid(cid);
        model.addAttribute("model",category);
        return "/admin/category/edit";
    }

    //后台修改一级分类的方法
    @RequestMapping("/update")
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:/adminCategory/findAll";
    }

}
