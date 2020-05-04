<%@page import="com.cigniti.airlines.supportClasses.PropertiesSupport"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configuration</title>

<style type="text/css">
table {
	width: 80%;
	margin: 20px 0;
	border-collapse: collapse;
	
}

table, th, td {
	border: 1px solid black;
	font-size: 12px;
}
b{
	align :center;
	font-size: 18px;
}
p{
	
	font-size: 18px;
	color: #000099;
	font-style: italic;
}
center{
	
	font-size: 22px;
	
}

table th, table td {
	padding: 0px;
	text-align: left;
	;
}

input, td {
	text-align: center;
	width: 300px;
}
</style>


</head>
<body bgcolor='#ffffff'>
<%@ include file="header.jsp" %>
<form id='config' action='/TCgenerator/propertiesServlet' method='get'>
<center>


<%
PropertiesSupport ps=new PropertiesSupport();
Map<String,String> props=new LinkedHashMap<String,String>();
props=ps.readFile("TestData");
%>
<br><br>
<a href="C:\\Users\\E000838\\Downloads\\TestData.xlsx">Click Here for Excel Sheet</a>
<table border=1 width='80%'>
<tr bgColor='#339966'>
<td><b><center>Name</center><b></td>
<td><center><b>Value</b></center></td>
</tr>
<tbody bgcolor='#a3a3c2'>
<tr>
<td><p># Name of test data sheet</p><br><b>readFile<b></b><input type='hidden' name='readFile' value='readFile'/></td>
<td><p>Enter Name of test data sheet (Ex :C:\\Airlines\\TestData.xlsx):</p><br><input type='text' name='readFile' id='' value='<%=props.get("readFile")%>'/></td>
</tr>


<tr>
<td><p># Minimum number of passengers</p><br><b>minPaxCount</b><input type='hidden' name='minPaxCount' value='minPaxCount'/></td>
<td><p>Enter Minimum passengers count (Ex : 1):</p><br><input type='text' name='minPaxCount' id='minPaxCount' value='<%=props.get("minPaxCount")%>'/></td>
</tr>


<tr>
<td><p># Maximum number of passengers that are possible</p><br><b>maxPaxCount</b><input type='hidden' name='maxPaxCount' value='maxPaxCount'/></td>
<td><p>Enter maximum passengers count (Ex: 8):</p><br><input type='text' name='maxPaxCount' id='maxPaxCount' value='<%=props.get("maxPaxCount")%>'/></td>
</tr>

<tr>
<td><p>#Directory where generated test cases are placed</p><br><b>testCasesPath</b><input type='hidden' name='testCasesPath' value='testCasesPath'/></td>
<td><p>Enter testcases path (Ex :C:\\Airlines\\TestCases): </p><br><input type='text' name='testCasesPath' id='testCasesPath' value='<%=props.get("testCasesPath")%>'/></td>
</tr>

<tr>
<td><p>#Directory where generated Results are placed</p><br><b>resultsPath</b><input type='hidden' name='resultsPath' value='resultsPath'/></td>
<td><p>Enter results path (Ex:C:\\Airlines\\Results): </p><br><input type='text' name='resultsPath' id='resultsPath' value='<%=props.get("resultsPath")%>'/></td>
</tr>

<tr>
<td><p>#Sheet name in test data sheet where business rules are configured</p><br><b>businessRulesSheetName</b><input type='hidden' name='businessRulesSheetName' value='businessRulesSheetName'/></td>
<td><p>Enter business rules sheet name (Ex : Brules): </p><br><input type='text' name='businessRulesSheetName' id='businessRulesSheetName' value='<%=props.get("businessRulesSheetName")%>'/></td>
</tr>

<tr>
<td><p>#sheet names for which test cases and test scripts need to be generated</p><br><b>sheetNames</b><input type='hidden' name='sheetNames' value='sheetNames'/></td>
<td><p>Enter Main Sheet Name (Ex : Booking):</p><br><input type='text' name='sheetNames' id='sheetNames' value='<%=props.get("sheetNames")%>'/></td>
</tr>

<tr>
<td><p>#Application URL</p><br><b>url</b><input type='hidden' name='url' value='url'/></td>
<td><p>Enter Application url (Ex: www.southwest.com)</p><br><input type='text' name='url' id='url' value='<%=props.get("url")%>'/></td>
</tr>


<tr>
<td><p>#Configure with TRUE or FALSE to generate test case</p><br><b>generateTestCases</b><input type='hidden' name='generateTestCases' value='generateTestCases'/></td>
<td><p>Select True to generate test case :</p><br><select name='generateTestCases' id='generateTestCases'>
					<option value='<%=props.get("generateTestCases")%>'><%=props.get("generateTestCases")%></option>
					<option value='TRUE'>TRUE</option>
					<option value='FALSE'>FALSE</option>
					</select></td>
</tr>


<tr>
<td><p>#Configure with TRUE or FALSE to run test script</p><br><b>generateTestScript</b><input type='hidden' name='generateTestScript' value='generateTestScript'/></td>
<td><p>Select Tue to execute test script :</p><br><select name='generateTestScript' id='generateTestScript'>
					<option value='<%=props.get("generateTestCases")%>'><%=props.get("generateTestCases")%></option>
					<option value='TRUE'>TRUE</option>
					<option value='FALSE'>FALSE</option>
					</select></td>
</tr>

<tr>
<td><p>#Directory to store screen shots and summary report</p><br><b>reportsDirectory</b><input type='hidden' name='reportsDirectory' value='reportsDirectory'/></td>
<td><p>Enter path to copy failed screen shots :</p> <br><input type='text' name='reportsDirectory' id='reportsDirectory' value='<%=props.get("reportsDirectory")%>'/></td>
</tr>

<tr>
<td><p># Sheet name where verification/static steps are written</p><br><b>verificationSteps</b><input type='hidden' name='verificationSteps' value='verificationSteps'/></td>
<td><p>Enter sheet name where verification steps are written :</p><br><input type='text' name='verificationSteps' id='verificationSteps' value='<%=props.get("verificationSteps")%>'/></td>
</tr>
</tbody>

</table>

<input type='submit' value='Save'/>

</center>



</form>



</body>
<%@ include file="footer.jsp"%>
</html>