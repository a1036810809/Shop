package com.lzfmy.action;

import com.lzfmy.model.Category;
import com.lzfmy.model.Categorysecond;
import com.lzfmy.model.Product;
import com.lzfmy.service.CategorySecondService;
import com.lzfmy.service.CategoryService;
import com.lzfmy.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * 首页访问Action
 */
@Repository("indexAction")
public class IndexAction extends ActionSupport {
    @Resource
    private CategoryService categoryService;
    
    @Resource
    private ProductService productService;
    
    @Resource
    private CategorySecondService categorySecondService;
    
    @Override
    public String execute() throws Exception {
        //一级目录查询
        List<Category> categories = categoryService.findAllCategory();
        for (int i = 0;i<categories.size();i++){
            List<Categorysecond> categoryseconds = categorySecondService.findByCid(categories.get(i).getCid());
            categories.get(i).setCategorySeconds(categoryseconds);
        }
        //一级目录存入Session
        ActionContext.getContext().getSession().put("categories",categories);
        //热门商品
        List<Product> hotP = productService.findAllHot();
        //保存到值栈
        ActionContext.getContext().getValueStack().set("hotP",hotP);
        //最新商品
        List<Product> newP = productService.findAllNew();
        //保存到值栈
        ActionContext.getContext().getValueStack().set("newP",newP);
        return "index";
    }
}
