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
<li id="save" value="roleAction_update"><a href="#">保存</a></li>
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
   修改角色
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">名称：</td>
	            <td class="tableContent"><input type="text" name="name" value="${name}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>		
		</table>
	</div>
 
</div>
</form>
</body>
</html>

