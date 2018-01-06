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
<li id="save" value="invoiceAction_insert"><a href="#">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增发票
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">

	        <tr>
	            <td class="columnTitle">SC_NO：</td>
	            <td class="tableContent"><input type="text" name="scNo" value=""/></td>

	            <td class="columnTitle">BL_NO：</td>
	            <td class="tableContent"><input type="text" name="blNo" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value=""/></td>

	        </tr>
		</table>
	</div>

	<div class="eXtremeTable" >
		<table id="ec_table" class="tableRegion" width="98%" >
			<thead>
			<tr>
				<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
				<td class="tableHeader">序号</td>
				<td class="tableHeader">委托单号</td>
				<td class="tableHeader">海运/空运</td>
				<td class="tableHeader">货主</td>
				<td class="tableHeader">装期</td>
				<td class="tableHeader">份数</td>
				<td class="tableHeader">复核人</td>
				<td class="tableHeader">状态</td>
			</tr>
			</thead>
			<tbody class="tableBody" >
			${page.links}
			<c:forEach items="${page.results}" var="o" varStatus="status">
			<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
				<td><input type="checkbox" name="id" value="${o.id}"/></td>
				<td>${status.index+1}</td>
				<td>${o.id}</td>
				<td>${o.orderType}</td>
				<td>${o.shipper}</td>
				<td><fmt:formatDate value="${o.loadingDate}" pattern="yyyy-MM-dd"/></td>
				<td>${o.copyNum}</td>
				<td>${o.checkBy}</td>
				<td>
					<c:if test="${o.state==0}">草稿</c:if>
					<c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>
				</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
 
 
</form>
</body>
</html>

