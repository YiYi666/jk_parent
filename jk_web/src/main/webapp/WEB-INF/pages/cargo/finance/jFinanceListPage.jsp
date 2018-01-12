<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="javascript:toView('financeAction_toview')">查看</a></li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('financeAction_tocreate','_self');this.blur();">新增</a></li>
                        <li id="update"><a href="javascript:toUpdate('financeAction_toupdate')">修改</a></li>
                        <li id="submit"><a href="javascript:toSubmit('financeAction_submit')">提交</a></li>
                        <li id="delete"><a href="javascript:toDelete('financeAction_delete')">删除</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox-title">
        <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
        财务单列表
    </div>

    <div>


        <div class="eXtremeTable">
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">财务单号</td>
                    <td class="tableHeader">制单时间</td>
                    <td class="tableHeader">制单人</td>
                    <td class="tableHeader">状态</td>
                </tr>
                </thead>
                <tbody class="tableBody">
                ${page.links}
                <c:forEach items="${page.results}" var="o" varStatus="status">
                <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" style="text-align: left">
                    <td><input type="checkbox" name="id" value="${o.id}"/></td>
                    <td>${status.index+1}</td>
                    <td>${o.id}</td>
                    <td><fmt:formatDate value="${o.inputDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${o.inputBy}</td>
                    <td>
                            ${o.state==0?"草稿":"已上报"}
                        <%--<c:if test="${o.state==0}">草稿</c:if>
                        <c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>--%>
                    </td>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

