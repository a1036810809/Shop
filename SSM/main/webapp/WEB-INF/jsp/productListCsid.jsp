<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>烟大商城</title>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@include file="menu.jsp" %>
<div class="container productList">
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

        <form id="productForm" action="<c:out value="${basePath}"/>/image/蔬菜 - Powered By Mango Team.htm" method="get">
            <div id="result" class="result table clearfix">
                <ul>
                    <c:forEach var="p" items="${pageBean.list}">
                        <li>
                            <a href="<c:out value="${basePath}"/>/product/findByPid?pid=<c:out value="${p.pid}" />">
                                <img src="<c:out value="${basePath}"/>/<c:out value="${p.image}" />" width="170" height="170"
                                     style="display: inline-block;">

                                <span style='color:green'>
                                    <c:out value="${p.pname}"/>
                                </span>

                                <span class="price">
                                    商城价： ￥<c:out value="${p.shopPrice}"/>
                                </span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="pagination">
                <span>第<c:out value="${page}"/>/<c:out value="${pageBean.totalPage}"/>页</span>
                <c:if test="${csid != null}">
                    <c:if test="${pageBean.page != 1}">
                        <a href="<c:out value="${basePath}"/>/product/findByCsid?csid=<c:out value="${csid}" />&page=1" class="firstPage">&nbsp;</a>
                        <a href="<c:out value="${basePath}"/>/product/findByCsid?csid=<c:out value="${csid}" />&page=<c:out value="${pageBean.page-1}" />"
                           class="previousPage">&nbsp;</a>
                    </c:if>
                    <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                        <a href="product_findByCid.action?cid=<c:out value="${cid}" />&page=<c:out value="${i}"/>"><c:out value="${i}"/>
                        </a>
                    </c:forEach>

                    <c:if test="${pageBean.page != pageBean.totalPage}">
                        <a class="nextPage"
                           href="<c:out value="${basePath}"/>/product/findByCsid?csid=<c:out value="${csid}" />&page=<c:out value="${pageBean.page + 1}"/>">&nbsp;</a>
                        <a class="lastPage"
                           href="<c:out value="${basePath}"/>/product/findByCsid?csid=<c:out value="${csid}" />&page=<c:out value="${pageBean.totalPage}"/>">&nbsp;</a>
                    </c:if>
                </c:if>
            </div>
        </form>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="<c:out value="${basePath}"/>/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
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
                <a>诚聘英才</a>
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
                <a>官网</a>
                |
            </li>
            <li>
                <a>论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2018-2099 网上商城 版权所有</div>
    </div>
</div>
<s:debug></s:debug>
</body>
</html>
