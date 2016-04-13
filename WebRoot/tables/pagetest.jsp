<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<form id="pagerForm" method="post" action="AllAction_userSearch.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${pages.pageSize}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="AllAction_userSearch.do" method="post">
	<div class="searchBar">
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="90">
		<thead>
			<tr>
				<th width="80">序号</th>
				<th width="120" orderField="id" <c:if test='${param.orderField == "id" }'> class="${param.orderDirection}"  </c:if>   >id</th>
				<th width="120" orderField="name" <c:if test='${param.orderField == "name" }'> class="${param.orderDirection}"  </c:if> >姓名</th>
				<th width="120" orderField="age" <c:if test='${param.orderField == "age" }'> class="${param.orderDirection}"  </c:if>  >年龄</th>
				<th >corp_id</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach var="data" items="${pages.pages}" varStatus="status" > 
			<tr target="sid" rel="${data.id}">
				<td>${status.count + (pages.pageNo-1)*pages.pageSize}</td>
				<td>${data.id}</td>
				<td>${data.name}</td>
				<td>${data.age }</td>
				<td>${data.corp_id}</td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${pages.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pages.totalCount}" numPerPage="${pages.pageSize}" pageNumShown="10" currentPage="${pages.pageNo}"></div>
	</div>
</div>
