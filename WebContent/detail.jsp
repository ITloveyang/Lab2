<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!-- 引入struts的标签库-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head></head>
<body>
<div style="position:absolute;z-index:-1;width:100%;height:100%;">
    	<img src="4.jpg" width="100%" height="100%" />
</div>
<center>
<h1 style="font-family:verdana;color:green">详细信息</h1>





<table border="1" width="50%"  cellspacing="0" cellpadding="0">
   
	 <c:forEach items="${detailMap}" var="result">
	 <tr>
	 <td>${result.key}:${result.value}</td>
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