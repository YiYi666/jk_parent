<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   模块基础代码详情
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle">id：</td>
				<td class="tableContent">
					${id }
				</td>
			</tr>
			<tr>
				<td class="columnTitle">模块名：</td>
				<td class="tableContent">${name }</td>
			</tr>
			<tr>
				<td class="columnTitle">父id：</td>
				<td class="tableContent">${parentId==null?"没有父节点":parentId}</td>
			</tr>
			<tr>
				<td class="columnTitle">父节点名：</td>
				<td class="tableContent">${parentName==null?"没有父节点":parentName}</td>
			</tr>
			<tr>
				<td class="columnTitle">层数：</td>
				<td class="tableContent">${layerNum }</td>
			</tr>
			<tr>
				<td class="columnTitle">链接：</td>
				<td class="tableContent">${curl }</td>
			</tr>
			<tr>
				<td class="columnTitle">说明：</td>
				<td class="tableContent">${remark }</td>
			</tr>
			<tr>
				<td class="columnTitle">状态：</td>
				<td class="tableContent">${state==0?"停用":"正常"}</td>
			</tr>
			<tr>
				<td class="columnTitle">是否叶子节点：</td>
				<td class="tableContent">${isLeaf==0?"是":"否"}</td>
			</tr>
			<tr>
				<td class="columnTitle">引用次数：</td>
				<td class="tableContent">${quoteNum }</td>
			</tr>
			<tr>
				<td class="columnTitle">创建人：</td>
				<td class="tableContent">${createBy }</td>
			</tr>
			<tr>
				<td class="columnTitle">创建部门：</td>
				<td class="tableContent">${createDept }</td>
			</tr>
			<tr>
				<td class="columnTitle">创建日期：</td>
				<td class="tableContent"><%--<fmt:formatDate value='${createTime }' pattern='yyyy-MM-dd' />--%>${createTime }</td>
			</tr>
			<tr>
				<td class="columnTitle">修改人：</td>
				<td class="tableContent">${updateBy }</td>
			</tr>
			<tr>
				<td class="columnTitle">修改时间：</td>
				<td class="tableContent"><%--<fmt:formatDate value='${updateTime }' pattern='yyyy-MM-dd' />--%>${updateTime }</td>
			</tr>
		</table>
	</div>
 </form>
</body>
</html>