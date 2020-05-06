package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

	private Connection connect()
	{
		Connection con = null;
		try
		{
			String dbURL = "jdbc:mysql://127.0.0.1:3306/doctor" ;
			//String dbName = "" ;
			String dbUsername = "root" ;
			String dbPassword = "dila123";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(dbURL,dbUsername , dbPassword);
			
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 
	
	
	// reading an doctor -------------------------
	public String readDoc()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr><th>First Name</th> <th>Last Name</th><th>Email</th><th>password</th><th>doctorID</th><th>Specialty</th><th>NIC</th><th>Phone Number</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctor_reg";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			while (rs.next())
			{
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String doctorID = Integer.toString(rs.getInt("doctorID"));
				String specialty = rs.getString("specialty");
				String nic = rs.getString("nic");
				String phoneNO = rs.getString("phoneNO"); 

		
				output += "<tr><td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + specialty + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + phoneNO + "</td>";

				output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-doctorid='"+ doctorID + "'>" + "</td></tr>";
			}
			con.close();
		
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//insert doctor
	public String insertDoc(String fname, String lname,String email, String passwrd , String spec, String nic,String phnenum)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			
			String query = " INSERT INTO doctor_reg(`firstName`, `lastName`, `email`, `password`, `doctorID`, `specialty`, `nic`, `phoneNO`) VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
	
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, passwrd);
			preparedStmt.setInt(5, 0);
			preparedStmt.setString(6, spec);
			preparedStmt.setString(7, nic);
			preparedStmt.setString(8, phnenum);
			
			preparedStmt.execute();
			
			System.out.print("successfuly inserted");
			 
			con.close();
			
			String newItems = readDoc();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update doctor
	public String updateDoc(String fname, String lname,String email, String passwrd ,String docID, String spec, String nic,String phnenum)
	{
		System.out.print(fname);
		
		System.out.print("1");
		
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE doctor_reg SET firstName=?,lastName=?,email=?,password=?,specialty=?,nic=?,phoneNO=? WHERE doctorID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, passwrd);
			preparedStmt.setString(5, spec);
			preparedStmt.setString(6, nic);
			preparedStmt.setString(7, phnenum);
			preparedStmt.setInt(8,Integer.parseInt(docID));
			
		
			preparedStmt.execute();
			con.close();
			String newItems = readDoc();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete doctor
	public String deleteDoc(String doctorID) {
		
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
	
			String query = "DELETE FROM doctor.doctor_reg WHERE doctorID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
		
			preparedStmt.setString(1, doctorID.toString());
			
		
			preparedStmt.execute();
			
			con.close();
		
			String newItems = readDoc();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
		
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
