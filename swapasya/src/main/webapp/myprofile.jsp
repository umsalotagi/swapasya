<%@page import="com.swapasya.dataTypes.PersonProp"%>
<%@page import="com.swapasya.dataTypes.BookProp"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.swapasya.core.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
<style type="text/css">
#content
{
	
	width: 1200px;
	left:500px;
	height: 500px;
	background-color: gray;
	border: 1px solid black;
	
}
#nav
{
 width: 960px; margin: 0 auto;
}
#nav ul li
{

margin-left:0px;
	border: 1px solid;
	background-color: orange;
	width: 30px;
	height: 20px;
	padding: 10px;
	
	display: inline;
	
}
#nav ul{margin:0;padding: 0;list-style-type: none;}
#nav ul li a{background: :#fbe49b;text-decoration:none;padding:10px 10px 6px 10px;text-transform:uppercase;border-left:1px solid #e4bc3f;border-top: 1px solid #e4bc3f; border-right: 1px solid #e4bc3f; background-color: #fbe49b}
 
</style>
</head>
<body>
<%! String pid="18117"; %>
<form name="form" onsubmit="return Validate()">
<div id="nav">
<ul>
<li><a href="Login.jsp">Login</a></li>
<li><a href="myprofile.jsp">My Profile</a></li>
<li><a href="issuereturn.jsp">Issue/Return</a></li>
<li><a href="">Book Info</a></li>
<li><a href="">Student Info</a></li>
<li><a href="catalogue.jsp">Catalogue</a></li>
<li><a href="admin.jsp">Admin</a></li>
</ul>

</div>
<div id="content">
<div style="margin-left: 850px;margin-top: 10px;">
<%
Read r=new Read("MH001");
Entity e=r.getPerson(pid);
out.println(e.getProperty(PersonProp.personID));
out.println(e.getProperty(PersonProp.personName)+"</br>");
out.println(e.getProperty(PersonProp.inPossession)+"</br>");


%>
<input type="submit" value="Edit">
</div>
</div>

</form>
</body>
</html>
