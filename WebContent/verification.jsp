<%@ page errorPage="ConfigError.jsp" %> 
<%@page import="com.cigniti.airlines.supportClasses.VerificationStepsSupport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cigniti.airlines.utils.HtmlData"%>
<%@page import="java.util.List"%>
<%@page import="com.cigniti.airlines.supportClasses.ReadExcelSupport"%>
<%@page import="com.cigniti.airlines.supportClasses.WriteExcelSupport"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Verification/Static Steps</title>
<style type="text/css">
table {
	width: 100%;
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

input, td {
	text-align: center;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script src='verification.js'></script>

</head>
<body bgcolor='#ffffff'>
	<%@ include file="header.jsp"%>
	<%@ include file="PopUp.jsp"%>

	<%
		HttpSession session1=request.getSession();
		ReadExcelSupport read = new ReadExcelSupport();
		
		List<HtmlData> excelData1 = new ArrayList();
		excelData1= read.readExcelData(session1);
		Set<String> featuresList = new HashSet();
		featuresList = read.allFeatures;
		featuresList.remove("Feature");
		featuresList.remove("Dummy");
		Set<String> operationList = new HashSet();
		operationList=read.allOperations;
		operationList.add("CLICK");
		operationList.add("SENDTEXT");
		operationList.add("NAVIGATE");
		operationList.add("GETTEXT");
		operationList.add("VERIFYTEXT");
		operationList.add("VERIFYELEMENT");
		operationList.add("SELECT");
		operationList.add("SELECTBYVALUE");
		
		
		//Remove header and footer data from list
		operationList.remove("Operation");
		operationList.remove("Dummy");
		
	%>
<h2  style='color:red; text-align: right; '>Module Name : <%=session1.getAttribute("moduleName") %></h2>
	<form name='addRecord'>
	<input type="hidden" id='automation' name='automation' value="<%=session1.getAttribute("includeAuto") %>" />
		<center>
			<table>
				<tr bgcolor='#3366ff'>
					<th><center>Feature</center></th>
					<th><center>Statement</center></th>
					<th><center>Locator Type</center></th>
					<th><center>Locator Value</center></th>
					<th><center>Operation</center></th>
					<th><center>Text Data</center></th>
				</tr>

	<%
		List<HtmlData> excelData = new ArrayList();
	
		VerificationStepsSupport vs=new VerificationStepsSupport();
		excelData=vs.readExcelData(session1);
		excelData.remove(0);
		
	%>

				<tr bgcolor='#564d47'>
					<td><center>

							<select id="feature">
								<option value="0" selected="selected">-----Select
									Feature-----</option>
								<option value="InitialSteps" >InitialSteps</option>
									
								<%
									for (String featureName : featuresList) {
								%>
								<option value='<%=featureName%>'><%=featureName%></option>

								<%
									}
								%>
							
						</center></td>

					
					<td><center>
							<input type="text" id="statement" placeholder="Statement">
						</center></td>
					<td><center>

							<select id="locatorType" disabled>
								<option value="0" selected="selected">-----LocatorType-----</option>
								<option value="ID">ID</option>
								<option value="NAME">NAME</option>
								<option value="TAGNAME">TAGNAME</option>
								<option value="CLASSNAME">CLASSNAME</option>
								<option value="PARTIALLINKTEXT">PARTIALLINKTEXT</option>
								<option value="XPATH">XPATH</option>
								<option value="CSS">CSS</option>
								<option value='LINKTEXT'>LINKTEXT</option>
								<option value='TESTDATA'>TESTDATA</option>
								<option value='NA'>NA</option>
							</select>
						</center></td>

					<td><center>
							<input type="text" id="locatorValue" placeholder="Locator Value"  disabled>
						</center></td>
					<td><center>
							<input type="button" id="myBtn1" value="+" disabled> 
						<select id="operation" disabled>
						<option value="0" selected="selected">-------Select Operation-------</option>
								<%
									for (String optn : operationList) {
								%>
								<option value='<%=optn%>'><%=optn%></option>

								<%
									}
								%>
								<option value='NA'>NA</option>
						</select>
						</center></td>
					<td><center>
							<input type='checkBox' id='txtdata' disabled/><input type="text" id="textData" placeholder="Text Data">
						</center></td>
				</tr>
			</table>

<input type="button" id="deselect-Category" name='deselectCategory' value='Deselect Category'/>
			<input type="button" id="select-Category" name='selectCategory' value='Select Category'/>
			<button type="button" class="insert-after" name='insertAfter'>Insert After</button>
			<button type="button" class="insert-before" name='insertBefore'>Insert Before</button>
			<button type="button" class="delete-row">Delete Row</button>
			<input type="button" class="add-row" value="Add Row">

		</center>
	</form>
	<center>
		<form id='formData' method="get" action="/TCgenerator/verificationServlet"
			onsubmit="return(validateExport());">
			<table >
				<thead border='1'>
					<tr bgColor='#339966'>
						<th><input type='checkbox' name='selectAll' id='selectAll' /></th>
						<th>Feature<input type='hidden' name='feature' class='s' value="Feature"/></th>
						<th>Statement<input type='hidden' name='statement' class='s' value="Statement"></th>
						<th>Locator Type<input type='hidden' name='locatorType'	class='s' value="Locator Type"></th>
						<th>Locator Value<input type='hidden' name='locatorValue' class='s' value="Locator Value"></th>
						<th>Operation<input type='hidden' name='operation' class='s' value="Operation"></th>
						<th>TextData<input type='hidden' name='textData' class='s' value="TextData"></th>
					</tr>
				</thead>

				<tbody id='tBody1' bgcolor='#a3a3c2'>

					<%
						String pFeatureName = "";
						String pStatement = "";
						if (excelData.size() > 0) {
							for (HtmlData data : excelData) {

								String feature = "";

								if (!data.getFeature().equals("")) {
									pFeatureName = data.getFeature();
									feature = data.getFeature();
								} else {
									feature = pFeatureName;
								}

								String statement = "";
								if (!data.getStatement().equals("")) {
									pStatement = data.getStatement();
									statement = data.getStatement();
								} else {
									statement = pStatement;
								}
								
								
								String locatorType = data.getLocatorType();
								String locatorValue = data.getLocatorValue();
								locatorValue=locatorValue.replace(",", "COMMA")
										.replace("|", "LINE").replace(":", "XCOLON")
										.replace(";", "SCOLON").replace("'", "SQUOTE")
										.replace("\"", "DQUOTE").replace("/", "SSLASH")
										.replace("//", "DSLASH").replace("(", "ROBRACE")
										.replace(")", "RCBRACE").replace("]", "SCBRACE")
										.replace("[", "SOBRACE").replace("=","EQUALTO");
								
								String operation = data.getOperation();
								String textData = data.getTextData(); 
					%>

					 <tr class="<%=feature%>">
						<td width='1%'><input type="checkbox" name="record" class='record'/></td>
						
						<td>
						<input type='hidden' name='feature' class='s' value="<%=feature%>"/><p class="text-info"><%=feature%></p></td>
						
						<td><input type='hidden' name='statement' class='s' value='<%=""+statement+""%>'><p class="text-info"><%=statement%></p></td>
						
						<td><input type='hidden' name='locatorType'	class='s' value="<%=locatorType%>"><p class="text-info"><%=locatorType%></p></td>
						
						<td><input type='hidden' name='locatorValue' class='s' value='<%=locatorValue%>'><p class="text-info"><%=""+data.getLocatorValue()%></p></td>
						
						<td><input type='hidden' name='operation' class='s' value="<%=operation%>"><p class="text-info"><%=operation%></p></td>
						
						<td><input type='hidden' name='textData' class='s' value="<%=textData%>"><p class="text-info"><%=textData%></p></td>
					</tr>

					<%
						}
						}
					%>

 


				</tbody>
			</table>

			<input type='submit' value='Save'>


			<button type="button" class="insert-after" name='insertAfter'>Insert
				After</button>
			<button type="button" class="insert-before" name='insertBefore'>Insert
				Before</button>

			<button type="button" class="delete-row">Delete Row</button>
		</form>



	</center>
</body>
<%@ include file="footer.jsp"%>
</html>