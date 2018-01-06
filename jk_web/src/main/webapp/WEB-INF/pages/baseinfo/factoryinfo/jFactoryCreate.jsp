<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
					新增厂家
				</div>
			</div>
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">厂家id：</td>
					<td class="tableContent"><input type="text" readonly value="自动生成"/></td>
					<td class="columnTitle">供货类型：</td>
					<td class="tableContent"><input type="text" name="ctype" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">厂家全名：</td>
					<td class="tableContent"><input type="text" name="fullName" value=""/></td>
					<td class="columnTitle">厂名缩写：</td>
					<td class="tableContent"><input type="text" name="factoryName" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">联系人：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="contacts" value=""/></td>
					<td class="columnTitle">电话：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="phone" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">手机：</td>
					<td class="tableContent"><input type="text" name="mobile" value=""/></td>
					<td class="columnTitle">传真：</td>
					<td class="tableContent"><input type="text" name="fax" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">验货员：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="inspector" value=""/></td>
					<td class="columnTitle">排序号：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="orderNo" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">创建人：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="createBy" value=""/></td>
					<td class="columnTitle">创建部门：</td>
					<td class="tableContent"><input type="text" style="width:90px;" name="createDept" value=""/></td>
				</tr>
				<tr>
					<td class="columnTitle">状态：</td>
					<td class="tableContentAuto">
						<input type="radio" name="state" value="1" checked class="input">正常
						<input type="radio" name="state" value="0" class="input">停用
					</td>
					<td class="columnTitle">创建日期：</td>
					<td class="tableContent">
						<input type="text" style="width:90px;" name="createTime"
							   value=""
							   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
					</td>
				</tr>
				<tr>
					<td class="columnTitle">地址：</td>
					<td class="tableContent"><textarea name="address" style="height:150px;"></textarea>
					<td class="columnTitle">说明：</td>
					<td class="tableContent"><textarea name="remark" style="height:150px;"></textarea>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>

