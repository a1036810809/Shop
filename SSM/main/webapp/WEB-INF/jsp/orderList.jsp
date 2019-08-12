<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单页面</title>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/product.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/cart.css" rel="stylesheet" type="text/css"/>

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
            <c:forEach var="list" items="${pageBean.list}">

                <tr>
                    <th colspan="5">订单编号：<c:out value="${list.oid}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <c:choose>
                            <c:when test="${list.state == 1}">
                                <a href="<c:out value="${basePath}"/>/order/findByOid?oid=<c:out value="${list.oid}"/>"><font
                                        color="red">付款</font></a>
                            </c:when>
                            <c:when test="${list.state == 2}">
                                已经付款
                            </c:when>
                            <c:when test="${list.state == 3}">
                                <a href="<c:out value="${basePath}"/>/order/updateState?oid=<c:out value="${list.oid}"/>"><font color="red">确认收货</font></a>
                            </c:when>
                            <c:when test="${list.state == 4}">
                                交易完成
                            </c:when>
                        </c:choose>
                    </th>
                </tr>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <c:forEach var="o" items="${list.orderitems}">
                    <tr>
                        <td width="60">
                            <input type="hidden" name="id" value="22"/>
                            <img src="<c:out value="${basePath}"/>/<c:out value="${o.product.image}"/>"/>
                        </td>
                        <td>
                            <a target="_blank"><c:out value="${o.product.pname}"/></a>
                        </td>
                        <td align="center">
                            <c:out value="${o.product.shopPrice}"/>
                        </td>
                        <td class="quantity" width="60" align="center">
                            <c:out value="${o.count}"/>
                        </td>
                        <td width="140">
                            <span class="subtotal">￥<c:out value="${o.subtotal}"/></span>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <td colspan="5">

                    <div class="pagination">
                        <span>第<c:out value="${page}"/>/<c:out value="${pageBean.totalPage}"/>页</span>
                        <c:if test="${pageBean.page != 1}">
                            <a href="<c:out value="${basePath}"/>/order/findByUid?page=1" class="firstPage">首页</a>
                            <a href="<c:out value="${basePath}"/>/order/findByUid?page=<c:out value="${pageBean.page-1}" />"
                               class="previousPage">&nbsp;</a>
                        </c:if>
                        <%--<span class="currentPage">1</span>--%>

                        <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                        <a href="order_findByUid.action?page=<c:out value="${i}"/>"><c:out value="${i}"/>
                        </a>
                        </c:forEach>
                        
                        

                        <c:if test="${pageBean.page != pageBean.totalPage}">
                            <a class="nextPage"
                               href="order_findByUid.action?page=<c:out value="${pageBean.page + 1}"/>">&nbsp;</a>
                            <a class="lastPage"
                               href="order_findByUid.action?page=<c:out value="${pageBean.totalPage}"/>">末页</a>
                        </c:if>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="container footer">
        <div class="span24">
            <div class="footerAd">
                <img src="<c:out value="${i}"/>/image/r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
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