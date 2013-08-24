<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript">
	$(function(){
		$('#tt2').tree({
			checkbox: true,
			url:'${ctx}/system/role/loadMenuTree/${roleId}',
			onClick:function(node){
				//$('#tt2').tree('options').url='http://localhost/Super_sqmis/loadTypeTreeUseJson.do?rootPcode='+node.id;
				//$(this).tree('toggle', node.target);
				//alert('you dbclick '+node.text);
			},
			onContextMenu: function(e, node){
				e.preventDefault();
				$('#tt2').tree('select', node.target);
			},
			onCheck:function(node){
			}
		});
	});
	function setRoleMenuData(){
		var nodes = $('#tt2').tree('getChecked');
		var json='';
		for(var i=0; i<nodes.length; i++){
			if($('#tt2').tree('isLeaf',nodes[i].target)){
				var id=nodes[i].id;
				var pObj=$('#tt2').tree('getParent',nodes[i].target);
				var pid=pObj.id;
				var ppObj=$('#tt2').tree('getParent',pObj.target);
				var ppid=ppObj.id;
				json+='{"id":"'+id+'","pid":"'+pid+'","ppid":"'+ppid+'"}';
				if(i!=nodes.length-1){
					json+=',';
				}
			}
		}
		json='['+json+']';
		$('#roleFunction_Data').val(json);

		var callback=function(message){
			$.message(message);
		};
		ajaxRequestCommon4Form("#roleFunctionForm",false,null,"post","json",callback);
		//$('#roleFunctionForm').submit();
	}
</script>
<ul>
	<form id="roleFunctionForm" target="mainFrame" action="${ctx}/system/role/doAuthorize/${roleId}" method="post">
	 <input id="roleFunction_Data" name="roleMenuData" type="hidden"/> 
	<!-- <input type="button" value="确定" onclick="submitRoleMenuData();" />
	<input type="button" value="关闭" onclick="" /> -->
	</form>
</ul>
<ul id="tt2"></ul>
