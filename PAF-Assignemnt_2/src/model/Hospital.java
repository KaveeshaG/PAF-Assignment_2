package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/hcs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

				//For testing          
				 System.out.print("Successfully connected");
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
		public String readHospital() {  
			String output = "";  
			
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

				// Prepare the html table to be displayed   
				output = "<table border='1'>"
						+ "<th>Hospital Name</th>"
						+ "<th>Hospital Address</th>"
						+ "<th>Hospital HotLine</th>"
						+ "<th>Hospital Email</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>";


				  String query = "select * from hospital";   
				  Statement stmt = con.createStatement();   
				  ResultSet rs = stmt.executeQuery(query); 

				  // iterate through the rows in the result set   
				  while (rs.next())   {  

					  	int hid = rs.getInt("hid");
						String hospitalname = rs.getString("hospitalname");
						String hospitaladdress = rs.getString("hospitaladdress");
						String contactnumber = Integer.toString(rs.getInt("contactnumber"));
						String email = rs.getString("email");
					  // Add into the html table    

					  output += "<tr><td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='" + hid + "'>" + hospitalname + "</td>"; 

					  	output += "<td>" + hospitaladdress + "</td>";
						output += "<td>" + contactnumber + "</td>";
						output += "<td>" + email + "</td>"; 
					  
					// buttons     
					  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appID='"+ hid +"'>"+"</td></tr>";

					} 
				  
				  con.close(); 

				  // Complete the html table   
				  output += "</table>"; 
				}
				catch (Exception e) {  
					output = "Error while reading the Appointment.";  
					System.err.println(e.getMessage()); 
				}

				return output;
			}
		
		//Insert appointment
		public String insertHospital(String hospitalname, String hospitaladdress, String contactnumber, String email) {
			String output = "";

			try {
				Connection con = connect();  

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " INSERT INTO hospital (`hid`,`hospitalname`,`hospitaladdress`,`contactnumber`,`email`)"+" VALUES (?, ?, ?, ?, ?)";
				

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, hospitalname);
				preparedStmt.setString(3, hospitaladdress);
				preparedStmt.setString(4, contactnumber);
				preparedStmt.setString(5,email);
				
				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				//Create JSON Object to show successful msg.
				String newHospital = readHospital();
				output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
			}
			catch (Exception e) {  
				//Create JSON Object to show Error msg.
				output = "{\"status\":\"error\", \"data\": \"Error while Inserting Hospital Details.\"}";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		//Update appointment
		public String updateHospital(String hid, String hospitalname, String hospitaladdress, String contactnumber, String email)  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
			   String query = "UPDATE hospital SET hospitalname=?,hospitaladdress=?,contactnumber=?,email=? WHERE hid=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values 
		   
		    preparedStmt.setString(1, hospitalname);
			preparedStmt.setString(2,hospitaladdress);
			preparedStmt.setString(3, contactnumber);
			preparedStmt.setString(4,email);
			preparedStmt.setInt(5, Integer.parseInt(hid));
		   
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   //create JSON object to show successful msg
		   String newHospital = readHospital();
		   output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
		   }   catch (Exception e)   {    
			   output = "{\"status\":\"error\", \"data\": \"Error while Updating Hospital Details.\"}";      
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteHospital(String hid) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = connect();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "DELETE FROM hospital WHERE hid=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  // binding values   
		  preparedStmt.setInt(1, Integer.parseInt(hid));       
		  // execute the statement   
		  preparedStmt.execute();   
		  con.close(); 
		 
		  //create JSON Object
		  String newHospital = readHospital();
		  output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
		  }  catch (Exception e)  {  
			  //Create JSON object 
			  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Hospital.\"}";
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
}
