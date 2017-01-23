<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NewUser</title>
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
<script type="text/javascript">
function Validate()
{
	var v=document.forms["form1"]["pass"].value;
	var v1=document.forms["form1"]["cpass"].value;
	if(v!=v1)
		{
		alert("Password and Confirm Password should be same");
		return false;
		}
	else
		{
		return true;
		}
}
</script>
</head>
<body>
<form name="form1" onsubmit="return Validate()" action="Reg" method="post">
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
<div style="margin-left: 30px;margin-top: 2px;" >
<table cellspacing="8px">
<tr><td>Person ID</td><td><input type="text" name="pid"/></td></tr>
<tr><td>Person Name</td><td><input type="text" name="pname"/></td></tr>
<tr><td>Password</td><td><input type="password" name="pass"/></td></tr>
<tr><td>Confirm Password</td><td><input type="text" name="cpass"/></td></tr>
<tr><td>Address</td><td><textarea cols="20" rows="3" name="addr"></textarea></td></tr>
<tr><td>Degree</td><td><select name="degree">
<option></option>
<option>FE</option>
<option>SE</option>
<option>TE</option>
<option>BE</option></select></td></tr>
<tr><td>Branch</td><td><select name="branch">
<option></option>
<option>Information Technology</option>
<option>Computer Science </option>
<option>Electronics</option>
<option>Mechanical</option>
<option>Other</option>
</select></td></tr>
<tr><td>Course Year</td><td><input type="text" name="cyear"/></td></tr>
<tr><td>Roll No</td><td><input type="text" name="rno"/></td></tr>
<!-- 
<tr><td>BookInPossession</td><td><input type="text" name="bip"/></td></tr>
<tr><td>isBookBankAllowed</td><td><input type="radio" name="r" value="Yes"/>Yes<input type="radio" name="r" value="No"/>No</td></tr>
<tr><td>BookBankId</td><td><input type="text" name="bankid"></td></tr> -->
<tr><td>Email ID</td><td><input type="text" name="email"></td></tr>
<tr><td>Mobile Number</td><td><input type="text" name="ph"/></td></tr>
<tr><td>Role</td><td><select name="role">
<option></option>
<option>Student</option>
<option>Teacher</option>
<option>Topper</option>
<option>HOD</option>
<option>Principal</option>
</select></td></tr>
<tr><td>Year Of Admission</td><td><input type="text" name="yoa"/></td></tr>
<tr><td>TransactionID</td><td><input type="text" name="tID" disabled="disabled"/></td></tr>
<tr><td><input type="submit" value="Register"></td><td><input type="reset"></td></tr>
</table>
</div>
</div>
</form>
</body>
</html>