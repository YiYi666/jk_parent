<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<li id="view"><a href="#" onclick="formSubmit('shippingOrderAction_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('shippingOrderAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('shippingOrderAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('shippingOrderAction_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">等于PACKING_LIST_ID</td>
		<td class="tableHeader">SEA海运AIR空运</td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader">1是0否</td>
		<td class="tableHeader">1是0否</td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader">0草稿 1已上报</td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
		<td class="tableHeader"></td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<td><a href="shippingOrderAction_toview?id=${o.id}">${o.deptName}</a></td>
		<td>${o.shippingOrderId}</td>
		<td>${o.orderType}</td>
		<td>${o.shipper}</td>
		<td>${o.consignee}</td>
		<td>${o.notifyParty}</td>
		<td>${o.lcNo}</td>
		<td>${o.portOfLoading}</td>
		<td>${o.portOfTrans}</td>
		<td>${o.portOfDischarge}</td>
		<td>${o.loadingDate}</td>
		<td>${o.limitDate}</td>
		<td>${o.isBatch}</td>
		<td>${o.isTrans}</td>
		<td>${o.copyNum}</td>
		<td>${o.remark}</td>
		<td>${o.specialCondition}</td>
		<td>${o.freight}</td>
		<td>${o.checkBy}</td>
		<td>${o.state}</td>
		<td>${o.createBy}</td>
		<td>${o.createDept}</td>
		<td>${o.createTime}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

