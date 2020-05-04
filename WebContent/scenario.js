
		
	function validateExport() {
		return confirm("Click 'OK' to Update excel sheet")
	}

	function validateForm() {
		var feature = document.forms["addRecord"]["feature"].value;
		var possibleValue = document.forms["addRecord"]["possibleValue"].value;
		//var statement = document.forms["addRecord"]["statement"].value;
		var locatorType = document.forms["addRecord"]["locatorType"].value;
		var locatorValue = document.forms["addRecord"]["locatorValue"].value;
		var operation = document.forms["addRecord"]["operation"].value;
		//var textData = document.forms["addRecord"]["textData"].value;

		if (feature == 0) {
			alert("Feature Name Cannot be Empty");
			return false;
		}
		if (possibleValue == "") {
			alert("Possible Value cannot be Empty");
			return false;
		}
		if (locatorType == 0) {
			alert("Locator Type is not selected");
			return false;
		}
		if (locatorValue == "") {
			alert("Please Enter valid locator value");
			return false;
		}

		if (operation == "") {
			alert("Please select valid operation");
			return false;
		}
		/*if (textData == "") {
			alert("Please Enter valid textData in input box");
			return false;
		}*/ else {
			return confirm("Click 'OK' to add New Row or Click 'Cancel' to exit'")

		}
	}

	$(document).ready(function() {
		$('#deselect-Category').hide();
		
		$('.record').click(function () {
	        var row = $(this).parents("tr");
	        if ($(this).is(":checked")) {
	        	row.css('background-color', '#8378D9');
	        }
	        else
	        {
	        	row.css('background-color', '#a3a3c2');
	        }
	        
	    });
		
		$(".add-row").click(function() {

											var bool = validateForm();
											
											if (bool == true) {
												var feature = $("#feature").val();
												var possibleValue = $("#possibleValue").val();
												var statement = $("#statement").val();
												var locatorType = $("#locatorType").val();
												var locatorValue = $("#locatorValue").val();
												var operation = $("#operation").val();
												var textData = "";
												
												if($("#textData").val()=="")
													{
														textData="NA";
													}
												else
													{
														textData=$("#textData").val();
													}
												
												var markup = $("<tr><td><input type='checkbox' name='record' class='record'></td><td>"
														+ feature
														+ "</td><td>"
														+ possibleValue
														+ "</td><td>"
														+ statement
														+ "</td><td>"
														+ locatorType
														+ "</td><td>"
														+ locatorValue
														+ "</td><td>"
														+ operation
														+ "</td><td>"
														+ textData
														+ "<input type='hidden' name='feature' value='"+feature+"'/><input type='hidden' name='possibleValue' value='"+possibleValue+"'/><input type='hidden' name='statement' value='"+statement+"'/><input type='hidden' name='locatorType' value='"+locatorType+"'/><input type='hidden' name='locatorValue' value='"+locatorValue+"'/><input type='hidden' name='operation' value='"+operation+"'/><input type='hidden' name='textData' value='"+textData+"'/></td></tr>");

												markup.css({"color" : "#62309A"});
												$("table #tBody1").append(markup);
												$(".saveImg").show();
												
											}
										});
						
						$("#addFeatureBtn").click(function() {
							var newFeature = $("#newFeature").val();
								if(newFeature!=""){
								var markup="<option value='"+newFeature+"'>"+newFeature+"</option>";
								
								$("#feature").append(markup);
								$('#myModal').hide();
								}
						});

						$("#addOptBtn").click(function() {
							var newOptn = $("#newOperation").val();
								
								var markup="<option value='"+newOptn+"'>"+newOptn+"</option>";
								
								$("#operation").append(markup);
								$('#operationPopup').hide();
						});

						//delete selected row
						$(".delete-row")
								.click(
										function() {
											
											var res = confirm("Click 'OK' to delete record");
											if (res == true) {
												$("table tbody")
														.find(
																'input[name="record"]')
														.each(
																function() {
																	if ($(this).is(":checked")) {
																		$(this).parents("tr").remove();
																		$(".saveImg").show();
																	}
																});
											}
										});

						// Find and insert new row after selected row
						$(".insert-after")
								.click(
										function() {

											$("table tbody")
													.find(
															'input[name="record"]')
													.each(
															function() {
																if ($(this)
																		.is(
																				":checked")) {
																	var bool = validateForm();

																	if (bool == true) {
																		$(this)
																				.click();
																		var feature = $(
																				"#feature")
																				.val();
																		var possibleValue = $("#possibleValue")
																				.val();
																		var statement = $(
																				"#statement")
																				.val();
																		var locatorType = $(
																				"#locatorType")
																				.val();
																		var locatorValue = $(
																				"#locatorValue")
																				.val();
																		var operation = $(
																				"#operation")
																				.val();
																		var textData = "";
																		
																		if($("#textData").val()=="")
																			{
																				textData="NA";
																			}
																		else
																			{
																				textData=$("#textData").val();
																			}
																		
																		
																		var $curRow = $(
																				this)
																				.closest(
																						'tr');
																		$newRow = $curRow.clone(true);
																		var markup = $("<tr><td><input type='checkbox' name='record' class='record'></td><td><input type='hidden' name='feature' value='"+feature+"'>"
																				+ feature
																				+ "</td><td><input type='hidden' name='possibleValue' value='"+possibleValue+"'>"
																				+ possibleValue
																				+ "</td><td><input type='hidden' name='statement' value='"+statement+"'>"
																				+ statement
																				+ "</td><td><input type='hidden' name='locatorType' value='"+locatorType+"'>"
																				+ locatorType
																				+ "</td><td><input type='hidden' name='locatorValue' value='"+locatorValue+"'>"
																				+ locatorValue
																				+ "</td><td><input type='hidden' name='operation' value='"+operation+"'>"
																				+ operation
																				+ "</td><td><input type='hidden' name='textData' value='"+textData+"'>"
																				+ textData
																				+ "</td></tr>");
																		markup.css({"color" : "#62309A"});
																		$curRow.after(markup);
																		$(".saveImg").show();
																	}
																}
															});
										});

						// Find and insert new row after selected row
	$(".insert-before").click(function() {

	$("table tbody").find('input[name="record"]').each(function() 
			{
				if ($(this).is(":checked")) {
				var result = validateForm();
				if (result == true) {
				$(this).click();
		var feature = $("#feature").val();
		var possibleValue = $("#possibleValue").val();
		var statement = $("#statement").val();
		var locatorType = $("#locatorType").val();
		var locatorValue = $("#locatorValue").val();
		var operation = $("#operation").val();
		var textData = "";
		
		if($("#textData").val()=="")
		{
			textData="NA";
		}
		else
		{
			textData=$("#textData").val();
		}
		var $curRow = $(this).closest('tr');

		var markup = $("<tr><td><input type='checkbox' name='record' class='record'></td><td><input type='hidden' name='feature' value='"+feature+"'>"
						+ feature+ "</td><td><input type='hidden' name='possibleValue' value='"+possibleValue+"'>"+ possibleValue+ "</td><td><input type='hidden' name='statement' value='"+statement+"'>"
																				+ statement
																				+ "</td><td><input type='hidden' name='locatorType' value='"+locatorType+"'>"
																				+ locatorType
																				+ "</td><td><input type='hidden' name='locatorValue' value='"+locatorValue+"'>"
																				+ locatorValue
																				+ "</td><td><input type='hidden' name='operation' value='"+operation+"'>"
																				+ operation
																				+ "</td><td><input type='hidden' name='textData' value='"+textData+"'>"
																				+ textData
																				+ "</td></tr>");
		markup.css({"color" : "#62309A"});							
		$curRow.before(markup);
		$(".saveImg").show();	
																	}
																}
															});
										});
						
						
						$(".displayRow1").dblclick(function() 
								{
								
								$("#editModal").show();
												
								var rowID= "#"+this.id;
								alert("Row Selected "+$(rowID));
								var editfeature=$(rowID).find('.editf').val();
								var editPossibleValue=$(rowID).find('.editPV').val();
								var editStatement=$(rowID).find('.editS').val();
								var editLocatorType=$(rowID).find('.editLT').val();
								var editLocatorValue=$(rowID).find('.editLV').val();
								var editOperation=$(rowID).find('.editOP').val();
								var editTextData=$(rowID).find('.editTD').val();
								$("#editModal").find("#feature1").val(editfeature);
								$("#editModal").find("#possibleValue1").val(editPossibleValue);
								$("#editModal").find("#statement1").val(editStatement);
								$("#editModal").find("#locatorType1").val(editLocatorType);
								$("#editModal").find("#locatorValue1").val(editLocatorValue);
								$("#editModal").find("#operation1").val(editOperation);
								$("#editModal").find("#textData1").val(editTextData);
								
								var newRow="<tr><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td><td>@@@@@</td></tr>"
																
								$("#updateBtn").click(function() {
									alert("Clicked on Update Button");
									alert(newRow);
									alert($(rowID));
									$(rowID).append(newRow);
									alert("Success");
								});
								
							});
						
						$("#select-Category").click(function() 
							{
								$("table tbody").find('input[name="record"]').each(function() 
										{
											if ($(this).is(":checked")) {
											var className=$(this).parents('tr').attr('class');
											$('tr').each(function() {
										       var t=$(this).attr('class');
										       if(t==className)
										    	   {
										    	   if($(this).find('.record').prop("checked") == false)
										    	   {
										    		   $(this).find('.record').prop("checked", true);
										    		   $(this).css('background-color', '#8378D9');
										    		   $("#select-Category").hide();
										    		   $("#deselect-Category").show();
										    	   }
										    	   }
											});

										}
											
								});

							});
						
						
					/*	/Deselect Category*/		
						
						$("#deselect-Category").click(function()  
								{
								
								$("table tbody").find('input[name="record"]').each(function() 
									{
										if ($(this).is(":checked")) 
										{
											var parentRow=$(this).parents('tr');
											
											$(this).prop("checked", false);
											parentRow.css('background-color', '#a3a3c2');
											$("#deselect-Category").hide();
											$("#select-Category").show();
										}
									});
						});
						
						

					//*******************************		

						
				$("#selectAll").click(function() 
						{
									$("input:checkbox").prop('checked',	$(this).prop("checked"));
						});
						
				$('#myBtn').click(function() 
						{
					
						$('#myModal').show();
					
						});
				
				$('#myBtn1').click(function() 
						{
					
						$('#operationPopup').show();
					
						});
				
				$('.close').click(function() 
					{
							//$('#myModal').hide();
					$('.modal').hide();
					});

				
				 $("#txtdata").click(function () {
					 if ($(this).is(":checked")) {
			                $("#textData").removeAttr("disabled");
			                $("#textData").focus();
			            } else {
			            	$("#textData").val('');
			                $("#textData").attr("disabled", "disabled");
			            }
					
			        });
				
				
				
					});

