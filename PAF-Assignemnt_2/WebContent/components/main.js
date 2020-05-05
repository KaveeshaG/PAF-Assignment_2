$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#save", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "HospitalAPI",
		type : t,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalSaveComplete(response.responseText, status);
		}
	});
}); 

function onHospitalSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formHospital")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
	$("#hospitalname").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#hospitaladdress").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#contactnumber").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "HospitalAPI",
		type : "DELETE",
		data : "appID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalDeletedComplete(response.responseText, status);
		}
	});
});

function onHospitalDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateHospitalForm() {  
	// NAME  
	if ($("#hospitalname").val().trim() == "")  {   
		return "Insert fullName.";  
		
	} 
	
	//ADDRESS
	if ($("#hospitaladdress").val().trim() == "")  {   
		return "Insert address.";  
		
	} 
	
	 // MOBILE  
	if ($("#contactnumber").val().trim() == "")  {   
		return "Insert Contact Number.";  
		
	} 
	 
	 // is numerical value  
	var tmpMobile = $("#contactnumber").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Contact Number.";  
		
	}
	 
	 // Email 
	if ($("#email").val().trim() == "")  {   
		return "Insert Email.";  
		
	} 
	
	 
	 return true; 
	 
}
