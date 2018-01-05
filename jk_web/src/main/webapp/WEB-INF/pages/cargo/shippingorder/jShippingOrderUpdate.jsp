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
<li id="save"><a href="#" onclick="formSubmit('shippingOrderAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">等于PACKING_LIST_ID：</td>
	            <td class="tableContent"><input type="text" name="shippingOrderId" value="${shippingOrderId}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">SEA海运AIR空运：</td>
	            <td class="tableContent"><input type="text" name="orderType" value="${orderType}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="shipper" value="${shipper}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${consignee}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="notifyParty" value="${notifyParty}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="lcNo" value="${lcNo}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfLoading" value="${portOfLoading}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfTrans" value="${portOfTrans}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="portOfDischarge" value="${portOfDischarge}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="loadingDate" value="${loadingDate}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="limitDate" value="${limitDate}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">1是0否：</td>
	            <td class="tableContent"><input type="text" name="isBatch" value="${isBatch}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">1是0否：</td>
	            <td class="tableContent"><input type="text" name="isTrans" value="${isTrans}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="copyNum" value="${copyNum}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="specialCondition" value="${specialCondition}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="freight" value="${freight}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="checkBy" value="${checkBy}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">0草稿 1已上报：</td>
	            <td class="tableContent"><input type="text" name="state" value="${state}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createBy" value="${createBy}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createDept" value="${createDept}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">：</td>
	            <td class="tableContent"><input type="text" name="createTime" value="${createTime}"/></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

