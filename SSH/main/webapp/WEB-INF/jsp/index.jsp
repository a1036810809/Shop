<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>烟大商城</title>
    <link href="./css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container index">
    <div class="span24">
        <div id="hotProduct" class="hotProduct clearfix">
            <div class="title">
                <strong>热门商品</strong>
            </div>

            <ul class="tab">
            </ul>

            <ul class="tabContent" style="display: block;">
                <s:iterator var="p" value="hotP">
                    <li>
                        <a href="product_findByPid.action?pid=<s:property value="#p.pid" />" target="_blank"><img
                                src="<s:property value="#p.image" />" style="display: block;"></a>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="span24">
        <div id="newProduct" class="newProduct clearfix">
            <div class="title">
                <strong>最新商品</strong>
            </div>

            <ul class="tab">
            </ul>

            <ul class="tabContent" style="display: block;">
                <s:iterator var="n" value="newP">
                    <li>
                        <a href="product_findByPid.action?pid=<s:property value="#n.pid" />" target="_blank"><img
                                src="<s:property value="#n.image" />" style="display: block;"></a>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="span24">
        <div class="friendLink">
            <dl>
                <dt>新手指南</dt>
                <dd>
                    <a target="_blank">支付方式</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">配送方式</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">售后服务</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">购物帮助</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">蔬菜卡</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">礼品卡</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">银联卡</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">亿家卡</a>
                    |
                </dd>

                <dd class="more">
                    <a>更多</a>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
        </div>
    </div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a>关于我们</a>
                |
            </li>
            <li>
                <a>联系我们</a>
                |
            </li>
            <li>
                <a>招贤纳士</a>
                |
            </li>
            <li>
                <a>法律声明</a>
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
                <a>服务声明</a>
                |
            </li>
            <li>
                <a>广告声明</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2018-2099 网上商城 版权所有</div>
    </div>
</div>
</body>
</html>
