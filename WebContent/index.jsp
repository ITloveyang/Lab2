<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入struts的标签库-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
<style>
			body {
				margin: 0 auto;
				width: 100%;
				font: 100% Georgia, "Times New Roman", Times, serif;
			}
			h2 {
				color:red;
				font-family:verdana;
			}
			h1 {
				
				font-family:verdana;
			}
		</style>
</head>
<body>
<div style="position:absolute;z-index:-1;width:100%;height:100%;">
    	<img src="3.jpg" width="100%" height="100%" />
</div>
<p>
<img src="1.jpg" alt="Smiley face" style="float:left" width="32" height="32">
</p>
<center>
<br>
<br>
<h1>
        <s:form action="/queryAction" method="post">
        <s:textfield name="authorNAME" key="作者名字"></s:textfield>
        <s:submit key="查询" value="查询"></s:submit>
    </s:form>
    </h1>
    <h2><a href='insert.jsp'>**添加新书**</a></h2>
    </center>





<center>


<p style="color:#FFFF00">放松一下，点击太阳或其他行星，注意变化哦：</p>

<img src="planets.gif" width="145" height="126" alt="Planets" usemap="#planetmap">

<map name="planetmap">
  <area shape="rect" coords="0,0,82,126" target="_blank" alt="Sun" href="sun.gif">
  <area shape="circle" coords="90,58,3" target="_blank" alt="Mercury" href="merglobe.gif">
  <area shape="circle" coords="124,58,8" target="_blank" alt="Venus" href="venglobe.gif">
</map>
</center>
<p>
<img src="1.jpg" alt="Smiley face" style="float:right" width="32" height="32">
</p>
</body>
</html>