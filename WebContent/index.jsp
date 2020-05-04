<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
table {
	width: 50%;
	margin: 20px 0;
	border-collapse: collapse;
	font-size: 20px;
}
table, th, td {
	border: 1px solid black;
}

table th, table td {
	padding: 5px;
	text-align: left;
}

input, td {
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br><br><br>

<center>

<%HttpSession session1=request.getSession(); %>
<h2  style='color:red; text-align: right; '>Module Name : <%=session1.getAttribute("moduleName") %></h2>	
<form id='formData' method="get" action="/TCgenerator/executeServlet" >
			<table >
				<thead border='1'>
					<tr bgColor='#339966'>
						<th><center>Configurations</center></th>
					</tr>
				</thead>

				<tbody id='tBody1' bgcolor='#a3a3c2'>
					<tr>
						<td><a href='scenario.jsp'>Click Here</a>  to configure Scenario data.</td>
					</tr>
					<tr>
						<td><a href='testdata.jsp'>Click Here</a>  to configure Test data.</td>
					</tr>
					<tr>
						<td><a href='brules.jsp'>Click Here</a> to configure Business Rules.</td>
					</tr>
					<tr>
						<td><a href='verification.jsp'>Click Here</a>  to configure verification steps.</td>
					</tr>
					<tr>
						<td><a href='Testcases.jsp'>Click Here</a>  to view testcases</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<input type='submit' value='Execute'>
</center>
</form>

<br><br><br><br>
</body>
<%@ include file="footer.jsp"%>
</html>
