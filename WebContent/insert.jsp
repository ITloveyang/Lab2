<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入struts的标签库-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
</head>
<body>
<div style="position:absolute;z-index:-1;width:100%;height:100%;">
    	<img src="3.jpg" width="100%" height="100%" />
</div>
<center>
<p><font size="5" face="arial" color="#FFFF00">
<s:form action="insert" method="post">
        <s:textfield name="ISBN" key="要添加的书ISBN值："></s:textfield>
        <s:textfield name="authorID" key="作者ID"></s:textfield>
        <s:textfield name="publishdate" key="出版日期"></s:textfield>
        <s:textfield name="publisher" key="出版社 "></s:textfield>
        <s:textfield name="price" key="价格"></s:textfield>
        <s:textfield name="Title" key="题目"></s:textfield>
        <s:textfield name="authorNAME" key="姓名"></s:textfield>
        <s:textfield name="Country" key="国家"></s:textfield>
        <s:textfield name="Age" key="年龄"></s:textfield>

        <s:submit key="更新" value="添加"></s:submit>
    </s:form>
    </font></p>
    <p>
点击返回首页：
<a href="index.jsp">
<img src="返回.jpg" alt="展示图片" width="42" height="42" border="0">
</a>
</p>
    </center>
</body>
</html>