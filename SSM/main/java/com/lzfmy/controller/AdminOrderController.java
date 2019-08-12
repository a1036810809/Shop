package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.Order;
import com.lzfmy.model.Orderitem;
import com.lzfmy.service.OrderItemService;
import com.lzfmy.service.OrderService;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController{
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private ProductService productService;
    
    @RequestMapping("/findAllByPage")
    public String findAllByPage(Integer page, Model model) {
        PageBean<Order> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 10;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount = orderService.findByCount();
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Order> orders =  orderService.findByPage(begin,limit);
        pageBean.setList(orders);
        //将分页数据显示到页面上--值栈
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        return "/admin/order/list";
    }

    //根据订单id查询订单项
    @RequestMapping("/findOrderItem")
    public String findOrderItem(Integer oid,Model model) {
        //根据订单id查询订单项
        List<Orderitem> list = orderItemService.findByOid(oid);
        //通过值栈显示到页面
        model.addAttribute("list",list);
        return "/admin/order/orderItem";
    }

    //修改订单状态的方法
    @RequestMapping("/updateState")
    public String updateState(Integer oid) {
        //根据订单id查询订单
        Order currOrder = orderService.findByOid(oid);
        //修改订单状态
        currOrder.setState(3);
        orderService.update(currOrder);
        return "redirect:/adminOrder/findAllByPage?page=1";
    }
    
    //根据状态查询订单
    @RequestMapping("findByState")
    public String findByState(Integer page,Integer state,Model model) {
        PageBean<Order> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 10;
        pageBean.setLimit(limit);
        //设置总记录
        int totalCount = 0;
        totalCount = orderService.findByCount();
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Order> orders =  orderService.findByState(state,begin,limit);
        pageBean.setList(orders);
        //将分页数据显示到页面上--值栈
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        return "/admin/order/list";
    }
}
