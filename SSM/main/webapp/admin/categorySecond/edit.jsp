<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>

<body>
<form id="userAction_save_do" name="Form1"
      action="${pageContext.request.contextPath}/adminCategorySecond/update" method="post">
    <input type="hidden" name="csid" value="<c:out value="${model.csid}" />"/>
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26">
                <STRONG>编辑二级分类</STRONG>
            </td>
        </tr>

        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                二级分类名称：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="csname" value="<c:out value="${model.csname}" />" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                所属的一级分类：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <select name="category.cid">
                    <c:forEach var="c" items="${cList}">
                        <option value="<c:out value="${c.cid}" />"
                                <c:if test="${model.category.cid==c.cid}">selected</c:if>>
                            <c:out value="${c.cname}"/></option>
                    </c:forEach>
                </select>
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