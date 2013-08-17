<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>用户列表 - Powered By VDC</title>
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
		<a href="${ctx}">首页</a> &raquo; 用户列表 <span>(共<span id="pageTotal">${pagination.numFound}</span>条记录)</span>
	</div>
	<form id="listForm" action="${ctx}/setting/user/list" method="get">
		<div class="bar">
			<a href="${ctx}/setting/user/create" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled" actionUrl="${ctx}/setting/user/delete">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
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
							<a href="javascript:;" val="userName">用户名</a>
						</li>
						<li>
							<a href="javascript:;" val="realName">名称</a>
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
					<a href="javascript:;" name="userName">用户名</a>
				</th>
				<!-- <th>
					<a href="javascript:;" name="customerId">所属客户</a>
				</th> -->
				<th>
					<a href="javascript:;" name="roleId">角色</a>
				</th>
				<th>
					<a href="javascript:;" name="realName">名称</a>
				</th>
				<th>
					<a href="javascript:;" name="email">邮箱</a>
				</th>
				<th>
					<a href="javascript:;" name="qq">QQ</a>
				</th>
				<th>
					<a href="javascript:;" name="telephone">电话</a>
				</th>
				<th>
					<a href="javascript:;" name="mobilephone">手机</a>
				</th>
				<th>
					<a href="javascript:;" name="fax">传真</a>
				</th>
				<th>
					<a href="javascript:;" name="isLocked">是否启用</a>
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
						<input type="checkbox" name="ids" value="${item.userId}" />
					</td>
					<td>
						${item.userName}
					</td>
					<%-- <td>
						${item.customerId}
					</td> --%>
					<td>
						${item.roleId}
					</td>
					<td>
						${item.realName}
					</td>
					<td>
						${item.email}
					</td>
					<td>
						${item.qq}
					</td>
					<td>
						${item.telephone}
					</td>
					<td>
						${item.mobilephone}
					</td>
					<td>
						${item.fax}
					</td>
					<td>
						<span class="${item.isLocked==0?'trueIcon':'falseIcon'}"></span>
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
						<a href="${ctx}/setting/user/edit/${item.userId}">[编辑]</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" id="searchProperty" name="searchProperty" value="" />
		<input type="hidden" id="orderProperty" name="orderProperty" value="" />
		<input type="hidden" id="orderDirection" name="orderDirection" value="" />
		<input type="hidden" id="pageSize" name="pageSize" value="10" />
		<jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true"/>
	</form>
</body>
</html>
