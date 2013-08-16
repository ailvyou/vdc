<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
	<c:set var="actionUrl" value="${ctx}/system/menu/save"/>
	<c:if test="${not empty menuId}">
	<c:set var="actionUrl" value="${ctx}/system/menu/update/${menuId}"/>
	</c:if>
	<form id="inputForm" action="${actionUrl}" method="post">
		<input type="hidden" name="parentMenuId" value="${parentMenuId}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="menuName" value="${menu.menuName}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					链接:
				</th>
				<td>
					<input type="text" name="menuUri" value="${menu.menuUri}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="menuDescription" value="${menu.menuDescription}" class="text" maxlength="200" />
				</td>
			</tr>
		</table>
	</form>

