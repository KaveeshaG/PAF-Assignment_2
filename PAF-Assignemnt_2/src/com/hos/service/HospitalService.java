package com.hos.service;

import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hos.controller.HospitalController;
import com.hos.model.Hospital;


/**
 * 
 * @author KaveeshaG
 * all the services regarding to PaymentSchemes are implemented here
 */

@Path("/Hospital")
public class HospitalService {


	Hospital hos = new Hospital(); //Hospital object which is used in all the methods
	
	HospitalController hosc = new HospitalController(); //Hospital bean object which is used in all the methods
	
	
	//view all Hospital
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentSchemes() {

		return hos.viewAllHospitals();
	}


	//insert Hospital
	@RolesAllowed({"admin"})
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPaymentScheme(String psData) {
		
		// Convert the input string to a JSON object
				JsonObject jsonObj = new JsonParser().parse(psData).getAsJsonObject();
				
				hos.setHospitalname(jsonObj.get("hospitalname").getAsString());
				hos.setHospitaladdress(jsonObj.get("hospitaladdress").getAsString());
				hos.setContactnumber(jsonObj.get("contactnumber").getAsString());
				hos.setEmail(jsonObj.get("email").getAsString());
				
				String output = hos.insertHospital(hosc);
				
				return output;		
	}
	
	
	//delete Hospital
	@RolesAllowed({"admin"})
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String psData) {

		String output = "";
		
		JsonObject doc = new JsonParser().parse(psData).getAsJsonObject();
		
		Hospital hos = new Hospital();
		
		hos.setHid(doc.get("hid").getAsInt());
		
		output = hos.deleteHospital(hos);

		return output;
	}
	

	//update Hospital 
	@RolesAllowed({"admin"})
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentScheme(String psData) {
		
		//psData String is converted to the json object
		JsonObject jsonObj = new JsonParser().parse(psData).getAsJsonObject();
		
		Hospital hos = new Hospital();
		
		hos.setHid(jsonObj.get("hid").getAsInt());
		hos.setHospitalname(jsonObj.get("hospitalname").getAsString());
		hos.setHospitaladdress(jsonObj.get("hospitaladdress").getAsString());
		hos.setContactnumber(jsonObj.get("contactnumber").getAsString());
		hos.setEmail(jsonObj.get("email").getAsString());
		
		String output = hos.updateHospital(hos);
		return output;	
	}
}

