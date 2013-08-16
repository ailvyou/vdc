<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<c:set var="paginationObjectName" value="${(not empty param.paginationObjectName)?param.paginationObjectName:'pagination'}"/>
<c:set var="pagination" value="${requestScope[paginationObjectName]}"/>
<c:set var="totalPages" value="${pagination.totalPages}"/>
<c:set var="curPage" value="${pagination.pageNo}"/>
<c:if test="${totalPages > 1}">
		<div class="pagination">
			<a href="javascript:$.pageSkip(1);" class="firstPage"></a>
			<c:choose>
				<c:when test="${curPage==1}">
					<a href="javascript:;" class="previousPage"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:$.pageSkip(${curPage-1});" class="previousPage"></a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${curPage <= 10 }">
					<c:forEach var="n" begin="1" end="${totalPages>10?10:totalPages}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<a href="javascript:;" class="currentPage">${n}</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:$.pageSkip(${n});">${n}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${curPage+5 <= totalPages}">
					<c:forEach var="n" begin="${curPage-4}" end="${curPage+5}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<a href="javascript:;" class="currentPage">${n}</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:$.pageSkip(${n});">${n}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="n" begin="${curPage-4}" end="${totalPages}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<a href="javascript:;" class="currentPage">${n}</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:$.pageSkip(${n});">${n}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<span class="pageBreak">...</span>
			<c:choose>
				<c:when test="${curPage==totalPages}">
					<a href="javascript:;" class="nextPage"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:$.pageSkip(${curPage+1});" class="nextPage"></a>
				</c:otherwise>
			</c:choose>
			<a href="javascript:$.pageSkip(${totalPages});" class="lastPage"></a>
			<span class="pageSkip">跳转至&nbsp;&nbsp;<input type="text" id="pageNumber" name="pageNo" value="${curPage}">&nbsp;&nbsp;页
				<button type="submit">&nbsp;</button>
			</span>
		</div>
</c:if>