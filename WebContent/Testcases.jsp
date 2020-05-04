<%@page import="com.cigniti.airlines.accelerators.ReadTestCases"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generated Test Cases</title>
<style type="text/css">
p {
	
	font-size: 16px;
	font-weight:bold;
	text-align:left;
	padding-right:30px;
}

</style>
</head>
<%@ include file="header.jsp" %>
<body>
<center>
<%HttpSession session1=request.getSession(); %>
<h2  style='color:red; text-align: right; '>Module Name : <%=session1.getAttribute("moduleName") %></h2>
<br><br><br>
<table width="60%" border='1'>
<tr bgColor='#339966'><td><p>TC Count</p></td><td><p>Test Cases<p></td></tr>

<%

ReadTestCases read=new ReadTestCases();
Map<Integer,String> readdata = new HashMap<>();
readdata=read.readData(session1);
int tcCount=readdata.size();
for(int i=1;i<=tcCount;i++){
	String[] s=readdata.get(i).split("\n");
%>
<tr bgcolor='#a3a3c2' ><td><p class="text-info"><%=i %></p></td><td>

<% for(int j=0;j<s.length;j++){%>
<p class="text-info"><%=s[j]%></p>
<%} %>

</td></tr>
<%} %>
</table>
</center>
</body>
</html>