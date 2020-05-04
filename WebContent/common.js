	$(document).ready(function() {
		$('#deselect-Category').hide();
		$('.saveImg').hide();
		
		$('#newModuleForm').hide();
		$('#selectNewModuleBtn').hide();
		$('#deleteModuleForm').hide();
	
		
		$('#deleteModuleBtn').click(function () {
			$('#deleteModuleForm').show();
			$('#newModuleForm').hide();
			$('#selectModuleForm').hide();
	    });

		$('#createNewModuleBtn').click(function () {
			
			$('#newModuleForm').show();
			 $('#selectModuleForm').hide();			 
			$('#selectNewModuleBtn').show();
			$('#createNewModuleBtn').hide();
			$('#deleteModuleForm').hide();
	    });
		
		
		
		$('#selectNewModuleBtn').click(function () {
			
			$('#selectModuleForm').show();
			$('#selectNewModuleBtn').hide();
			
			$('#createNewModuleBtn').show();
			$('#newModuleForm').hide();
			$('#deleteModuleForm').hide();
			
	      
	    });
		
		
		
		 var auto = $('#automation').val();
		/* alert($('#automation'))
			alert("$$$$$$$ "+auto);*/
			if(auto=="TRUE")
				 {
				
				 /*alert("@@@@@@@@@")*/
			    $("#locatorValue").removeAttr("disabled");
				 $("#locatorType").removeAttr("disabled");
				 $("#myBtn1").removeAttr("disabled");
				 $("#operation").removeAttr("disabled");
				 $("#txtdata").removeAttr("disabled");
				 
				 }
			else
				{
				 $("#locatorType").val("NA");
				 $("#operation").val("NA");
				 $("#locatorValue").val("NA");
				}
		
		
		
	});