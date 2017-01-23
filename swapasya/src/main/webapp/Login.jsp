<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script type="text/javascript">
function Validate()
{
	if(document.form.unm.value=="")
		{
		alert("Enter Username");
		document.form.unm.focus();
		return false;
		}
	else
		{
		if(document.form.pass.value=="")
		{
		alert("Enter Password");
		document.form.pass.focus();
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
<table>
<tr><th align="center" colspan="2"><h2>Login</h2></th></tr>
<tr><td>Username</td><td><input type="text" name="unm" id="unm"/></td></tr>
<tr><td>Password</td><td><input type="password" name="pass"/></td></tr>
<tr><td>User</td><td><select><option>Student</option>
<option>Clerk</option>
<option>Librarian</option>
<option>Admin</option>
</select></td></tr>
<tr><td><input type="submit" value="Login"/></td><td><a href="NewUser.jsp">New User</a></td></tr>
</table>
</div>
</div>

</form>
</body>
</html>