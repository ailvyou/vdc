<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑客户资料 - Powered By VDC</title>
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
	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			isCompany: "required",
			customerName: "required",
			contactName: "required",
			telephone: "required",
			mobilephone: "required",
			email: "required",
			qq: "required"
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
		<a href="${ctx }">首页</a> &raquo; 编辑客户资料
	</div>
	<c:set var="actionUrl" value="${ctx}/system/customer/save"/>
	<c:if test="${not empty customerId}">
	<c:set var="actionUrl" value="${ctx}/system/customer/update/${customerId}"/>
	</c:if>
	<form id="inputForm" action="${actionUrl}" method="post">
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>是否企业:
				</th>
				<td>
					<input type="text" name="isCompany" value="${customer.isCompany}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="customerName" value="${customer.customerName}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>联系人:
				</th>
				<td>
					<input type="text" name="contactName" value="${customer.contactName}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>电话:
				</th>
				<td>
					<input type="text" name="telephone" value="${customer.telephone}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>手机:
				</th>
				<td>
					<input type="text" name="mobilephone" value="${customer.mobilephone}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>邮箱:
				</th>
				<td>
					<input type="text" name="email" value="${customer.email}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>QQ:
				</th>
				<td>
					<input type="text" name="qq" value="${customer.qq}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					传真:
				</th>
				<td>
					<input type="text" name="fax" value="${customer.fax}" class="text" maxlength="200" />
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
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${ctx}/system/customer/list'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
