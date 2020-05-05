<%@page import="model.Hospital"%>
<%@page import="com.HospitalApi"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Hospital Management</h1>        
				
				<form id="formHospital" name="formAppointment" method="post" action="index.jsp">  
					FullName:  
					<input id="hospitalname" name="hospitalname" type="text" class="form-control form-control-sm">  
					
					<br> 
					Hospital Address:  
					<input id="hospitaladdress" name="hospitaladdress" type="text" class="form-control form-control-sm">  
					
					<br> 
					Phone Number:  
					<input id="contactnumber" name="contactnumber" type="number" class="form-control form-control-sm">  
					
					<br>
					 Email:  
					 <input id="email" name="email" type="text" class="form-control form-control-sm">  
					 
					 <br> 
			 

					  
					 <input id="save" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Hospital appObj = new Hospital();
						out.print(appObj.readHospital());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>

</html>