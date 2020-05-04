<%@ page errorPage="ConfigError.jsp" %> 
<%@page import="com.cigniti.airlines.supportClasses.TestDataSupport"%>
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
<title>Test Data </title>
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
<%@ include file="PopUp.jsp"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
	<script src="scenario.js"></script>

<script src='testData.js'></script>

</head>
<body bgcolor='#ffffff'>
	<%@ include file="header.jsp"%>

	<%
	HttpSession session1=request.getSession();
		TestDataSupport tdSupport = new TestDataSupport();
		List<HtmlData> excelData = new ArrayList();
		
		excelData = tdSupport.readExcelData(session1);
		
		//Remove header row
			excelData.remove(0);
				
		//Remove footer row
			excelData.remove(excelData.size()-1);
				
		Set<String> featuresList = new HashSet();
		featuresList = tdSupport.allFeatures;
		featuresList.remove("Feature");
		featuresList.remove("Dummy");
		
		
		Set<String> operationList = new HashSet();
		operationList=tdSupport.allOperations;
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
		<center>
		<input type="hidden" id='automation' name='automation' value="<%=session1.getAttribute("includeAuto") %>" />
			<table>
				<tr bgcolor='#3366ff'>
					<th><center>Feature</center></th>
					<!-- <th><center>Variable Name</center></th>
					<th><center>Statement</center></th> -->
					<th><center>Locator Type</center></th>
					<th><center>Locator Value</center></th>
					<th><center>Operation</center></th>
					<th><center>Text Data</center></th>
				</tr>

				<tr bgcolor='#564d47'>
					<td><center>

						<input type="button" id="myBtn5" value="+">


							<select id="feature" >
								<option value="0" selected="selected">-----Select
									Feature-----</option>
									
								<%
									for (String featureName : featuresList) {
								%>
								<option value='<%=featureName%>'><%=featureName%></option>

								<%
									}
								%>
							</select>
						</center></td>

					<!-- <td><center>
							<input type="text" id="possibleValue"
								placeholder="Variable Name" >
						</center></td> -->
					<!-- <td><center>
							<input type="text" id="statement" placeholder="Statement" >
						</center></td> -->
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
								<option value="NA">NA</option>
							</select>
						</center></td>

					<td><center>
							<input type="text" id="locatorValue" placeholder="Locator Value" disabled>
						</center></td>
					<td><center>
							<center>
						<input type="button" id="myBtn1" value="+" disabled> 
						<select id="operation" disabled>
						<option value="0" selected="selected" >-------Select Operation-------</option>
								<%
									for (String optn : operationList) {
								%>
								<option value='<%=optn%>'><%=optn%></option>

								<%
									}
								%>
								<option value="NA"  >NA</option>
						</select>
					</center>
						</center></td>
					<td><center>
							<input type='checkBox' id='txtdata'/><input type="text" id="textData" placeholder="Text Data">
						</center></td>
				</tr>
			</table>

				<button type="button" id="deselect-Category" name='deSelectCategory'>DeSelect Category</button>
				<button type="button" id="select-Category" name='selectCategory'>Select Category</button>
				<button type="button" class="insert-after1" name='insertAfter'>Insert After</button>
			<button type="button" class="insert-before1" name='insertBefore'>Insert	Before</button>

			<button type="button" class="delete-row">Delete Row</button>
			<input type="button" class="add-row1" id='add-row1' value="Add Row">
		</center>
	</form>
	<center>
		<form id='formData' method="post" action="/TCgenerator/testDataServlet"
			onsubmit="return(validateExport());">
			<table >
				<thead border='1'>
					<tr bgColor='#339966'>
						<th><input type='checkbox' name='selectAll' id='selectAll' /></th>
						<th>Feature<input type='hidden' name='feature'  value='Feature'/></th>
						<!-- <th>Variable Name<input type='hidden' name='possibleValue' class='s' value='Variable Name'/></th>
						<th>Statement<input type='hidden' name='statement' class='s' value='Statement'></th> -->
						<th>Locator Type<input type='hidden' name='locatorType'	class='s' value='Locator Type'></th>
						<th>Locator Value<input type='hidden' name='locatorValue' class='s' value='Locator Value'></th>
						<th>Operation<input type='hidden' name='operation' class='s' value='Operation'></th>
						<th>TextData<input type='hidden' name='textData' class='s' value='Text Data'></th>
					</tr>
				</thead>

				<tbody id='tBody1' bgcolor='#a3a3c2'>

					<%
						String pFeatureName = "";
						if (excelData.size() > 0) {
							for (HtmlData data : excelData) {

								String feature = "";

								if (!data.getFeature().equals("")) {
									pFeatureName = data.getFeature();
									feature = data.getFeature();
								} else {
									feature = pFeatureName;
								}

								/* String possibleValue = data.getPossibleValue();
								String statement = data.getStatement(); */
								String locatorType = data.getLocatorType();
								String locatorValue = data.getLocatorValue();
								String operation = data.getOperation();
								String textData = data.getTextData();
					%>

					<tr class='<%=feature%>'>
						<td width='1%'><input type="checkbox" name="record" class='record'></td>
						<td><input type='hidden' name='feature' class='s' value='<%=feature%>'/><p class="text-info"><%=feature%></p></td>
						<%-- <td><input type='hidden' name='possibleValue' class='s' value='<%=possibleValue%>'/><p class="text-info"><%=possibleValue%></p></td>
						<td><input type='hidden' name='statement' class='s' value='<%=statement%>'><p class="text-info"><%=statement%></p></td> --%>
						<td><input type='hidden' name='locatorType'	class='s' value='<%=locatorType%>'><p class="text-info"><%=locatorType%></p></td>
						<td><input type='hidden' name='locatorValue' class='s' value='<%=locatorValue%>'><p class="text-info"><%=locatorValue%></p></td>
						<td><input type='hidden' name='operation' class='s' value='<%=operation%>'><p class="text-info"><%=operation%></p></td>
						<td><input type='hidden' name='textData' class='s' value='<%=textData%>'><p class="text-info"><%=textData%></p></td>
					</tr>

					<%
						}
						}
					%>
					</tbody>
					<tr class='dummyRow'>
						<td width='1%'></td>
						<td><input type='hidden' name='feature' value='Dummy'/></td>
						<!-- <td><input type='hidden' name='possibleValue'  value='Dummy'/></p></td>
						<td><input type='hidden' name='statement'  value='Dummy'></td> -->
						<td><input type='hidden' name='locatorType'	 value='Dummy'></p></td>
						<td><input type='hidden' name='locatorValue' value='Dummy'></p></td>
						<td><input type='hidden' name='operation'  value='Dummy'></td>
						<td><input type='hidden' name='textData'  value='Dummy'></td>
					</tr> 
				
			</table>

			<input type='submit' value='Save'>


			<button type="button" class="insert-after1" name='insertAfter'>Insert
				After</button>
			<button type="button" class="insert-before1" name='insertBefore'>Insert
				Before</button>

			<button type="button" class="delete-row">Delete Row</button>
		</form>

	</center>
</body>
<%@ include file="footer.jsp"%>
</html>