<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!-- 引入struts的标签库-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
</head>
<body>
<div style="position:absolute;z-index:-1;width:100%;height:100%;">
    	<img src="4.jpg" width="100%" height="100%" />
</div>
<center>
<h1>查询成功</h1>
 <table border="1" width="50%"  cellspacing="0" cellpadding="0">    
    <tr>    
      <td>ISBN</td>    
      <td>题目</td>    
      <td>作者ID</td>    
      <td>出版社</td>    
      <td>出版日期</td>    
      <td>价格</td> 
      <td>操作</td>
      <td>操作</td>
      <td>操作</td>

    </tr>   

    <c:forEach items="${bookList}" var="result">
    <tr>    
     <td>${result.ISBN}</td>    
     <td><a href='detail.action?ISBN=${result.ISBN}'>${result.TITLE}</a></td>    
     <td>${result.AUTHORID}</td>    
     <td>${result.PUBLISHER}</td>   
     <td>${result.PUBLISHDATA}</td>    
     <td>${result.PRICE}</td>   
     <td><a href='detail.action?ISBN=${result.ISBN}'>详细信息</a> </td> 
     <td><a href='delete.action?ISBN=${result.ISBN}'>删除此书</a> </td> 
     <td><a href='updata.jsp?ISBN=${result.ISBN}'>更新此书</a> </td>
    </tr>    
    </c:forEach>
 </table>
 <p>
点击返回首页：
<a href="index.jsp">
<img src="返回.jpg" alt="展示图片" width="42" height="42" border="0">
</a>
</p>
</center>
</body>
</html>