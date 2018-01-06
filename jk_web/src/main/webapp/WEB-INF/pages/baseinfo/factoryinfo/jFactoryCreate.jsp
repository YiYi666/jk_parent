<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">

	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="save" value="factoryInfoAction_insert"><a href="#">保存</a></li>
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
					新增模块节点
				</div>
			</div>
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">id：</td>
					<td class="tableContent"><input type="text" readonly name="id" value=""/></td>
					<td class="columnTitle">模块名：</td>
					<td class="tableContent"><input type="text" name="name" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">父id：</td>
					<td class="tableContent"><input type="text" name="parentId" value=""/></td>
					<td class="columnTitle">父节点名：</td>
					<td class="tableContent"><input type="text" name="parentName" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">层数：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="layerNum" value=""/></td>
					<td class="columnTitle">链接：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="curl" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">引用次数：</td>
					<td class="tableContent"><input type="text" name="quoteNum" value=""/></td>
					<td class="columnTitle">是否页节点：</td>
					<td class="tableContentAuto">
						<input type="radio" name="isLeaf" value="1"  class="input">否
						<input type="radio" name="isLeaf" value="0" checked  class="input">是
					</td>
				</tr>
				<tr>
					<td class="columnTitle">状态：</td>
					<td class="tableContentAuto">
						<input type="radio" name="state" value="1" checked class="input">正常
						<input type="radio" name="state" value="0"  class="input">停用
					</td>
					<td class="columnTitle">创建日期：</td>
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<td class="tableContent">
						<input type="text" style="width:90px;" name="createTime" readonly
							   value="<fmt:formatDate value='${now }' pattern='yyyy-MM-dd' />"
							   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
					</td>
				</tr>
				<tr>
					<td class="columnTitle">修改人：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="updateBy" value=""/></td>
					<td class="columnTitle">修改日期：</td>
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<td class="tableContent">
						<input type="text" style="width:90px;" name="updateTime" readonly
							   value="<fmt:formatDate value='${now }' pattern='yyyy-MM-dd' />"
							   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
					</td>
				</tr>
				<tr>
					<td class="columnTitle">链接：</td>
					<td class="tableContent"><input type="text"  name="curl" value=""/></td>
					<td class="columnTitle">说明：</td>
					<td class="tableContent"><input type="text"  name="remark" value=""/></td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>

