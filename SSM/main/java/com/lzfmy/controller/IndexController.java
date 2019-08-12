package com.lzfmy.controller;

import com.lzfmy.model.Category;
import com.lzfmy.model.Product;
import com.lzfmy.service.CategoryService;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 首页访问Action
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Resource
    private CategoryService categoryService;
    
    @Resource
    private ProductService productService;
    
    @RequestMapping("/index")
    public String execute(HttpServletRequest request, Model model){
        //一级目录查询
        List<Category> categories = categoryService.findAllCategory();
        //一级目录存入Session
        request.getSession().setAttribute("categories",categories);
        //热门商品
        List<Product> hotP = productService.findAllHot();
        //保存到值栈
        model.addAttribute("hotP",hotP);
        //最新商品
        List<Product> newP = productService.findAllNew();
        //保存到值栈
        model.addAttribute("newP",newP);
        return "/WEB-INF/jsp/index";
    }
}
