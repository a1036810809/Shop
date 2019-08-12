package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.Category;
import com.lzfmy.model.Categorysecond;
import com.lzfmy.service.CategorySecondService;
import com.lzfmy.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/adminCategorySecond")
public class AdminCategorySecondController {
    //模型驱动使用的类
    private Categorysecond categorySecond = new Categorysecond();
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategorySecondService categorySecondService;
    
    @RequestMapping("/findAllByPage")
    public String findAllByPage(Integer page, Model model) {
        PageBean<Categorysecond> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //System.out.println(page);
        //设置每页的记录
        int limit = 12;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount =categorySecondService.findCount();
        //System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Categorysecond> categorySeconds = categorySecondService.findByPage(begin,limit);
        pageBean.setList(categorySeconds);
        model.addAttribute("page",page);
        model.addAttribute("pageBean",pageBean);
        return "/admin/categorySecond/list";
    }

    //跳转到添加页面
    @RequestMapping("/addPage")
    public String addPage(Model model) {
        //查询所有一级分类
        List<Category> cList = categoryService.findAllCategory();
        //把数据显示到页面下拉列表--值栈
        model.addAttribute("cList",cList);
        return "/admin/categorySecond/add";
    }

    //保存二级分类的方法
    @RequestMapping("/save")
    public String save(Categorysecond categorysecond) {
        categorySecondService.save(categorysecond);
        return "redirect:/adminCategorySecond/findAllByPage?page=1";
    }


    //删除二级分类的方法
    @RequestMapping("/delete")
    public String delete(Integer csid) {
        //如果级联删除先查询再删除，配置cascade
        categorySecond = categorySecondService.findByCsid(csid);
        categorySecondService.delete(categorySecond);
        return "redirect:/adminCategorySecond/findAllByPage?page=1";
    }

    //跳转到修改页面
    @RequestMapping("/edit")
    public String edit(Integer csid,Model model) {
        //根据二级分类的id查询二级分类对象
        categorySecond = categorySecondService.findByCsid(csid);
        //查询所有一级分类
        List<Category> cList = categoryService.findAllCategory();
        model.addAttribute("cList",cList);
        model.addAttribute("model",categorySecond);
        return "/admin/categorySecond/edit";
    }

    //修改二级分类
    @RequestMapping("/update")
    public String update(Categorysecond categorysecond) {
        categorySecondService.update(categorysecond);
        return "redirect:/adminCategorySecond/findAllByPage?page=1";
    }

}
