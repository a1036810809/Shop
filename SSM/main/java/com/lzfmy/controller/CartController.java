package com.lzfmy.controller;

import com.lzfmy.model.Cart;
import com.lzfmy.model.CartItem;
import com.lzfmy.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController{
    @Resource
    private ProductService productService;
    private String basePath = "/WEB-INF/jsp/";

    //从session中获取购物车
    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        return cart;
    }

    //将购物项添加到购物车
    @RequestMapping("/addCart")
    public String addCart(Integer count,Integer pid,HttpSession session){
        CartItem cartItem = new CartItem();
        //接受pid  count
        cartItem.setCount(count);
        cartItem.setProduct(productService.findByPid(pid));
        Cart cart = getCart(session);
        cart.addCartItem(cartItem);
        return basePath+"cart";
    }

    //清空购物车
    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session){
        Cart cart = getCart(session);
        cart.clearCart();
        return basePath+"cart";
    }

    //从购物车移除购物项
    @RequestMapping("/removeCart")
    public String removeCart(HttpSession session,Integer pid) {
        Cart cart = getCart(session);
        cart.removeCartItem(pid);
        return basePath+"cart";
    }

    //我的购物车
    @RequestMapping("/myCart")
    public String myCart() {
        return basePath+"cart";
    }
}
