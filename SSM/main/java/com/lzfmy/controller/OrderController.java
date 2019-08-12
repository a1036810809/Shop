package com.lzfmy.controller;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.*;
import com.lzfmy.service.OrderItemService;
import com.lzfmy.service.OrderService;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController{
    private Order order = new Order();
    private String basePath = "/WEB-INF/jsp/";
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private ProductService productService;
    
    //生成订单
    @RequestMapping("/save")
    public String save(HttpSession session, Model model) {
        //订单数据的补全
            order.setOrdertime(new Timestamp(new Date().getTime()));
        //1:未付款 2:已付款未发货 3:已经发货，但未收货 4:交易完成
            order.setState(1);
        //总计数据是购物车中的信息
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                model.addAttribute("time",3);
                model.addAttribute("url","index/index");
                model.addAttribute("msg","亲！您还没有购物，请先去购物");
                return basePath+"msg";
            }
            order.setTotal(cart.getTotal());
            //订单所属用户
            User existUser = (User) session.getAttribute("existUser");
            if (existUser == null) {
                model.addAttribute("time",3);
                model.addAttribute("url","user/loginPage");
                model.addAttribute("msg","亲，您还未登录，请先登录！");
                return basePath+"msg";
            }
            order.setName(existUser.getName());
            order.setPhone(existUser.getPhone());
            order.setAddr(existUser.getAddr());
            order.setUid(existUser.getUid());
            order.setUser(existUser);
            orderService.save(order);
            order = orderService.findLastByUid(existUser.getUid());
            order.setUser(existUser);
            //设置订单中的订单项
            for(CartItem cartItem : cart.getCartItems()){
                Orderitem orderitem = new Orderitem();
                orderitem.setCount(cartItem.getCount());
                orderitem.setSubtotal(cartItem.getSubtotal());
                orderitem.setProduct(cartItem.getProduct());
                orderitem.setOid(order.getOid());
                orderitem.setPid(cartItem.getProduct().getPid());
                
                orderitem.setOrder(order);
                order.getOrderitems().add(orderitem);
                orderItemService.save(orderitem);
            }
            //将订单的对象显示到页面上
            //通过值栈的方式显示：因为Order显示的对象就是模型驱动使用的对象
            //所以该对象就在栈顶
            //提交完订单后  需要清空购物车
            cart.clearCart();
            model.addAttribute("model",order);
            return basePath+"order";
    }

    //我的订单查询
    @RequestMapping("/findByUid")
    public String findByUid(Integer page,HttpSession session,Model model){
        PageBean<Order> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 2;
        pageBean.setLimit(limit);
        //设置总记录
        User user = (User) session.getAttribute("existUser");
        int totalCount = 0;
        //根据用户的id进行查找
        totalCount = orderService.findByUid(user.getUid());
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Order> orders =  orderService.findByPageUid(user.getUid(),begin,limit);
        pageBean.setList(orders);
        //将分页数据显示到页面上--值栈
        System.out.println(orders.get(0).getOrderitems().get(0).getProduct().getShopPrice());
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("page",page);
        return basePath+"orderList";
    }

    //根据订单id查询订单的方法
    @RequestMapping("/findByOid")
    public String findByOid(Integer oid,HttpSession session,Model model) {
        order = orderService.findByOid(oid);
        order.setUser((User) session.getAttribute("existUser"));
        model.addAttribute("model",order);
        return basePath+"order";
    }

    //订单付款
    @RequestMapping("/payOrder")
    public String payOrder(HttpSession session,Order order) throws IOException {
        // 1.修改数据:
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setAddr(order.getAddr());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());
        // 修改订单
        orderService.update(currOrder);
        session.setAttribute("payOrder",currOrder);
        return "redirect:/order/callBack";
    }

    // 付款成功后跳转回来的路径:
    @RequestMapping("/callBack")
    public String callBack(HttpSession session,Model model){
        // 修改订单的状态:
        Order currOrder =(Order) session.getAttribute("payOrder");
        if (currOrder==null){
            model.addAttribute("time",3);
            model.addAttribute("url","index/index");
            model.addAttribute("msg","订单错误！");
            return basePath+"msg";
        }
        // 修改订单状态为2:已经付款:
        currOrder.setState(2);
        orderService.update(currOrder);
        model.addAttribute("time",3);
        model.addAttribute("url","index/index");
        model.addAttribute("msg","支付成功!订单编号为: "+currOrder.getOid() +" 付款金额为: "+currOrder.getTotal());
        session.removeAttribute("payOrder");
        return basePath+"msg";
    }

    //确认收货 修改订单状态
    @RequestMapping("updateState")
    public String updateState(Integer oid) {
        //根据订单id查询我的订单
        Order currOrder = orderService.findByOid(oid);
        currOrder.setState(4);
        orderService.update(currOrder);
        return "redirect:/order/findByUid?page=1";
    }
}
