package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.Utils.UUIDUtils;
import com.lzfmy.model.Categorysecond;
import com.lzfmy.model.Product;
import com.lzfmy.service.CategorySecondService;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController  {
    //模型驱动使用的对象
    @Resource
    private ProductService productService;
    @Resource
    private CategorySecondService categorySecondService;
    
    private Product product;
    
    //带分页的查询商品的执行的方法
    @RequestMapping("/findAllByPage")
    public String findAllByPage(Integer page, Model model) {
        PageBean<Product> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 10;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount =productService.findCount();
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> products = productService.findByPage(begin,limit);
        pageBean.setList(products);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        return "/admin/product/list";
    }

    //跳转到添加页面
    @RequestMapping("/addPage")
    public String addPage(Model model) {
        //查询所有的二级分类集合
        List<Categorysecond> csList = categorySecondService.findAll();
        //通过值栈保存数据
        model.addAttribute("csList",csList);
        return "/admin/product/add";
    }

    //保存商品的方法
    @RequestMapping("/save")
    public String save(Product product, HttpServletRequest request) throws IOException {
        //调用service完成保存
        product.setPdate(new Timestamp(new Date().getTime()));
        if (!product.getUpload().isEmpty()) {
            //获取文件上传的磁盘绝对路径
            String uuid = UUIDUtils.getUUID();
            String realPath = request.getSession().getServletContext().getRealPath("/products");
            String contentType = product.getUpload().getContentType();
            String suffixName=contentType.substring(contentType.indexOf("/")+1);
            String uploadFileName = uuid+"."+suffixName;
            product.getUpload().transferTo(new File(realPath,uploadFileName));
            product.setImage("products/"+uploadFileName);
        }
        productService.save(product);
        return "redirect:/adminProduct/findAllByPage?page=1";
    }

    //删除商品的方法
    @RequestMapping("/delete")
    public String delete(Integer pid,HttpServletRequest request) {
        //先查询再删除
        product = productService.findByPid(pid);
        //设置ManyToOne的One为null(非级联删除)
        product.setCategorysecond(null);
        productService.update(product);
        //删除上传的图片
        String path = product.getImage();
        if (path != null) {
            String realPath = request.getSession().getServletContext().getRealPath("/" + path);
            File file = new File(realPath);
            file.delete();
        }
        //删除商品
        productService.delete(product);
        return "redirect:/adminProduct/findAllByPage?page=1";
    }

    //编辑商品的方法
    @RequestMapping("/edit")
    public String edit(Integer pid,Model model) {
        //根据商品的id查询商品
        product = productService.findByPid(pid);
        //查询所有的二级分类
        List<Categorysecond> csList = categorySecondService.findAll();
        model.addAttribute("csList",csList);
        model.addAttribute("model",product);
        return "/admin/product/edit";
    }

    //修改商品的方法
    @RequestMapping("/update")
    public String update(Product product, HttpServletRequest request) throws IOException {
        product.setPdate(new Timestamp(new Date().getTime()));
        //文件上传
        if (!product.getUpload().isEmpty()) {
            //删除原来的图片
            String path = product.getImage();
            String realPathOrigin = request.getSession().getServletContext()
                    .getRealPath("/" + path);
            File file = new File(realPathOrigin);
            file.delete();
            //上传新的图片
            String uuid = UUIDUtils.getUUID();
            String realPath = request.getSession().getServletContext().getRealPath("/products");
            String contentType = product.getUpload().getContentType();
            String suffixName=contentType.substring(contentType.indexOf("/")+1);
            String uploadFileName = uuid+"."+suffixName;
            product.getUpload().transferTo(new File(realPath,uploadFileName));
            product.setImage("products/"+uploadFileName);
        }
        //修改商品数据到数据库
        productService.update(product);
        return "redirect:/adminProduct/findAllByPage?page=1";
    }
}
