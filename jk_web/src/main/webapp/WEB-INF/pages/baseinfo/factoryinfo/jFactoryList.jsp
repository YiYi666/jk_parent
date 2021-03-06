<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
    <%--<script>
        function isOnlyChecked() {
            var checkBoxArray = document.getElementsByName('id');
            var count = 0;
            for (var index = 0; index < checkBoxArray.length; index++) {
                if (checkBoxArray[index].checked) {
                    count++;
                }
            }
            //jquery
            //var count = $("[input name='id']:checked").size();
            if (count == 1)
                return true;
            else
                return false;
        }

        function toView() {
            if (isOnlyChecked()) {
                formSubmit('factoryInfoAction_toview', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现更新
        function toUpdate() {
            if (isOnlyChecked()) {
                formSubmit('factoryInfoAction_toupdate', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }
    </script>--%>
</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="#" onclick="javascript:toView('factoryInfoAction_toview')">查看</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('factoryInfoAction_tocreate','_self');this.blur();">新增</a>
                        </li>
                        <li id="update"><a href="#" onclick="javascript:toUpdate('factoryInfoAction_toupdate')">修改</a></li>
                        <li id="delete"><a href="#" onclick="javascript:toDelete('factoryInfoAction_delete')">删除</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    厂家列表
                </div>
            </div>
        </div>

        <div>


            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">编号</td>
                        <td class="tableHeader">类型</td>
                        <td class="tableHeader">厂名</td>
                        <td class="tableHeader">联系人</td>
                        <td class="tableHeader">手机</td>
                        <td class="tableHeader">地址</td>
                        <td class="tableHeader">验货员</td>
                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">
                    ${links }

                    <c:forEach items="${results }" var="factory" varStatus="st">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
                            <td><input type="checkbox" name="id" value="${factory.id }"/></td>
                            <td>${st.count }</td>
                            <td>${factory.id }</td>
                            <td>${factory.ctype }</td>
                            <td><a href="factoryInfoAction_toview?id=${factory.id }">${factory.fullName }
                            <td>${factory.contacts }</td>
                            <td>${factory.mobile }</td>
                            <td>${factory.address }</td>
                            <td>${factory.inspector }</td>
                            <td>${factory.state==0?"停用":"正常"}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
</form>
</body>
</html>

