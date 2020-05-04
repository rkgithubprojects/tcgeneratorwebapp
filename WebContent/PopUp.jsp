<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">

function validateNewFeatureForm() {
	var newFeature = document.forms["addFeatureForm"]["newFeature"].value;
	
	if (newFeature == "") {
		alert("Enter New feature name in input box");
		return false;
	} else {
		return true;/* confirm("Click 'OK' to add New Row or Click 'Cancel' to exit'") */

	}
}

</script>
 <!-- **************************Add New Feature Pop Up*******************************/ -->

<div id="myModal" class="modal" >
  <div class="modal-content">
     <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Add Feature</h2>
    </div>
    <div class="modal-body">
    <form  method="get" name="addFeatureForm" action="AddNewFeature.jsp" >
      <center>
      <p>Enter New Feature Name : </p>
      <p><input type='text' name='newFeature' id='newFeature'/><br><br><input type="button" id="addFeatureBtn" value="Add" onclick="return(validateNewFeatureForm());">
      </center>
      </form>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>

<!--**************************End*******************************/ -->

 <!-- **************************Add Test Data Name*******************************/ -->

<div id="testDataModal" class="modal" >
  <div class="modal-content">
     <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Add New TestData</h2>
    </div>
    <div class="modal-body">
    <form  method="get" action="">
      <center>
      <p>Enter New Testdata Name : </p>
      <p><input type='text' name='newTestData' id='newTestData'/>
      <br><br>
      <input type="button" id="addTestDataBtn" value="Add">
      </center>
      </form>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>

<!--**************************End*******************************/ -->


<div id="operationPopup" class="modal" >
  <div class="modal-content">
     <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Edit Row</h2>
    </div>
    <div class="modal-body">
    <form  method="get" >
      <center>
      <p>Enter New Operation : </p>
      <p><input type='text' name='newOperation' id='newOperation'/><br><br><input type="button" id="addOptBtn" value="Add">
      </center>
      </form>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>


</body>
</html>