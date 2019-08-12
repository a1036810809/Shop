<%--
  Created by IntelliJ IDEA.
  User: breakl
  Date: 19-5-4
  Time: 下午13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td align="center">商品图片</td>
        <td align="center">商城价</td>
        <td align="center">数量</td>
        <td align="center">小计</td>
    </tr>
    <c:forEach var="orderItem" items="${list}">
        <tr>
            <td align="center"><img width="45" height="45" src="${pageContext.request.contextPath}/<c:out value="${orderItem.product.image}" />"></td>
            <td align="center"><c:out value="${orderItem.product.shopPrice}"/></td>
            <td align="center"><c:out value="${orderItem.count}"/></td>
            <td align="center"><c:out value="${orderItem.subtotal}"/></td>
        </tr>
    </c:forEach>
</table>