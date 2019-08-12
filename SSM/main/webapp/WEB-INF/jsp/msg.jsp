<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="${time};url=<c:out value="${basePath}"/>/${url}"> 
    <title>消息提醒</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="menu.jsp" %>
<div id="container">
    <table style="margin: auto" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="60%" border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <td style="width:98px">
                            <img src="<c:out value="${basePath}"/>/images/IconTexto_WebDev_009.jpg" width="128" height="128"/>
                        </td>
                        <td style="padding-top:30px">
                            ${msg}<br/>
                            ${time}秒后自动跳转
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <a href="<c:out value="${basePath}"/>/${url}">点击跳转</a>
                        </td>
                    </tr>
                </table>
        </tr>
    </table>
</div>
</body>
</html>