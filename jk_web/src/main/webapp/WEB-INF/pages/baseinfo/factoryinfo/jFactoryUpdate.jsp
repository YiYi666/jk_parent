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
<li id="save" value="factoryInfoAction_update"><a href="#">保存</a></li>
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
				<td class="columnTitle">厂家id：</td>
				<td class="tableContent"><input type="text" readonly name="id" value="${id }"/></td>
				<td class="columnTitle">供货类型：</td>
				<td class="tableContent"><input type="text" name="ctype" value="${ctype }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">厂家全名：</td>
				<td class="tableContent"><input type="text" name="fullName" value="${fullName }"/></td>
				<td class="columnTitle">厂名缩写：</td>
				<td class="tableContent"><input type="text" name="factoryName" value="${factoryName }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">联系人：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="contacts" value="${contacts }"/></td>
				<td class="columnTitle">电话：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="phone" value="${phone }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">手机：</td>
				<td class="tableContent"><input type="text" name="mobile" value="${mobile }"/></td>
				<td class="columnTitle">传真：</td>
				<td class="tableContent"><input type="text" name="fax" value="${fax }"/></td>
			</tr>
			<tr>
				<td class="columnTitle">验货员：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="inspector" value="${inspector }"/></td>
				<td class="columnTitle">排序号：</td>
				<td class="tableContent"><input type="text" style="width:90px;" name="orderNo" value="${orderNo }"/></td>
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
						   value="<fmt:formatDate value='${createTime }' pattern='yyyy-MM-dd' />"/>
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
				<td class="columnTitle">地址：</td>
				<td class="tableContent"><textarea name="address" style="height:150px;">${address }</textarea>
				<td class="columnTitle">说明：</td>
				<td class="tableContent"><textarea name="remark" style="height:150px;">${remark }</textarea>
			</tr>
		</table>
	</div>
 </form>
</body>
</html>
