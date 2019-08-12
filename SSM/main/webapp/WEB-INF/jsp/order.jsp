<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单页面</title>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@include file="menu.jsp" %>
<div class="container cart">
    <div class="span24">
        <div class="step step1">
            <ul>

                <li class="current"></li>
                <li>生成订单成功</li>
            </ul>
        </div>
        <table>
            <tbody>
            <tr>
                订单编号：<c:out value="${model.oid}"/>
            </tr>
            <tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            <c:forEach var="o" items="${model.orderitems}">
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
            </tbody>
        </table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            商品金额: <strong id="effectivePrice">￥<c:out value="${model.total}"/>元</strong>
        </div>
        <form id="orderForm" action="<c:out value="${basePath}"/>/order/payOrder" method="post">
            <input type="hidden" name="oid" value="<c:out value="${model.oid}"/>"/>
            <div class="span24">
                <p>
                    收货地址：<input name="addr" type="text" value="<c:out value="${model.user.addr}"/>"
                                style="width:350px"/>
                    <br/>
                    收货人&nbsp;&nbsp;&nbsp;：<input name="name" type="text"
                                                 value="<c:out value="${model.user.name}"/>" style="width:150px"/>
                    <br/>
                    联系方式：<input name="phone" type="text" value="<c:out value="${model.user.phone}"/>"
                                style="width:150px"/>

                </p>
                <hr/>
                <p>
                    选择银行：<br/>
                    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
                    <img src="<c:out value="${basePath}"/>/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
                    <img src="<c:out value="${basePath}"/>/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
                    <img src="<c:out value="${basePath}"/>/bank_img/abc.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
                    <img src="<c:out value="${basePath}"/>/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
                    <img src="<c:out value="${basePath}"/>/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
                    <img src="<c:out value="${basePath}"/>/bank_img/ccb.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
                    <img src="<c:out value="${basePath}"/>/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
                    <img src="<c:out value="${basePath}"/>/bank_img/cmb.bmp" align="middle"/>
                </p>
                <hr/>
                <p style="text-align:right">
                    <a href="javascript:document.getElementById('orderForm').submit();">
                        <img src="<c:out value="${basePath}"/>/images/finalbutton.gif" width="204" height="51" border="0"/>
                    </a>
                </p>
            </div>
        </form>
    </div>

</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="<c:out value="${basePath}"/>/image/r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
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