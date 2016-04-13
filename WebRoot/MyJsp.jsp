<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript"></script>
	<style  type="text/css"> 
		.undisplay {display: none;}
	</style>
  </head>
  
  <body>
    <div >This is my JSP page. <br></div>
    
    <table >
    	<tr><td class="undisplay">1列</td><td>2列</td><td>3列</td></tr>
    	<tr><td class="undisplay">1列</td><td>2列</td><td>3列</td></tr>
    	<tr><td class="undisplay">1列</td><td>2列</td><td>3列</td></tr>
    </table>
    <button class="close" value="关闭">关闭</button>
  </body>
</html>
