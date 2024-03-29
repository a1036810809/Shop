<%@ page import="com.lzfmy.model.Order" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.lzfmy.Utils.PageBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单页面</title>
    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/product.css" rel="stylesheet" type="text/css"/>
    <link href="./css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@include file="menu.jsp" %>
<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li class="current"></li>
                <li><h2>我的订单显示</h2></li>
            </ul>
        </div>


        <table>
            <tbody>
            <s:iterator var="list" value="pageBean.list">

                <tr>
                    <th colspan="5">订单编号：<s:property value="#list.oid"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:if test="#list.state == 1">
                            <a href="order_findByOid.action?oid=<s:property value="#list.oid"/> "><font
                                    color="red">付款</font></a>
                        </s:if>
                        <s:if test="#list.state == 2">
                            已经付款
                        </s:if>
                        <s:if test="#list.state == 3">
                            <a href="order_updateState.action?oid=<s:property value="#list.oid"/>"><font color="red">确认收货</font></a>
                        </s:if>
                        <s:if test="#list.state == 4">
                            交易完成
                        </s:if>
                    </th>
                </tr>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <s:iterator var="o" value="#list.orderitems">
                    <tr>
                        <td width="60">
                            <input type="hidden" name="id" value="22"/>
                            <img src="<s:property value="#o.product.image"/>"/>
                        </td>
                        <td>
                            <a target="_blank"><s:property value="#o.product.pname"/></a>
                        </td>
                        <td align="center">
                            <s:property value="#o.product.shopPrice"/>
                        </td>
                        <td class="quantity" width="60" align="center">
                            <s:property value="#o.count"/>
                        </td>
                        <td width="140">
                            <span class="subtotal">￥<s:property value="#o.subtotal"/></span>
                        </td>
                    </tr>
                </s:iterator>
            </s:iterator>
            <tr>
                <td colspan="5">

                    <div class="pagination">
                        <span>第<s:property value="page"/>/<s:property value="pageBean.totalPage"/>页</span>
                        <s:if test="pageBean.page != 1">
                            <a href="order_findByUid.action?page=1" class="firstPage">&nbsp;</a>
                            <a href="order_findByUid.action?page=<s:property value="pageBean.page-1" />"
                               class="previousPage">&nbsp;</a>
                        </s:if>
                        <%--<span class="currentPage">1</span>--%>

                        <%
                            PageBean<Order> pageBean = (PageBean<Order>) ActionContext.getContext().getValueStack().findValue("pageBean");
                            for (int i = 1; i <= pageBean.getTotalPage(); i++) {
                        %>
                        <a href="order_findByUid.action?page=<%=i%>"><%=i%>
                        </a>
                        <%
                            }
                        %>

                        <s:if test="pageBean.page != pageBean.totalPage">
                            <a class="nextPage"
                               href="order_findByUid.action?page=<s:property value="pageBean.page + 1"/>">&nbsp;</a>
                            <a class="lastPage"
                               href="order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
                        </s:if>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="container footer">
        <div class="span24">
            <div class="footerAd">
                <img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
            </div>
        </div>
        <div class="span24">
            <ul class="bottomNav">
                <li>
                    <a href="#">关于我们</a>
                    |
                </li>
                <li>
                    <a href="#">联系我们</a>
                    |
                </li>
                <li>
                    <a href="#">诚聘英才</a>
                    |
                </li>
                <li>
                    <a href="#">法律声明</a>
                    |
                </li>
                <li>
                    <a>友情链接</a>
                    |
                </li>
                <li>
                    <a target="_blank">支付方式</a>
                    |
                </li>
                <li>
                    <a target="_blank">配送方式</a>
                    |
                </li>
                <li>
                    <a>SHOP++官网</a>
                    |
                </li>
                <li>
                    <a>SHOP++论坛</a>

                </li>
            </ul>
        </div>
        <div class="span24">
            <div class="copyright">Copyright © 2018-2099 网上商城 版权所有</div>
        </div>
    </div>
</body>
</html>