<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>客户列表 - Powered By VDC</title>
<meta name="author" content="VDC Team" />
<meta name="copyright" content="VDC" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/list.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}
</style>
<script type="text/javascript">
$().ready(function() {
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${ctx}">首页</a> &raquo; 客户列表 <span>(共<span id="pageTotal">${pagination.numFound}</span>条记录)</span>
	</div>
	<form id="listForm" action="${ctx}/system/customer/list" method="get">
		<div class="bar">
			<a href="${ctx}/system/customer/create" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<jsp:include page="/WEB-INF/pages/common/pageSizeSelect.jsp" flush="true"/>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="customerName">名称</a>
						</li>
						<li>
							<a href="javascript:;" val="contactName">联系人</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" name="isCompany">是否企业</a>
				</th>
				<th>
					<a href="javascript:;" name="customerName">名称</a>
				</th>
				<th>
					<a href="javascript:;" name="contactName">联系人</a>
				</th>
				<th>
					<a href="javascript:;" name="telephone">电话</a>
				</th>
				<th>
					<a href="javascript:;" name="mobilephone">手机</a>
				</th>
				<th>
					<a href="javascript:;" name="email">邮箱</a>
				</th>
				<th>
					<a href="javascript:;" name="qq">QQ</a>
				</th>
				<th>
					<a href="javascript:;" name="fax">传真</a>
				</th>
				<th>
					<a href="javascript:;" name="accountBalance">账户余额</a>
				</th>
				<th>
					<a href="javascript:;" name="createTime">创建时间</a>
				</th>
				<th>
					<a href="javascript:;" name="updateTime">更新时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<c:forEach items="${pagination.data}" var="item">
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${item.customerId}" />
					</td>
					<td>
						<span class="${item.isCompany==1?'trueIcon':'falseIcon'}"></span>
					</td>
					<td>
						${item.customerName}
					</td>
					<td>
						${item.contactName}
					</td>
					<td>
						${item.telephone}
					</td>
					<td>
						${item.mobilephone}
					</td>
					<td>
						${item.email}
					</td>
					<td>
						${item.qq}
					</td>
					<td>
						${item.fax}
					</td>
					<td>
						${item.accountBalance}
					</td>
					<td>
						<fmt:formatDate var="createTime" value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="createDate" value="${item.createTime}" pattern="yyyy-MM-dd" />
						<span title="${createTime}">${createDate }</span>
					</td>
					<td>
						<fmt:formatDate var="updateTime" value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="updateDate" value="${item.updateTime}" pattern="yyyy-MM-dd" />
						<span title="${updateTime}">${updateDate}</span>
					</td>
					<td>
						<a href="${ctx}/system/customer/edit/${item.customerId}">[编辑]</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" id="searchProperty" name="searchProperty" value="" />
		<input type="hidden" id="orderProperty" name="orderProperty" value="" />
		<input type="hidden" id="orderDirection" name="orderDirection" value="" />
		<input type="hidden" id="pageSize" name="pageSize" value="3" />
		<jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true"/>
	</form>
</body>
</html>
