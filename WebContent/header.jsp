<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cigniti.airlines.accelerators.Configuration"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="style.css" />
	<title>CompanyName</title>
</head>
<script type="text/javascript"	src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

function validateHeader() {
	return confirm("Warning : Saved data ????");
}

<%
/* Map<String, String> props= new HashMap<String, String>(); */
Configuration configure=new Configuration();
configure.loadPropertiesData();

/* configure.createFolders(); */
%>
</script>
<script src='common.js'></script>

<body>
<div id="top">
  <div id="slogan"><h4>Cigniti Test Accelerator</h4></div>

</div>

<div id="menu">


	<ul>
	
	
		<li><a class='hr' href="ConfigProject.jsp" onclick="return(validateHeader());">Home</a></li>
		<li><a class='hr' href="index.jsp" onclick="return(validateHeader());">ConfigProject</a></li>
		<li><a class='hr' href="scenario.jsp" onclick="return(validateHeader());">Scenarios</a></li>
		<li><a class='hr' href="testdata.jsp" onclick="return(validateHeader());">TestData</a></li>
		<li><a class='hr' href="brules.jsp" onclick="return(validateHeader());">BusinessRules</a></li>
		<li><a class='hr' href="verification.jsp" onclick="return(validateHeader());">Verification Steps</a></li>
		<li><a class='hr' href="Testcases.jsp" onclick="return(validateHeader());">TestCases</a></li>
		
		<li><a href="#" class='saveImg'><img src='http://www.dukeschedulator.com/images/save.png' style='width:15px;height:15px;'/></a></li>
	</ul>
</div>




</body>
</html>