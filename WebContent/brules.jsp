<%@ page errorPage="ConfigError.jsp" %> 
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.cigniti.airlines.supportClasses.BusinessRulesSupport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cigniti.airlines.utils.HtmlData"%>
<%@page import="java.util.List"%>
<%@page import="com.cigniti.airlines.supportClasses.ReadExcelSupport"%>
<%@page import="com.cigniti.airlines.supportClasses.WriteExcelSupport"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Business Rules</title>
<style type="text/css">

.myForm{
background-color:#9cb0d1;
width: 80%;
}

.display{
background-color:#a3a3c2;
width: 90%;
}
table {
	width: 90%;
	margin: 20px 0;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

table th, table td {
	padding: 5px;
	text-align: left;
	;
}
b{
font-size: 18px;
color: #ffffff
}

p{
font-size: 15px;

}
input, td {
	text-align: center;
}
</style>


<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>


<SCRIPT language="javascript">

		function validateForm1()
		{
		var featureInput = document.getElementsByName('feature[]');
		var pvInput = document.getElementsByName('possibleValue[]');
		
		for (i=0; i<featureInput.length; i++)
			{
			 if (featureInput[i].value == 0)
				{
			 	 alert('Complete all the Feature fields');		
			 	 return false;
				}
			}
		
		for (i=0; i<pvInput.length; i++)
		{
		 if (pvInput[i].value == 0)
			{
		 	 alert('Complete all the Possible Values');		
		 	 return false;
			}
		}
		
		
	}
		
	
function addRow(tableID) {

	var table = document.getElementById(tableID);

	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var colCount = table.rows[0].cells.length;

	for(var i=0; i<colCount; i++) {

		var newcell	= row.insertCell(i);

		newcell.innerHTML = table.rows[0].cells[i].innerHTML;
		//alert(newcell.childNodes);
		switch(newcell.childNodes[0].type) {
			case "text":
					newcell.childNodes[0].value = "";
					break;
			case "checkbox":
					newcell.childNodes[0].checked = false;
					break;
			case "select-one":
					newcell.childNodes[0].selectedIndex = 0;
					break;
		}
	}
}

function deleteRow(tableID) {
	try {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;

	for(var i=0; i<rowCount; i++) {
		var row = table.rows[i];
		var chkbox = row.cells[0].childNodes[0];
		if(null != chkbox && true == chkbox.checked) {
			if(rowCount <= 1) {
				/* alert("Cannot delete all the rows."); */
				break;
			}
			table.deleteRow(i);
			rowCount--;
			i--;
		}
	}
	}catch(e) {
		alert(e);
	}
}

	$(document).ready(function() {
		
		$('.record').click(function() {
			var row = $(this).parents("tr");
			if ($(this).is(":checked")) {
				row.css('background-color', '#8378D9');
			} else {
				row.css('background-color', '#a3a3c2');
			}

		});
		
				$("#selectAll").click(function() 
					{
							$("input:checkbox.record").prop('checked',	$(this).prop("checked"));
					});
				
				//Delete selected row
				$(".delete-row")
				.click(
						function() {
							
							var res = confirm("Click 'OK' to delete record");
							if (res == true) {
								$("table tbody").find('input[class="record"]').each(
												function() {
													if ($(this).is(":checked")) {
														$(this).parents("tr").remove();
														$(".saveImg").show();
													}
												});
							}
						});
				
				
	});
	
	
</SCRIPT>
<%
	HttpSession session1=request.getSession();
		ReadExcelSupport read = new ReadExcelSupport();
		List<HtmlData> excelData = new ArrayList();
		excelData = read.readExcelData(session1);
		Set<String> featuresList = new HashSet();
		Set<String> pvList = new HashSet();
		
		featuresList = read.allFeatures;
		featuresList.remove("Feature");
		featuresList.remove("Dummy");
		pvList=read.allPossibleValues;
		pvList.remove("Possible Value");
		pvList.remove("Dummy");
		if (featuresList.size() > 0) {
			System.out.println(featuresList);
		}
	%>

</HEAD>

<BODY bgColor='#ffffff'>

	<%@ include file="header.jsp" %>
	<center>
	

	
<h2  style='color:red; text-align: right; '>Module Name : <%=session1.getAttribute("moduleName") %></h2>	
<form id='dataForm' method='get' action='/TCgenerator/businessRulesServlet' name='dataForm' onsubmit="return validateForm1()">
	
<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />

<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />

<TABLE id="header" width="350px" border="1" bgcolor='#3366ff'>
<TR><TD width='10%'><INPUT type="checkbox" name="chk" /></TD><td width='45%'><B>Feature Name</B></td><td width='45%'><B>Possible Value</B></td></TR>
</TABLE>

<TABLE id="dataTable" width="350px" border="1">

<tbody bgcolor='#a3a3c2'>
<TR>
			<TD width='10%'><INPUT type="checkbox" name="chk" class='rule'/></TD>
			<TD width='45%'><SELECT name="feature[]" >
					<option value="0" selected="selected">-----Select Feature-----</option>
					<%
						for (String featureName : featuresList) {
					%>
					<option value='<%=featureName%>'><%=featureName%></option>

					<%
						}
					%>
			</SELECT></TD>
			<TD width='45%'><SELECT name="possibleValue[]" >
					<option value="0" selected="selected">-----Select Possible Value-----</option>
					<%
						for (String pv : pvList) {
					%>
					<option value='<%=pv%>'><%=pv%></option>

					<%
						}
					%>
			</SELECT></TD>
		</TR>
		</tbody>
</TABLE><br>
<input type='submit' value='Add Rule'/>
<br><br>
</form>



<%
BusinessRulesSupport brs=new BusinessRulesSupport();
Map<Integer, List<String>> rules=new LinkedHashMap();
rules=brs.readBusinessRules(session1);

int rulesCount=rules.size();
System.out.println("rulesCount"+rulesCount);
%>
<br><br>
<form class='display' method='post' action='/TCgenerator/businessRulesServlet'>
<br>
<INPUT type="button" value="Delete Rule"  class='delete-row' />
<table id='displayTable'>
<tr bgcolor='#339966'>
<td><input type='checkbox' name='selectAll' id='selectAll' /></td>
<td>SNo</td>
<td><b>R1</b></td>
<td><b>R2</b></td>
<td><b>R3</b></td>
<td><b>R4</b></td>
<td><b>R5</b></td>
<td><b>R6</b></td>
<td><b>R7</b></td>
<td><b>R8</b></td>
<td><b>R9</b></td>
<td><b>R10</b></td>
</tr>
<%

for(int i=1;i<=rulesCount-1;i++)
{
%>
<tr bgcolor='#a3a3c2'>
<td><input type='checkbox' name='rule' class='record'/></td>
<td><p class='R0'><%=i%></p></td>
	
<%
int count=0;
Object[] li=rules.get(i).toArray();


for(int j=0;j<10;j++)
{	count++;
	if(j<li.length)
	{%>
		<td><input type='hidden' class='<%="R"+count%>' value='<%=li[j].toString() %>' name='<%="R"+count%>'/><p class='pTag'><%=li[j].toString() %></p></td>
	<%}
	else
	{%>
		<td><input type='hidden' class='<%="R"+count%>' value="" name='<%="R"+count%>'/><p class='<%="R"+count%>'>-NA-</p></td>
	<%}
	
}

%>
</tr>
<%}%>

<tr>
<td></td>
<td><input type='hidden' name='R0' value='Dummy'/></td>
<td><input type='hidden' name='R1' value='Dummy'/></td>
<td><input type='hidden' name='R2' value='Dummy'/></td>
<td><input type='hidden' name='R3' value='Dummy'/></td>
<td><input type='hidden' name='R4' value='Dummy'/></td>
<td><input type='hidden' name='R5' value='Dummy'/></td>
<td><input type='hidden' name='R6' value='Dummy'/></td>
<td><input type='hidden' name='R7' value='Dummy'/></td>
<td><input type='hidden' name='R8' value='Dummy'/></td>
<td><input type='hidden' name='R9' value='Dummy'/></td>
<td><input type='hidden' name='R10' value='Dummy'/></td>

</tr>

</table>
<input type='submit' value='Save'/> 

</form>
</center>
	
</BODY>
<%@ include file="footer.jsp"%>
</html>