<%@page import="com.hos.controller.HospitalController"%>
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
				
				<form id="formHospital" name="formHospital" method="post" action="index.jsp">  
					Hospital Name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					Hospital Address:  
					<input id="address" name="address" type="text" class="form-control form-control-sm">  
					
					<br>
					 Hospital Contact/Hotline:  
					 <input id="contactl" name="contact" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Hospital Email:  
					 <input id="email" name="email" type="text" class="form-control form-control-sm">  
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidappIDSave" name="hidappIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						HospitalController appObj = new HospitalController();
						out.print(appObj.viewAllHospitals());   
					%>  					
				</div> 				  
 			</div>		 
 		</div>    		
	</div> 
</body>
</html>