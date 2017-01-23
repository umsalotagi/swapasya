<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<style type="text/css">
#content
{
	
	width: 1200px;
	left:500px;
	height: 600px;
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
<form name="form" onsubmit="return Validate()">
<div id="nav">
<ul>
<li><a href="Login.jsp">Login</a></li>
<li><a href="">My Profile</a></li>
<li><a href="issuereturn.jsp">Issue/Return</a></li>
<li><a href="">Book Info</a></li>
<li><a href="">Student Info</a></li>
<li><a href="catalogue.jsp">Catalogue</a></li>
<li><a href="admin.jsp">Admin</a></li>
</ul>
</div>
<div id="content">
<div style="margin-left: 100px;margin-top: 50px;width: 50%;float:none;">
<table>
<tr><th colspan="2"><h1>Librarian Admin</h1></th></tr>
<tr><td>Library Name</td><td><input type="text" name="libnm"/></td></tr>
<tr><td>Library ID</td><td><input type="text" name="libid"/></td></tr>
<tr><td>Address</td><td><input type="text" name="addr"/></td></tr>
<tr><td>Purchase Date</td><td><input type="text" name="pdate"/></td></tr>
<tr><td>Phone</td><td><input type="text" name="ph"/></td></tr>
<tr><td>Email</td><td><input type="text" name="email"/></td></tr>
<tr><td><input type="submit" value="Issue" name="issue"/></td><td><input type="submit" value="Return" name="return"/></td></tr>
</table>
</div>

<div style="float:left; width: 50%; margin-top: 10px;margin-left: 100px">
<input type="submit"  value="Edit Rules">
<h2>Role Wise Rules</h2>
<table border="2" style="color: orange; ">
<tr><th>Role</th><th>Book limit</th><th>Day limit</th><th>Fine per day</th><th>Permissions</th></tr>
<tr><td>Student</td><td>2</td><td>7</td><td>10</td><td>Issue,Catalogue</td></tr>
<tr><td>Teacher</td><td>5</td><td>14</td><td>00</td><td>Issue,Catalogue,Student Info Read</td></tr>
</table>
</div>
</div>

</form>
</body>
</html>