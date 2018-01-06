<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%--<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>--%>
    <script>
        /*function isOnlyChecked() {
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
                formSubmit('systemInfoAction_toview', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现更新
        function toUpdate() {
            if (isOnlyChecked()) {
                formSubmit('systemInfoAction_toupdate', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }*/

        function toSub() { //子目录
            if (isOnlyChecked()) {
                var i = $("input[name='id']").attr("isLeaf");
                if(i!=0) {
                formSubmit('systemInfoAction_list', '_self');
                }
                else {
                    alert("该节点为叶子节点，无子目录")
                }
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }
    </script>
</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="#" onclick="javascript:toView('systemInfoAction_toview')">查看</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('systemInfoAction_tocreate','_self');this.blur();">新增</a>
                        </li>
                        <li id="update"><a href="#" onclick="javascript:toUpdate('systemInfoAction_toupdate')">修改</a></li>
                        <li id="delete"><a href="#"
                                           onclick="javascript:toDelete('systemInfoAction_delete')">删除</a></li>
                        <li id="view"><a href="#" onclick="javascript:toSub()">子目录</a></li>
                        <li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    模块层级列表
                </div>
            </div>
        </div>

        <div>


            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="100%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">id</td>
                        <td class="tableHeader">模块名</td>
                        <td class="tableHeader">父id</td>
                        <td class="tableHeader">父节点名</td>
                        <td class="tableHeader">层数</td>
                        <td class="tableHeader">链接</td>
                        <td class="tableHeader">说明</td>
                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">
                    ${links }

                    <c:forEach items="${results }" var="module" varStatus="st">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
                            <td><input type="checkbox" name="id" value="${module.id }" isLeaf="${module.isLeaf}"/></td>
                            <td>${st.count }</td>
                            <td>${module.id }</td>
                            <td>${module.name }</td>
                            <td>${module.parentId==null?"没有父节点":module.parentId}</td>
                            <td>${module.parentName==null?"没有父节点":module.parentName}</td>
                            <td>${module.layerNum }</td>
                            <td>${module.curl }</td>
                            <td>${module.remark }</td>
                            <td>${module.state==0?"停用":"正常"}</td>
                            <%--<td><a href="systemInfoAction_toview?id=${module.id }">${module.moduleName }</a></td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</form>
</body>
</html>

