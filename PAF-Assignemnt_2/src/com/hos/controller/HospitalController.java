package com.hos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hos.model.Hospital;
import com.hos.util.DBConnection;

/**
 * 
 * @author KaveeshaG
 * all the services regarding to Hospitals are implemented here
 */

public class HospitalController {

	private static Connection connection;
	private static PreparedStatement ps;

	
	
	//--------------------------------view all the hospital details-------------------------------
	public String viewAllHospitals() {
		
		String output = "";
		
		Hospital hos = new Hospital();
		
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database."; }
			
			//html table output headings
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>HID</th>"
					+ "<th>Hospital Name</th>"
					+ "<th>Hospital Address</th>"
					+ "<th>Hospital Contact</th>"
					+ "<th>Hospital Email</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>"; 
			
			
			String query = "SELECT * FROM hospital";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {

					hos.setHid(rs.getInt("hid"));
					hos.setHospitalname(rs.getString("hospitalname"));
					hos.setHospitaladdress(rs.getString("hospitaladdress"));
					hos.setContactnumber(rs.getString("contactnumber"));
					hos.setEmail(rs.getString("email"));

					output += "<tr><td>"+hos.getHid()+" </td>";
					output += "<td>"+hos.getHospitalname()+"</td>";
					output += "<td>"+hos.getHospitaladdress()+"</td>";
					output += "<td>"+hos.getContactnumber()+"</td>";
					output += "<td>"+hos.getEmail()+"</td>";
					
					
					//buttons in a row
					output += "<td><input name='btnUpdate' type='button' value='update' class='btn btn-outline-success'></td>"
							+ "<td><input name='btnRemove' type='button' value='remove' class='btn btn-outline-danger'></td>"		;
					
			}
			
		} catch (Exception e) {
			
			 output = "Error while reading the Hospital Details.";
			 System.err.println(e.getMessage()); 
			
		}
		
		return output;
		
		
	}
	
	
	//-------------------------------------insert a hospital--------------------------------
	public String insertHospital(Hospital hos) {
		
		String output = "";
		
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			String query = "insert into hospital(hid,hospitalname,hospitaladdress,contactnumber,email)"
					+ "values(?, ?, ?, ?, ?)"; 	
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			//value binding
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, hos.getHospitalname());
			preparedStatement.setString(3, hos.getHospitaladdress());
			preparedStatement.setString(4, hos.getContactnumber());
			preparedStatement.setString(5, hos.getEmail());
			
			preparedStatement.execute();
			connection.close();
			
			output = "Inserted successfully"; 
			
			
			String newHospital = viewAllHospitals();
			output = "{\"status\":\"success\", \"data\": \"" +newHospital + "\"}"; 
		} catch (Exception e) {
		
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 
		}
		
		
		return output;
		
	}
	
	
	//------------------------------delete a hospital----------------------------------
	public String deleteHospital(Hospital hos){
	
		String output = "";
		
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for delete the details."; }

		
		String query = "DELETE FROM hospital WHERE hid = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, hos.getHid());
		
		preparedStatement.execute();
		
		connection.close();
		
		output = "Succefully Deleted, Hospital : " +hos.getHid();
				
		}
		catch (Exception e) {

			System.out.println("Error while deleting");
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//------------------------------------ update the hospital data-------------------------------------------------------
	public String updateHospital(Hospital hos) {
		
		String output = "";
		
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for Update the details."; }
			
			String query = "UPDATE hospital SET  hospitalname=?, hospitaladdress=?, contactnumber=?, email=?"
					+ "WHERE hid = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, hos.getHospitalname());
			preparedStatement.setString(2, hos.getHospitaladdress());			
			preparedStatement.setString(3, hos.getContactnumber());
			preparedStatement.setString(4, hos.getEmail());
					
		preparedStatement.execute();
		connection.close();
		
		output = "Update Succesfully";
			
		} catch (Exception e) {
			
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	

}
