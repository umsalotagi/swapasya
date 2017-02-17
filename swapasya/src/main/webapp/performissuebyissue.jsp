<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.swapasya.core.GeneralFinal,java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--<%!int i=0; 
<%
String libop=request.getParameter("libop");
if("Issue".equals(libop))
{
GeneralFinal gf=new GeneralFinal("MH001");
String pid=request.getParameter("pid");
String bid=request.getParameter("bid");
String l=request.getParameter("dt");//2017-01-26 want to do 26-10-2017
/* System.out.println("From html="+l);
StringBuffer sb=new StringBuffer(l);
char c[]=new char[2];
char c1[]=new char[4];
//2017
sb.getChars(0, l.indexOf("-"), c1, 0);
System.out.println("2017="+new String(c1));
//26
sb.getChars(l.lastIndexOf("-")+1, l.length(), c, 0);
System.out.println("26="+new String(c));
sb.replace(l.lastIndexOf("-")+1, sb.length(), new String(c1));
sb.replace(0, l.indexOf("-"), new String(c));

System.out.println("My Conversion="+sb); */


SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
Date d=fmt.parse(l);
String s=fmt.format(d);

System.out.println(d);
System.out.println("In String form="+s);


i=gf.issueBookByIssueType(pid, bid, d,request.getParameter("bookbank"));
if(i==0)
{

%>
Issued Successfully...
<jsp:include page="issuebyissue.jsp"></jsp:include>
<%
}
else{
	out.println("Book limit crossed");
}
}%>--%>
</body>
</html>