<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cigniti.airlines.supportClasses.PropertiesSupport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateDeleteModule() {
	return confirm("Click 'OK' to Delete Module'");
}

function validateForm() {
	var selectModule = document.forms["selectModuleForm"]["selectModule"].value;
	

	if (selectModule == 0) {
		alert("Module Name Cannot be Empty");
		return false;
	}

	 else {
		return confirm("Click 'OK' to Select Module or Click 'Cancel' to exit'")

	}
}

function validateCreateModuleForm() {
	var moduleName = document.forms["newModuleForm"]["moduleName"].value;
	

	if (moduleName == "") {
		alert("Module Name Cannot be Empty");
		return false;
	}

	 else {
		return confirm("Click 'OK' to Select Module or Click 'Cancel' to exit'")

	}
}

function validateDeleteModuleForm() {
	var moduleName = document.forms["deleteModuleForm"]["deleteModuleName"].value;
	
	if (moduleName == 0) {
		alert("Select Module Name to be Deleted !!!!!!");
		return false;
	}

	 else {
		return confirm("Click 'OK' to Delete Module or Click 'Cancel' to exit'")

	}
}

</script>
<style type="text/css">


#deleteModuleForm{
background-color:#82b9e5 ;
width: 70%;
color:#00000;
}
.warning{
color:red;
font-size:20px;
}

#newModuleForm{
background-color:#5c85d6 ;
width: 70%;
color:#ffffff;
}

#selectModuleForm{
background-color: #9cb0d1;
width: 70%;
color:#00000;
}
h2 {
    text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configure Project</title>
<script type="text/javascript"	src="http://code.jquery.com/jquery.min.js"></script>
<script src='common.js'></script>

<%@ include file="header.jsp"%>
</head>

<body>
<br><br><br>
<center>
<button id='createNewModuleBtn'>New Module</button>
<button id='selectNewModuleBtn'>Select Module</button>
<button id='deleteModuleBtn'>Delete Module</button>
<br><br>



<br><br>

<form id='newModuleForm'  method='get' action="/TCgenerator/createModuleServlet"  onSubmit="return(validateCreateModuleForm());">

<table id='newModuleTable' width='100%'>
<!-- <tr><td><h2><center>Browse Module Location :</center> </h2></td><td> <input type="file" size='25'></td></tr> -->
<!-- <tr><td><h2>Enter Module Location : </h2></td><td> <input type="text" name="modulePath" class='modulePath' size='40'></td></tr> -->
<!-- <input type="hidden" name="modulePath" class='modulePath' size='40' value="C:\\ABC\\TCgenerator\\SW_TC_TS_Generator"> -->
<input type="hidden" name="modulePath" class='modulePath' size='40' value="SW_TC_TS_Generator">
<tr><td><h2>Enter Module Name : </h2></td><td> <input type="text" id='moduleName' name="moduleName" class='moduleName' size='40'></td></tr>
</table>
<input type='submit' value='create'/>
<br><br>
</form>
<%

Map<String,String> props=new LinkedHashMap<String,String>();
PropertiesSupport propSupport =new PropertiesSupport();
props=propSupport.readFile("Module"); 
Set<String> moduleList=new LinkedHashSet();
moduleList=props.keySet();
%>

<form id='selectModuleForm'  method='get' action="/TCgenerator/selectModuleServlet" width='50%' onsubmit="return(validateForm());">
<table id='newModuleTable' width='100%'>
<tr><td><h2>Select Module : </h2></td><td>
<select name="moduleData" id='selectModule' style="width: 200px" >
<option value="0" selected="selected">--------- Select Module --------</option>

<%for(String moduleName:moduleList) 
{
	String modulepath=props.get(moduleName);
%>
<option value="<%=modulepath+"##"+moduleName%>" ><%=moduleName%></option>

<%}%>


</select>
</td></tr>

<tr><td><h2>Generate TestCase : </h2></td><td>
<select name="generateTC"  style="width: 200px">
<option value="FALSE" selected="selected">------------  FALSE  ------------</option>
<option value="TRUE" >------------TRUE------------</option>
</select>
</td></tr>

<tr><td><h2>Generate TestScript : </h2></td><td> 
<select  name="includeAuto" style="width: 200px;">
<option value="FALSE" selected="selected">------------FALSE------------</option>
<option value="TRUE" >------------TRUE------------</option>
</select>
</td></tr>

<tr><td><h2>Execute TestScript : </h2></td><td> 
<select  name="generateTS" style='width: 200px;'>
<option value="FALSE" selected="selected">------------FALSE------------</option>
<option value="TRUE" >------------TRUE------------</option>
</select>
</td></tr>

</table>
<br><br>
<input type='submit' value='Select'/>
<br><br>
</form>

<form id='deleteModuleForm'  method='get' action="/TCgenerator/deleteModuleServlet" width='50%' onsubmit="return(validateDeleteModuleForm());">
<br><br><br>
<table id='deleteModuleTable' width='100%'>
<tr><td><h2>Select Module To Delete : </h2></td><td>
<select name="moduleData" id='deleteModuleName'style="width: 200px" >
<option value="0" selected="selected">--------- Select Module --------</option>



<%for(String moduleName:moduleList) 
{
	String modulepath=props.get(moduleName);
%>
<option value="<%=modulepath+"##"+moduleName%>"><%=moduleName%></option>

<%}%>


</select>
</td></tr>

</table>
<br><br><br>
<input type='submit' value='Delete' id='deleteModule' />
<br><br><br><br><br>
</form>
<%if(request.getParameter("message")!=null&&request.getParameter("message")!=""){ %>
<p class='message'><%=request.getParameter("message")%></p>
<%} %>
</center>
<br><br><br><br><br><br>
</body>
<%@ include file="footer.jsp"%>
</html>