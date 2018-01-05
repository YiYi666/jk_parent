<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
<li id="save"><a href="#" onclick="formSubmit('shippingOrderAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   新增部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">等于PACKING_LIST_ID：</td>
	            <td class="tableContent"><input type="text" name="shippingOrderId" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">SEA海运AIR空运：</td>
	            <td class="tableContent"><input type="text" name="orderType" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="shipper" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="consignee" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="notifyParty" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="lcNo" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfLoading" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfTrans" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfDischarge" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="loadingDate" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="limitDate" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">1是0否：</td>
	            <td class="tableContent"><input type="text" name="isBatch" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">1是0否：</td>
	            <td class="tableContent"><input type="text" name="isTrans" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="copyNum" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="remark" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="specialCondition" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="freight" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="checkBy" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">0草稿 1已上报：</td>
	            <td class="tableContent"><input type="text" name="state" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createBy" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createDept" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createTime" value=""/></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

