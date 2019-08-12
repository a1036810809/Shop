package com.lzfmy.action;

import com.lzfmy.model.Category;
import com.lzfmy.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台一级分类管理的Action
 */
@Repository("adminCategoryAction")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
    //模型驱动使用的类
    private Category category = new Category();
    @Override
    public Category getModel() {
        return category;
    }
    @Resource
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //后台执行查询所有一级分类的方法
    public String findAll() {
        //查询所有一级分类
        List<Category > cList = categoryService.findAllCategory();
        //将集合数据显示到页面上--值栈
        ActionContext.getContext().getValueStack().set("cList",cList);
        return "findAll";
    }

    //后台保存一级分类
    public String save() {
        categoryService.save(category);
        return "saveSuccess";
    }

    //后台删除一级分类的方法
    public String delete() {
        //接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类，必须现根据id查询，在进行删除
        category = categoryService.findByCid(category.getCid());
        //删除
        categoryService.delete(category);
        return "deleteSuccess";
    }

    //后台编辑一级分类的方法
    public String edit() {
        //根据一级分类的管理查询一级分类
        category = categoryService.findByCid(this.category.getCid());
        return "editSuccess";
    }

    //后台修改一级分类的方法
    public String update() {
        categoryService.update(category);
        return "updateSuccess";
    }

}
