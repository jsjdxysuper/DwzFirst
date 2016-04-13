<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>user.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="AllAction_userAdd.so" target="dialog" title="添加"
					width="500" height="200"><span>添加</span>
				</a>
				</li>
				<li><a class="delete" href="fhl_del.so?fhlform.nf={sid}"
					target="navTabTodo" title="确定要删除吗?"><span>删除</span>
				</a>
				</li>
				<li><a class="edit" href="fhl_edit.so?fhlform.nf={sid}"
					target="dialog" width="700" height="200"><span>修改</span>
				</a>
				</li>
			</ul>
		</div>
		<table class="table" layouth="90" width="90%">
			<thead>
				<tr>
					<th width="20%">Id</th>
					<th width="30%">Name</th>
					<th width="20%">age</th>
					<th width="30%">corp_id</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="data" items="${users }" varStatus="status">
					<tr target="sid" rel="${data.id }">
						<td>${data.id }</td>
						<td>${data.name }</td>
						<td>${data.age }</td>
						<td>${data.corp_id }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
