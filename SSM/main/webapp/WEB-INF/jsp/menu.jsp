<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="<c:out value="${basePath}"/>/index/index">
                <img src="<c:out value="${basePath}"/>/image/r___________renleipic_01/logo.jpg" alt="宇神商城">
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="<c:out value="${basePath}"/>/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
        </div>
    </div>
    <div class="span10 last">
        <div class="topNav clearfix">
            <ul>
                <c:choose>
                    <c:when test="${sessionScope.existUser == null}">
                        <li id="headerLogin" class="headerLogin" style="display: list-item;">
                            <a href="<c:out value="${basePath}"/>/user/loginPage">登录</a>|
                        </li>
                        <li id="headerRegister" class="headerRegister" style="display: list-item;">
                            <a href="<c:out value="${basePath}"/>/user/registPage">注册</a>|
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li id="headerLogin" class="headerLogin" style="display: list-item;">
                            <c:out value="${sessionScope.existUser.name}"/>|
                        </li>
                        <li id="headerLogin" class="headerLogin" style="display: list-item;">
                            <a href="<c:out value="${basePath}"/>/order/findByUid?page=1">我的订单</a>|
                        </li>
                        <li id="headerRegister" class="headerRegister" style="display: list-item;">
                            <a href="<c:out value="${basePath}"/>/user/logout">退出</a>|
                        </li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <a>会员中心</a>
                    |
                </li>
                <li>
                    <a>购物指南</a>
                    |
                </li>
                <li>
                    <a>关于我们</a>
                </li><br/>
                <li>
                    <a>本站第${countUser}位访客</a>
                </li>
            </ul>
        </div>
        <div class="cart">
            <a href="<c:out value="${basePath}"/>/cart/myCart">购物车</a>
        </div>
        <div class="phone">
            客服热线:
            <strong>96008/53277764</strong>
        </div>
    </div>
    <div class="span24">
        <ul class="mainNav">
            <li>
                <a href="<c:out value="${basePath}"/>/index/index">首页</a>
                |
            </li>
            <c:forEach var="c" items="${sessionScope.categories}">
                <li>
                    <a href="<c:out value="${basePath}"/>/product/findByCid?cid=<c:out value="${c.cid}"/>&page=1">
                        <c:out value="${c.cname}"/></a>
                    |
                </li>
            </c:forEach>
        </ul>
    </div>
</div>