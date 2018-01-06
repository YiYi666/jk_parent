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
      <%--<input type="hidden" name="id" value="${id}"/>--%>
      
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save" value="systemInfoAction_update"><a href="#">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改厂家
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle">id：</td>
				<td class="tableContent"><input type="text" readonly name="id" value="${id }"/></td>
				<td class="columnTitle">模块名：</td>
				<td class="tableContent"><input type="text" name="name" value="${name }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">父模块：${parentId}</td>
				<td class="tableContent">
					<select name="parentId" >
						<option value="">无父模块</option>
						<c:forEach var="module" items="${moduleList}">
						<option value="${module.id}"<c:if test="${module.id==parentId}">selected</c:if>>${module.name}</option>
						</c:forEach>
					</select>

				</td>

				<td class="columnTitle">复用标识：</td>
				<td class="tableContent"><input type="text" name="cwhich" value=""/></td>
			</tr>
			<tr>
				<td class="columnTitle">层数：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="layerNum" value="${layerNum }"/></td>
				<td class="columnTitle">权限标识：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="cpermission" value="${cpermission }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">引用次数：</td>
				<td class="tableContent"><input type="text" name="quoteNum" value="${quoteNum }"/></td>
				<td class="columnTitle">是否页节点：</td>
				<td class="tableContentAuto">
					<input type="radio" name="isLeaf" value="1" ${isLeaf==1?"checked":"" } class="input">否
					<input type="radio" name="isLeaf" value="0" ${isLeaf==0?"checked":"" } class="input">是
				</td>
			</tr>
			<tr>
				<td class="columnTitle">状态：</td>
				<td class="tableContentAuto">
					<input type="radio" name="state" value="1" ${state==1?"checked":"" } class="input">正常
					<input type="radio" name="state" value="0" ${state==0?"checked":"" } class="input">停用
				</td>
				<td class="columnTitle">创建日期：</td>
				<td class="tableContent">
					<input type="text" style="width:90px;" name="createTime" readonly
						   value="<fmt:formatDate value='${createTime }' pattern='yyyy-MM-dd' />"
						   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			<tr>
				<td class="columnTitle">修改人：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="updateBy" value=""/></td>
				<td class="columnTitle">修改日期：</td>
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<td class="tableContent">
					<input type="text" style="width:90px;" name="updateTime"
						   value="<fmt:formatDate value='${now }' pattern='yyyy-MM-dd' />"
						   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			<tr>
				<td class="columnTitle">链接：</td>
				<td class="tableContent"><input type="text"  name="curl" value="${curl }"/></td>
				<td class="columnTitle">说明：</td>
				<td class="tableContent"><input type="text"  name="remark" value="${remark }"/></td>
			</tr>
		</table>
	</div>
 </form>
</body>
</html>
