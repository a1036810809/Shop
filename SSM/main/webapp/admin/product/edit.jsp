<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct/update"
      method="post" enctype="multipart/form-data">
    <input type="hidden" name="pid" value="<c:out value="${model.pid}"/>"/>
    <input type="hidden" name="image" value="<c:out value="${model.image}"/>"/>
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26">
                <STRONG>编辑商品</STRONG>
            </td>
        </tr>

        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                商品名称：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <input type="text" name="pname" value="<c:out value="${model.pname}" />" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                所属的二级分类：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <select name="csid">
                    <c:forEach var="cs" items="${csList}" varStatus="status">
                        <option value="<c:out value="${cs.csid}"/>" 
                                <c:if test="#{cs.csid == model.categorysecond.csid}">selected</c:if>><c:out value="${cs.csname}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                商品市场价格：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <input type="text" name="market_price" value="<c:out value="${model.marketPrice}" />" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                商品商城价格：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <input type="text" name="shop_price" value="<c:out value="${model.shopPrice}" />" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                是否热门：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <select name="isHot">
                    <option value="1" <c:if test="${model.isHot == 1}">selected</c:if>>是</option>
                    <option value="0" <c:if test="${model.isHot == 0}">selected</c:if>>否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                商品图片：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <input type="file" name="upload"/>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" bgColor="#f5fafe" class="ta_01">
                商品描述：
            </td>
            <td align="center" class="ta_01" bgColor="#ffffff">
                <textarea rows="5" cols="20" name="pdesc"><c:out value="${model.pdesc}"/></textarea>
            </td>
        </tr>

        <tr>
            <td class="ta_01" style=" WIDTH: 100%" align="center"
                bgColor="#f5fafe" colSpan="4">
                <button type="submit" value="确定" class="button_ok">
                    &#30830;&#23450;
                </button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</HTML>