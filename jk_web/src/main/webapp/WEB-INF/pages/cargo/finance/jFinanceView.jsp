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
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   浏览发票
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票单号：</td>
	            <td class="tableContent">${id}</td>

	            <td class="columnTitle">SC_NO：</td>
	            <td class="tableContent">${scNo}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">BL_NO：</td>
	            <td class="tableContent">${blNo}</td>

	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent">${tradeTerms}</td>
	        </tr>	

	        <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
					<c:if test="${state==0}">草稿</c:if>
					<c:if test="${state==1}"><b><font color="green">已上报</font></b></c:if>
				</td>

	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent">${createTime}</td>
	        </tr>
		</table>
	</div>
 
 
</form>
</body>
</html>

