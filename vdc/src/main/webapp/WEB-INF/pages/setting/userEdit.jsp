<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑用户 - Powered By VDC</title>
<meta name="author" content="VDC Team" />
<meta name="copyright" content="VDC" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.tools.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/input.js"></script>
<style type="text/css">
	.specificationSelect {
		height: 100px;
		padding: 5px;
		overflow-y: scroll;
		border: 1px solid #cccccc;
	}
	
	.specificationSelect li {
		float: left;
		min-width: 150px;
		_width: 200px;
	}
</style>
<script type="text/javascript">
$().ready(function() {
	$("select[name='roleId']").val("${user.roleId}");
	
	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			userName: "required",
			roleId: "required",
			realName: "required",
			email: "required",
			qq: "required",
			telephone: "required",
			mobilephone: "required"
		},
		messages: {
		},
		submitHandler: function(form) {
			form.submit();
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${ctx }">首页</a> &raquo; 编辑用户
	</div>
	<c:set var="actionUrl" value="${ctx}/setting/user/save"/>
	<c:if test="${not empty userId}">
	<c:set var="actionUrl" value="${ctx}/setting/user/update/${userId}"/>
	</c:if>
	<form id="inputForm" action="${actionUrl}" method="post">
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>用户名:
				</th>
				<td>
					<c:choose>
						<c:when test="${not empty userId}">
							<input type="text" name="userName" value="${user.userName}" class="text" maxlength="200" disabled="disabled"/>
						</c:when>
						<c:otherwise>
							<input type="text" name="userName" value="${user.userName}" class="text" maxlength="200" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>角色:
				</th>
				<td>
					<select name="roleId">
						<option value=""></option>
						<c:forEach items="${roleList}" var="item">
							<option value="${item.roleId}">${item.roleName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="realName" value="${user.realName}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>邮箱:
				</th>
				<td>
					<input type="text" name="email" value="${user.email}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>QQ:
				</th>
				<td>
					<input type="text" name="qq" value="${user.qq}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>电话:
				</th>
				<td>
					<input type="text" name="telephone" value="${user.telephone}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>手机:
				</th>
				<td>
					<input type="text" name="mobilephone" value="${user.mobilephone}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					传真:
				</th>
				<td>
					<input type="text" name="fax" value="${user.fax}" class="text" maxlength="200" />
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${ctx}/setting/user/list'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
