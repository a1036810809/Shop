package com.lzfmy.action;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.Product;
import com.lzfmy.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository("productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    private Product product = new Product();
    @Resource
    private ProductService productService;
    private int cid;
    private int page;
    @Override
    public Product getModel() {
        return product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page)  {
        this.page = page;
    }

    //根据商品id进行查询商品
    public String findByPid() {
        //由于模型驱动 该product直接在栈顶
        product = productService.findByPid(product.getPid());
        return "findByPid";
    }

    public String findByCid() {
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
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByCid";
    }

    public String findByCsid() {
        PageBean<Product> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //System.out.println(page);
        //设置每页的记录
        int limit = 8;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount = productService.findCountCsid(product.getCsid());
        //System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> products = productService.findByPageCsid(product.getCsid(),begin,limit);
        pageBean.setList(products);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByCsid";
    }


}
