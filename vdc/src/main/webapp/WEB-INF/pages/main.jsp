<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 - Powered By VDC</title>
<meta name="author" content="VDC Team" />
<meta name="copyright" content="VDC" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<style type="text/css">
*{
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $nav = $("#nav a:not(:last)");
	var $menu = $("#menu dl");
	var $menuItem = $("#menu a");
	
	$nav.click(function() {
		var $this = $(this);
		$nav.removeClass("current");
		$this.addClass("current");
		var $currentMenu = $($this.attr("href"));
		$menu.hide();
		$currentMenu.show();
		return false;
	});
	
	$menuItem.click(function() {
		var $this = $(this);
		$menuItem.removeClass("current");
		$this.addClass("current");
	});

});
</script>
</head>
<body>
	<script type="text/javascript">
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<table class="main">
		<tr>
			<th class="logo">
				<a href="main.jhtml">
					<img src="${ctx}/images/header_logo.gif" alt="VDC" />
				</a>
			</th>
			<th>
				<div id="nav" class="nav">
					<ul>
						<c:forEach items="${menuMap}" var="aMap">
							<c:set var="aKey" value="${aMap.key}"/>
							<c:set var="aValue" value="${aMap.value}"/>
							<c:set var="menuId" value="${aKey}"/>
							<c:set var="menuName" value="${aValue[0].menuName}"/>
							<li>
								<a href="#menuLink___${menuId}">${menuName}</a>
							</li>
						</c:forEach>
							<li>
								<a href="${ctx}/center" target="iframe">首页</a>
							</li>
					</ul>
				</div>
				<div class="link">
					<a href="javascript:;" target="_blank">官方网站</a>|
					<a href="javascript:;" target="_blank">交流论坛</a>|
					<a href="javascript:;" target="_blank">关于我们</a>
				</div>
				<div class="link">
					<strong>${sessionScope['www_vdc_com_user_info'].userName}</strong>
					您好!
					<a href="javascript:;" target="iframe">[账号设置]</a>
					<a href="${ctx}/logout" target="_top">[注销]</a>
				</div>
			</th>
		</tr>
		<tr>
			<td id="menu" class="menu">
				<c:set var="menuDefaultSeletedClass" value="default"/>
				<c:forEach items="${menuMap}" var="aMap">
					<c:set var="aKey" value="${aMap.key}"/>
					<c:set var="aValue" value="${aMap.value}"/>
					<c:set var="menuId" value="${aKey}"/>
					<c:set var="menuName" value="${aValue[0].menuName}"/>
					<dl id="menuLink___${menuId}" class="${menuDefaultSeletedClass}">
						<dt>${menuName}</dt>
						<c:forEach items="${aValue}" var="aMenu" begin="1">
							<dd>
								<a href="${ctx}${aMenu.menuUri}" target="iframe">${aMenu.menuName}</a>
							</dd>
						</c:forEach>
					</dl>
					<c:set var="menuDefaultSeletedClass" value=""/>
				</c:forEach>
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="${ctx}/center" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>
