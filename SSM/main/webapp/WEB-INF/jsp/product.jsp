<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>烟大商城</title>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css">
    <link href="<c:out value="${basePath}"/>/css/product.css" rel="stylesheet" type="text/css">
    <script type="">
        function saveCart() {
            document.getElementById("add").submit();
        }
    </script>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container productContent">
    <div class="span6">
        <div class="hotProductCategory">
            <c:forEach var="c" items="${sessionScope.categories}">
                    <dt>
                        <a href="<c:out value="${basePath}"/>/product/findByCid?cid=<c:out value="${c.cid}"/>&page=1">
                            <c:out value="${c.cname}"/></a>
                    </dt>
                    <c:forEach var="cs" items="${c.categoryseconds}">
                        <dd>
                            <a href="<c:out value="${basePath}"/>/product/findByCsid?csid=<c:out value="${cs.csid}" />&page=1">
                                <c:out value="${cs.csname}"/></a>
                        </dd>
                    </c:forEach>
                </dl>
            </c:forEach>
        </div>
    </div>
    <div class="span18 last">

        <div class="productImage">
            <a title="" style="outline-style: none; text-decoration: none;" id="zoom"
               href="<c:out value="${basePath}"/>/image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg"
               rel="gallery">
                <div class="zoomPad"><img style="opacity: 1;" title="" class="medium"
                                          src="<c:out value="${basePath}"/>/<c:out value="${model.image}" />">
                    <div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
                         class="zoomPup"></div>
                    <div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
                         class="zoomWindow">
                        <div style="width: 368px;" class="zoomWrapper">
                            <div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div>
                            <div style="width: 0%; height: 0px;" class="zoomWrapperImage">
                                <img src=""/>
                            </div>
                        </div>
                    </div>
                    <div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">
                        Loading zoom
                    </div>
                </div>
            </a>
        </div>

        <div class="name"><c:out value="${model.pname}"/></div>
        <div class="sn">
            <div>编号：<c:out value="${model.pid}"/></div>
        </div>
        <div class="info">
            <dl>
                <dt>商城价:</dt>
                <dd>
                    <strong>￥：<c:out value="${model.shopPrice}"/>元</strong>
                    参 考 价：
                    <del>￥：<c:out value="${model.marketPrice}"/>元</del>
                </dd>
            </dl>
            <dl>
                <dt>促销:</dt>
                <dd>
                    <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
                </dd>
            </dl>
            <dl>
                <dt></dt>
                <dd>
                    <span>    </span>
                </dd>
            </dl>
        </div>
        <form id="add" action="<c:out value="${basePath}"/>/cart/addCart?pid=<c:out value="${model.pid}" />" method="post">
            <div class="action">
                <dl class="quantity">
                    <dt>购买数量:</dt>
                    <dd>
                        <input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text">
                    </dd>
                    <dd>
                        件
                    </dd>
                </dl>
                <div class="buy">
                    <input id="addCart" class="addCart" value="加入购物车" type="button" onclick="saveCart()">
                </div>
            </div>
        </form>
        <div id="bar" class="bar">
            <ul>
                <li id="introductionTab">
                    <a href="#introduction">商品介绍</a>
                </li>
            </ul>
        </div>

        <div id="introduction" name="introduction" class="introduction">
            <div class="title">
                <strong><c:out value="${model.pdesc}"/></strong>
            </div>
            <div>
                <img src="<c:out value="${basePath}"/>/<c:out value="${model.image}" />">
            </div>
        </div>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="<c:out value="${basePath}"/>/image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
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
