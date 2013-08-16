<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单列表 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />  
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/ui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.tools.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/list.js"></script>
<script type="text/javascript">
	$(function(){
		$('#tt2').tree({
			checkbox: false,
			url:'${ctx}/system/menu/load',
			onBeforeExpand:function(node){
				var url='${ctx}/system/menu/load/'+node.id;
				$('#tt2').tree('options').url=url;
			},
			onContextMenu: function(e, node){
				e.preventDefault();
				$('#tt2').tree('select', node.target);
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		});
	});
	function editMenu(){
		var node = $('#tt2').tree('getSelected');
		if (node){
			var actionUrl='${ctx}/system/menu/edit/'+node.id;
			$.dialog({
				title: "修改菜单",
				content: "",
				width: 470,
				modal: true,
				ok: "更&nbsp;&nbsp;新",
				cancel: "取&nbsp;&nbsp;消",
				onShow: function(){
					ajaxRequestCommon(".dialogContent",actionUrl,"get","html",null);
				},
				onOk: function(){
					var callback=function(message){
						$.message(message);
						if (message.type == "success") {
							doReload('parent');
						}
					};
					ajaxRequestCommon4Form("#inputForm",false,null,"post","json",callback);
				}
			});
		}
	}
	function removeMenu(){
		var node = $('#tt2').tree('getSelected');
		if (node){
			var actionUrl='${ctx}/system/menu/delete/'+node.id;
			$.dialog({
				title: "确认删除["+node.text+"]？",
				content: "",
				width: 250,
				modal: true,
				ok: "确&nbsp;&nbsp;定",
				cancel: "取&nbsp;&nbsp;消",
				onOk: function(){
					var callback=function(message){
						$.message(message);
						if (message.type == "success") {
							doReload('parent');
						}
					};
					ajaxRequestCommon(null,actionUrl,"get","json",callback);
				}
			});			
		}
	}
	function createMenu(){
		var node = $('#tt2').tree('getSelected');
		if (node){
			var actionUrl='${ctx}/system/menu/create?parentMenuId='+node.id;
			$.dialog({
				title: "添加菜单",
				content: "",
				width: 470,
				modal: true,
				ok: "保&nbsp;&nbsp;存",
				cancel: "取&nbsp;&nbsp;消",
				onShow: function(){
					ajaxRequestCommon(".dialogContent",actionUrl,"get","html",null);
				},
				onOk: function(){
					var callback=function(message){
						$.message(message);
						if (message.type == "success") {
							doReload('current');
						}
					};					
					ajaxRequestCommon4Form("#inputForm",false,null,"post","json",callback);
				}
			});
		}
	}
	function doReload(currentOrParent){
		var node = $('#tt2').tree('getSelected');
		if(currentOrParent=="parent"){
			var parent=$('#tt2').tree('getParent', node.target);
			if (parent){
				$('#tt2').tree('reload', parent.target);
			}
		}else{
			if (node){
				$('#tt2').tree('reload', node.target);
			}			
		}
	}
</script>
</head>
<body>
<ul id="tt2"></ul>
<div id="mm" class="easyui-menu" style="width:120px;">
	<div onclick="createMenu()" iconCls="icon-add">添加</div>
	<div onclick="removeMenu()" iconCls="icon-remove">删除</div>
	<div onclick="editMenu()" iconCls="icon-edit">修改</div>
</div>
</body>
</html>
