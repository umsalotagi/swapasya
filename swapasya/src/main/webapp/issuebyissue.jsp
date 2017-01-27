<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue/Return</title>
<script type="text/javascript">
function Validate()
{
	if(document.form.sid.value=="")
		{
		alert("Enter Student ID");
		document.form.sid.focus();
		return false;
		}
	else
		{
		if(document.form.bid.value=="")
		{
		alert("Enter Book ID");
		document.form.bid.focus();
		return false;
		}
		
		return true;
		
		}
	
	}
</script>
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
<form name="form" onsubmit="return Validate()" action="performissuereturn.jsp">
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
<div style="margin-left: 50px;margin-top: 50px;">
<div style="width: 960px; margin: 0 auto;">
<li style="margin-left:0px;border: 1px solid;
	background-color: orange;
	width: 30px;
	height: 20px;
	padding: 10px;
	
	display: inline;
	"><a href="Hello.html">IssueByCategory</a></li>
<li style="margin-left:0px;border: 1px solid;
	background-color: orange;
	width: 30px;
	height: 20px;
	padding: 10px;
	
	display: inline;
	"><a href="issuebyissue.jsp"  rel="noopener noreferrer" target="_blank">IssueByIssue</a></li>
<li style="margin-left:0px;border: 1px solid;
	background-color: orange;
	width: 30px;
	height: 20px;
	padding: 10px;
	
	display: inline;
	"><a href="Hello.html">IssueByRandom</a></li>
 
	</div></br></br></br>
<table cellspacing="10px">
<tr><td>Person ID *</td><td><input type="text" name="pid"/></td></tr>
<tr><td>Book ID *</td><td><select name="bid">
<option>Select</option>
<option>B001</option>
</select></td>
<tr><td>Select Date</td><td><input type="date" name="dt" value="1/12/2017"></td></tr>
<tr><td>Is Book Bank Allowed</td><td><input type="radio" name="booktype" value="bookbank"/></td></tr>
<tr><td><input type="submit" value="Issue" name="libop"/></td><td><input type="submit" value="Return" name="libop"/></td></tr>
</table>
</div>
</div>

</form>
</body>
</html>