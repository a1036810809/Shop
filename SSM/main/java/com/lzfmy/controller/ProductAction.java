package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.Product;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductAction {
    private Product product;
    @Resource
    private ProductService productService;
    private String basePath = "/WEB-INF/jsp/";

    //根据商品id进行查询商品
    @RequestMapping("/findByPid")
    public String findByPid(Integer pid, Model model) {
        //由于模型驱动 该product直接在栈顶
        product = productService.findByPid(pid);
        model.addAttribute("model",product);
        return basePath+"product";
    }
    
    @RequestMapping("/findByCid")
    public String findByCid(Integer page,Integer cid,Model model) {
        PageBean<Product> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 12;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount = productService.findCountCid(cid);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> products = productService.findByPageCid(cid,begin,limit);
        pageBean.setList(products);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        model.addAttribute("cid",cid);
        return basePath+"productListCid";
    }
    
    @RequestMapping("/findByCsid")
    public String findByCsid(Integer page,Integer csid,Model model) {
        PageBean<Product> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 8;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount = productService.findCountCsid(csid);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> products = productService.findByPageCsid(csid,begin,limit);
        pageBean.setList(products);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        model.addAttribute("csid",csid);
        return basePath+"productListCsid";
    }


}
