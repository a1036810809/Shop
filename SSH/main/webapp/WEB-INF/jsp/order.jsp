<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单页面</title>
    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/cart.css" rel="stylesheet" type="text/css"/>

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
                订单编号：<s:property value="model.oid"/>
            </tr>
            <tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            <s:iterator var="o" value="model.orderitems">
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
            </tbody>
        </table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            商品金额: <strong id="effectivePrice">￥<s:property value="model.total"/>元</strong>
        </div>
        <form id="orderForm" action="order_payOrder.action" method="post">
            <input type="hidden" name="order.oid" value="<s:property value="model.oid"/>"/>
            <div class="span24">
                <p>
                    收货地址：<input name="order.addr" type="text" value="<s:property value="model.user.addr"/>"
                                style="width:350px"/>
                    <br/>
                    收货人&nbsp;&nbsp;&nbsp;：<input name="order.name" type="text"
                                                 value="<s:property value="model.user.name"/>" style="width:150px"/>
                    <br/>
                    联系方式：<input name="order.phone" type="text" value="<s:property value="model.user.phone"/>"
                                style="width:150px"/>

                </p>
                <hr/>
                <p>
                    选择银行：<br/>
                    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
                    <img src="./bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
                    <img src="./bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
                    <img src="./bank_img/abc.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
                    <img src="./bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
                    <img src="./bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
                    <img src="./bank_img/ccb.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
                    <img src="./bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
                    <img src="./bank_img/cmb.bmp" align="middle"/>
                </p>
                <hr/>
                <p style="text-align:right">
                    <a href="javascript:document.getElementById('orderForm').submit();">
                        <img src="./images/finalbutton.gif" width="204" height="51" border="0"/>
                    </a>
                </p>
            </div>
        </form>
    </div>

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