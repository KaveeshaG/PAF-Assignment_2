$(document).ready(function() {  
	
	if ($("#alertSuccess").text().trim() == "")  {   
		$("#alertSuccess").hide();  
		
	}  
	$("#alertError").hide(); 
	
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) {  
	
	// Clear status msges-------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation----------------  
	var status = validateItemForm(); 

	// If not valid-------------------  
	if (status != true)  {   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid-----------------------  
	$("#formHospital").submit(); 
	
	$("#alertSuccess").text("Inserted successfully.");  
	$("#alertSuccess").show(); 
	
}); 

//INSERT ============================================ 
function validateItemForm() {  
	
	// NAME  
	if ($("#name").val().trim() == "")  {   
		return "Insert fullName.";  
		
	} 
	
	 // ADDRESS  
	if ($("#address").val().trim() == "")  {   
		return "Insert Mobile.";  
		
	} 
	 
	 // is numerical value  
	var tmpMobile = $("#contact").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 
	 // Email 
	if ($("#email").val().trim() == "")  {   
		return "Insert Email.";  
		
	} 
	 
	 return true; 
	 
}


//REMOVE========================================== 
$(document).on("click", ".btnRemove", function(event) {  
	
	$(this).closest("tr").remove();    
	$("#alertSuccess").text("Removed successfully.");  
	$("#alertSuccess").show(); 
	
});

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) {     
	
	$("#hidappIDSave").val($(this).closest("tr").find('#hidappIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#contact").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	
	$("#alertSuccess").text("Updated successfully.");  
	$("#alertSuccess").show(); 
	
});
