<%--
  Created by IntelliJ IDEA.
  User: breakl
  Date: 19-5-4
  Time: 下午12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>会员登录</title>

    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/login.css" rel="stylesheet" type="text/css"/>
    <script>
        function change() {
            var img1 = document.getElementById("checkImg");
            img1.src = "checkImg.action?" + new Date().getTime();
        }
    </script>

</head>
<body>
<%@include file="menu.jsp" %>
<div class="container login">
    <div class="span12">
        <div class="ad">
            <img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
        </div>
    </div>
    <div class="span12 last">
        <div class="wrap">
            <div class="main">
                <div class="title">
                    <strong>会员登录</strong>USER LOGIN
                </div>
                <form id="loginForm" action="user_login.action" method="post" novalidate="novalidate">
                    <table>
                        <tbody>
                        <tr>
                            <th>
                                用户名:
                            </th>
                            <td>
                                <input type="text" id="username" name="username" class="text" maxlength="20">
                                <span><s:fielderror fieldName="username"/></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                密&nbsp;&nbsp;码:
                            </th>
                            <td>
                                <input type="password" id="password" name="password" class="text" maxlength="20">
                                <span><s:fielderror fieldName="password"/> </span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                验证码:
                            </th>
                            <td>
										<span class="fieldSet">
											<input type="text" id="checkcode" name="checkcode" class="text captcha"
                                                   maxlength="4" autocomplete="off"><img id="checkImg"
                                                                                         class="captchaImage"
                                                                                         src="checkImg.action"
                                                                                         onclick="change()"
                                                                                         title="点击更换验证码"><s:actionerror/>
										</span>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <label>
                                    <input type="checkbox" id="isRememberUsername" name="isRememberUsername"
                                           value="true">记住用户名
                                </label>
                                <label>
                                    &nbsp;&nbsp;<a href="user_findPasswordPage.action">找回密码</a>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <input type="submit" class="submit" value="登 录">
                            </td>
                        </tr>
                        <tr class="register">
                            <th>&nbsp;

                            </th>
                            <td>
                                <dl>
                                    <dt>还没有注册账号？</dt>
                                    <dd>
                                        立即注册即可体验在线购物！
                                        <a href="./user_registPage.action">立即注册</a>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd"><img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/></div>
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
