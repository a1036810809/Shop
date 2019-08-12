package com.lzfmy.action;

import com.lzfmy.Utils.PageBean;
import com.lzfmy.model.*;
import com.lzfmy.service.OrderItemService;
import com.lzfmy.service.OrderService;
import com.lzfmy.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("orderAction")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
    private Order order = new Order();
    private int page;
    @Resource
    private OrderService orderService;
    
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private ProductService productService;

    public Order getOrder() {
        return order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public Order getModel() {
        return order;
    }

    //生成订单
    public String save() {
        //订单数据的补全
            order.setOrdertime(new Timestamp(new Date().getTime()));
        //1:未付款 2:已付款未发货 3:已经发货，但未收货 4:交易完成
            order.setState(1);
        //总计数据是购物车中的信息
            Cart cart = (Cart) ServletActionContext.getRequest().getSession()
                    .getAttribute("cart");
            if (cart == null) {
                this.addActionError("亲！您还没有购物，请先去购物");
                return "msg";
            }
            order.setTotal(cart.getTotal());
            //订单所属用户
            User existUser = (User) ServletActionContext.getRequest().getSession()
                    .getAttribute("existUser");
            if (existUser == null) {
                this.addActionError("亲，您还未登录，请先登录！");
                return "login";
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
            return "saveSuccess";
    }

    //我的订单查询
    public String findByUid(){
        PageBean<Order> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setPage(page);
        //设置每页的记录
        int limit = 2;
        pageBean.setLimit(limit);
        //设置总记录
        User user = (User) ServletActionContext.getRequest().getSession()
                .getAttribute("existUser");
        int totalCount = 0;
        //根据用户的id进行查找
        totalCount = orderService.findByUid(user.getUid());
        //System.out.println(totalCount);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage = 0;
        totalPage = (totalCount % limit == 0) ? totalCount/limit : totalCount/limit + 1;
        pageBean.setTotalPage(totalPage);
        //每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Order> orders =  orderService.findByPageUid(user.getUid(),begin,limit);
        for (int i = 0;i<orders.size();i++){
            List<Orderitem> os = orderItemService.findByOid(orders.get(i).getOid());
            for (int j = 0;j<os.size();j++){
                os.get(j).setProduct(productService.findByPid(os.get(j).getPid()));
            }
            orders.get(i).setOrderitems(os);
            orders.get(i).setUser(user);
        }
        pageBean.setList(orders);
        //将分页数据显示到页面上--值栈
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByUid";
    }

    //根据订单id查询订单的方法
    public String findByOid() {
        order = orderService.findByOid(order.getOid());
        List<Orderitem> os = orderItemService.findByOid(order.getOid());
        for (int j = 0;j<os.size();j++){
            os.get(j).setProduct(productService.findByPid(os.get(j).getPid()));
        }
        order.setOrderitems(os);
        order.setUser((User) ServletActionContext.getRequest().getSession()
                .getAttribute("existUser"));
        return "findByOid";
    }

    //订单付款
    public String payOrder() throws IOException {
        // 1.修改数据:
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setAddr(order.getAddr());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());
        // 修改订单
        orderService.update(currOrder);
        ServletActionContext.getRequest().getSession().setAttribute("payOrder",currOrder);
        return "PaySuccess";
    }

    // 付款成功后跳转回来的路径:
    public String callBack(){
        // 修改订单的状态:
        Order currOrder =(Order) ServletActionContext.getRequest().getSession().getAttribute("payOrder");
        if (currOrder==null){
            this.addActionMessage("订单错误！");
            return "msg";
        }
        // 修改订单状态为2:已经付款:
        currOrder.setState(2);
        orderService.update(currOrder);
        this.addActionMessage("支付成功!订单编号为: "+currOrder.getOid() +" 付款金额为: "+currOrder.getTotal());
        ServletActionContext.getRequest().getSession().removeAttribute("payOrder");
        return "msg";
    }

    //确认收货 修改订单状态
    public String updateState() {
        //根据订单id查询我的订单
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setState(4);
        orderService.update(currOrder);
        return "updateStateSuccess";
    }
}
