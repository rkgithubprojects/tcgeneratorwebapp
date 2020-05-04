
	function validateExport() {
		return confirm("Click 'OK' to Update excel sheet")
	}

	function validateForm() {
		var feature = document.forms["addRecord"]["feature"].value;
		var statement = document.forms["addRecord"]["statement"].value;
		var locatorType = document.forms["addRecord"]["locatorType"].value;
		var locatorValue = document.forms["addRecord"]["locatorValue"].value;
		var operation = document.forms["addRecord"]["operation"].value;
		//var textData = document.forms["addRecord"]["textData"].value;

		if (feature == 0) {
			alert("Feature Name Cannot be Empty");
			return false;
		}
		if (statement == 0) {
			alert("Statement Cannot be Empty");
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
		} */else {
			return confirm("Click 'OK' to add New Row or Click 'Cancel' to exit'")

		}
	}

	$(document).ready(function() {
		
		$('#deselect-Category').hide();
		  
        $("#textData").attr("disabled", "disabled");
		$('.record').click(function() {
			var row = $(this).parents("tr");
			if ($(this).is(":checked")) {
				row.css('background-color', '#8378D9');
			} else {
				row.css('background-color', '#a3a3c2');
			}

		});
		
		$('p.text-info').dblclick(function() {
			
			 var text = $(this).text();
			 var input = $('<input id="attribute" type="text" value="' + text + '" />')
			 var prnt=$(this).parents("td");
			 var hiddenElement=prnt.find('.s');
			 var hiddenElementVal=hiddenElement.val();
			 if(hiddenElement.attr('name')=="feature")
				{
				input=$('#feature').clone(true);
				}
			 if(hiddenElement.attr('name')=="locatorType")
				{
				 input=$('#locatorType').clone(true);
				} 
			 if(hiddenElement.attr('name')=="operation")
				{
				 input=$('#operation').clone(true);
				}
			 
			 input.attr('id', 'attribute');
			
			 $(this).text('').append(input);
			 input.select();

			 input.blur(function() {
			   var text = $('#attribute').val();
			  var text1=text.replace(",", "COMMA").replace("|", "LINE").replace(":", "XCOLON").replace(";", "SCOLON")
				.replace("'", "SQUOTE").replace("\"", "DQUOTE").replace("/", "SSLASH").replace("//", "DSLASH")
				.replace("(", "ROBRACE").replace(")", "RCBRACE").replace("]", "SCBRACE").replace("[", "SOBRACE").replace("=","EQUALTO");
			   hiddenElement.val(text1);
			  
			   $('#attribute').parent().text(text);
			   $('#attribute').remove();
			   $(this).css({
			         "color" : "#62309A",
				 });
			   $(".saveImg").show();
			 }); 
			});
		
		$(".add-row").click(function() {
			 var bool = validateForm();
			
			
											if (bool == true) {
												var feature = $("#feature")
														.val();
												
												var statement = $("#statement")
														.val();
												var locatorType = $(
														"#locatorType").val();
												var locatorValue = $(
														"#locatorValue").val();
												var operation = $("#operation")
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
												var markup = $("<tr><td><input type='checkbox' name='record' ></td><td>"
														+ feature
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
														+ "<input type='hidden' name='feature' value='"+feature+"'/><input type='hidden' name='statement' value='"+statement+"'/><input type='hidden' name='locatorType' value='"+locatorType+"'/><input type='hidden' name='locatorValue' value='"+locatorValue+"'/><input type='hidden' name='operation' value='"+operation+"'/><input type='hidden' name='textData' value='"+textData+"'/></td></tr>");
												markup.css({"color" : "#62309A"});
												$("table #tBody1").append(markup);
												$(".saveImg").show();
											}
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
																	if ($(this)
																			.is(
																					":checked")) {
																		$(this)
																				.parents(
																						"tr")
																				.remove();
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
																		$newRow = $curRow
																				.clone(true);
																		var markup = $("<tr><td><input type='checkbox' name='record' class='record'></td><td><input type='hidden' name='feature' value='"+feature+"'>"
																				+ feature
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
																		$curRow
																				.after(markup);
																		$(".saveImg").show();
																	}
																}
															});
										});

						// Find and insert new row after selected row
						$(".insert-before")
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
																	var result = validateForm();
																	if (result == true) {
																		$(this)
																				.click();
																		var feature = $(
																				"#feature")
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

																		var markup = $("<tr><td><input type='checkbox' name='record'></td><td><input type='hidden' name='feature' value='"+feature+"'>"
																				+ feature
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
																		$curRow.before(markup);

																		$(".saveImg").show();
																	}
																}
															});
										});

						$("#selectAll").click(
								function() {
									$("input:checkbox").prop('checked',
											$(this).prop("checked"));
								});
						
						
						$("#addOptBtn").click(function() {
							var newOptn = $("#newOperation").val();
								
								var markup="<option value='"+newOptn+"'>"+newOptn+"</option>";
								
								$("#operation").append(markup);
								$('#operationPopup').hide();
						});
						
						$('#myBtn1').click(function() 
								{
							
								$('#operationPopup').show();
							
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


					});

