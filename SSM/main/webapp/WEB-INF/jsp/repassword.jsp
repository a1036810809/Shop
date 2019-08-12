<%--
  Created by IntelliJ IDEA.
  User: breakl
  Date: 19-5-6
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>会员注册</title>
    <link href="<c:out value="${basePath}"/>/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<c:out value="${basePath}"/>/css/register.css" rel="stylesheet" type="text/css"/>
    <script>
        //简单前台js校验
        function checkForm() {
            var password = document.getElementById("password").value;
            var username = document.getElementById("username").value;
            var email = document.getElementById("email").value;
            var phone = document.getElementById("phone").value;
            if (email == null || email == ""){
                alert("邮箱不能为空");
                return false;
            }

            if (phone == null || phone == ""){
                alert("电话不能为空");
                return false;
            }

            if (username == null || username == ""){
                alert("用户名不能为空");
                return false;
            }

            if (password == null || password == '') {
                alert("密码不能为空！");
                return false;
            }

            //校验确认密码
            if (password != repassword) {
                alert("两次密码不一致！")
                return false;
            }

            return true;
        }

        function change() {
            var img1 = document.getElementById("checkImg");
            img1.src = "<c:out value="${basePath}"/>/checkImg?" + new Date().getTime();
        }
    </script>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container register">
    <div class="span24">
        <div class="wrap">
            <div class="main clearfix">
                <div class="title">
                    <strong>找回密码</strong>FIND PASSWORD
                </div>
                <form id="registerForm" action="<c:out value="${basePath}"/>/user/findPassword" method="post" novalidate="novalidate"
                      onsubmit="checkForm()">
                    <table>
                        <tbody>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>用户名:
                            </th>
                            <td>
                                <input type="text" id="username" name="username" class="text" maxlength="20"/>
                                <span id="span1"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>E-mail:
                            </th>
                            <td>
                                <input type="text" id="email" name="email" class="text" maxlength="200" pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"/>
                                <span></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>电话:
                            </th>
                            <td>
                                <input id="phone" type="text" name="phone" class="text">
                                <span></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>新的密码:
                            </th>
                            <td>
                                <input type="password" id="password" name="password" class="text" maxlength="20"
                                       autocomplete="off">
                                <span></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>确认密码:
                            </th>
                            <td>
                                <input type="password" id="repassword" name="repassword" class="text" maxlength="20"
                                       autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="requiredField">*</span>验证码:
                            </th>
                            <td>
										<span class="fieldSet">
											<input type="text" id="checkcode" name="checkcode" class="text captcha"
                                                   maxlength="4"><img id="checkImg" class="captchaImage"
                                                                      src="<c:out value="${basePath}"/>/checkImg" onclick="change()"
                                                                      title="点击更换验证码">
										</span>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <input type="submit" class="submit" value="找回密码">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="login">
                        <div class="ad">
                            <dl>
                                <dt>
                                    注册即享受
                                </dt>
                                <dd>
                                    正品保障、正规发票
                                </dd>
                                <dd>
                                    货到付款、会员服务
                                </dd>
                                <dd>
                                    自由退换、售后上门
                                </dd>
                            </dl>
                        </div>
                        <dl>
                            <dt>已经拥有账号了？</dt>
                            <dd>
                                立即登录即可体验在线购物！
                                <a href="<c:out value="${basePath}"/>/user/loginPage">立即登录</a>
                            </dd>
                        </dl>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="<c:out value="${basePath}"/>/iamge/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
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
<div id="_my97DP" style="position: absolute; top: -1970px; left: -1970px;">
    <iframe style="width: 190px; height: 191px;" src="./会员注册 - Powered By Mango Team_files/My97DatePicker.htm"
            frameborder="0" border="0" scrolling="no"></iframe>
</div>
</body>
</html>

