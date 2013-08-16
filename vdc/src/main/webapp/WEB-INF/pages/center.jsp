<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style type="text/css">
.input .powered {
	font-size: 11px;
	text-align: right;
	color: #cccccc;
}
</style>
<script type="text/javascript">
$().ready(function() {

	$.message("warn", "为了确保体验站点的完整性，您的部分操作将被限制执行!");

});
</script>
</head>
<body>
	<div class="path">
		管理中心首页
	</div>
	<table class="input">
		<tr>
			<th>
				系统名称:
			</th>
			<td>
				SHOP++网上商城系统
			</td>
			<th>
				系统版本:
			</th>
			<td>
				3.0 RELEASE
			</td>
		</tr>
		<tr>
			<th>
				官方网站:
			</th>
			<td>
				<a href="http://www.shopxx.net" target="_blank">http://www.shopxx.net</a>
			</td>
			<th>
				官方论坛:
			</th>
			<td>
				<a href="http://bbs.shopxx.net" target="_blank">http://bbs.shopxx.net</a>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				&nbsp;
			</td>
		</tr>
		<tr>
			<th>
				JAVA版本:
			</th>
			<td>
				1.6.0_45
			</td>
			<th>
				JAVA路径:
			</th>
			<td>
				/usr/shopxx/jdk6.0/jre
			</td>
		</tr>
		<tr>
			<th>
				操作系统名称:
			</th>
			<td>
				Linux
			</td>
			<th>
				操作系统构架:
			</th>
			<td>
				amd64
			</td>
		</tr>
		<tr>
			<th>
				Servlet信息:
			</th>
			<td>
				Apache Tomcat/6.0.37
			</td>
			<th>
				Servlet版本:
			</th>
			<td>
				2.5
			</td>
		</tr>
		<tr>
			<td colspan="4">
				&nbsp;
			</td>
		</tr>
		<tr>
			<th>
				上架商品数:
			</th>
			<td>
				300
			</td>
			<th>
				下架商品数:
			</th>
			<td>
				0
			</td>
		</tr>
		<tr>
			<th>
				商品库存报警数:
			</th>
			<td>
				5
			</td>
			<th>
				缺货商品数:
			</th>
			<td>
				5
			</td>
		</tr>
		<tr>
			<th>
				等待付款订单数:
			</th>
			<td>
				90
			</td>
			<th>
				等待发货订单数:
			</th>
			<td>
				0
			</td>
		</tr>
		<tr>
			<th>
				会员总数:
			</th>
			<td>
				285
			</td>
			<th>
				未读消息数:
			</th>
			<td>
				0
			</td>
		</tr>
		<tr>
			<td class="powered" colspan="4">
				COPYRIGHT © 2005-2013 SHOPXX.NET ALL RIGHTS RESERVED.
			</td>
		</tr>
	</table>
</body>
</html>
