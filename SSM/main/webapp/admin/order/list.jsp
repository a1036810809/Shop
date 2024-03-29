<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
    <script type="text/javascript">
        function showDetail(oid) {
            //获得按钮对象
            var but = document.getElementById("but" + oid);
            //获得div对象
            var div1 = document.getElementById("div" + oid);
            if (but.value == "订单详情") {
                //1.创建异步加载对象
                var xhr = createXmlHttp();
                //2.设置监听
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        if (xhr.status == 200) {

                            div1.innerHTML = xhr.responseText;
                        }
                    }
                }
                //3.打开链接
                xhr.open("GET", "${pageContext.request.contextPath}/adminOrder/findOrderItem?time=" + new Date().getTime() + "&oid=" + oid, true);
                //4.发送
                xhr.send(null);
                but.value = "关闭";
            } else {
                but.value = "订单详情";
                div1.innerHTML = "";
            }


            function createXmlHttp() {
                var xmlHttp;
                if (window.XMLHttpRequest) {
                    xmlHttp = new XMLHttpRequest();
                } else {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHttp");
                }
                return xmlHttp;
            }
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订单列表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                        <td align="center" width="7%">
                            序号
                        </td>
                        <td align="center" width="10%">
                            订单编号
                        </td>
                        <td align="center" width="10%">
                            总金额
                        </td>
                        <td align="center" width="10%">
                            收货人
                        </td>
                        <td align="center" width="10%">
                            订单状态
                        </td>
                        <td width="*" align="center">
                            订单详情
                        </td>
                    </tr>
                    <c:forEach var="order" varStatus="status" items="${pageBean.list}">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">
                                <c:out value="${status.count}"/>
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                <c:out value="${order.oid}"/>
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                <c:out value="${order.total}"/>
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                    <c:out value="${order.name}"/>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                <c:if test="${order.state==1}">
                                    未付款
                                </c:if>
                                <c:if test="${order.state==2}">
                                    <a href="${pageContext.request.contextPath}/adminOrder/updateState?oid=<c:out value="${order.oid}"/>"><font
                                            color="blue">发货</font></a>
                                </c:if>
                                <c:if test="${order.state==3}">
                                    未确认收货
                                </c:if>
                                <c:if test="${order.state==4}">
                                    交易完成
                                </c:if>
                            </td>
                            <td align="center" style="HEIGHT: 22px">
                                <input id="but<c:out value="${order.oid}" />" type="button" value="订单详情"
                                       onclick="showDetail(<c:out value="${order.oid}"/>)"/>
                                <div id="div<c:out value="${order.oid}"/>"></div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr align="center">
            <td class="ta_01" align="center" bgColor="#afd1f3">
                第<c:out value="${pageBean.page}"/>/<c:out value="${pageBean.totalPage}"/>页&nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${pageBean.page != 1}">
                    <a href="${pageContext.request.contextPath}/adminOrder/findAllByPage?page=1">首页</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/adminOrder/findAllByPage?page=<c:out value="${pageBean.page-1}"/>">上一页</a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${pageBean.page != pageBean.totalPage}">
                    <a href="${pageContext.request.contextPath}/adminOrder/findAllByPage?page=<c:out value="${pageBean.page+1}"/>">下一页</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/adminOrder/findAllByPage?page=<c:out value="${pageBean.totalPage}"/>">尾页</a>
                </c:if>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>

