<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<c:set var="paginationObjectName" value="${(not empty param.paginationObjectName)?param.paginationObjectName:'pagination'}"/>
<c:set var="pagination" value="${requestScope[paginationObjectName]}"/>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" class="${pagination.pageSize==10?'current':''}" val="10">10</a>
							</li>
							<li>
								<a href="javascript:;" class="${pagination.pageSize==20?'current':''}" val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" class="${pagination.pageSize==50?'current':''}" val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" class="${pagination.pageSize==100?'current':''}" val="100">100</a>
							</li>
						</ul>
					</div>
				</div>