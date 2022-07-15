package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validation {
	public  boolean validate(String userName,String password) 
	{
		boolean status = false;  
	
		try{  
	Class.forName("com.mysql.cj.jdbc.Driver");
	
		Connection con = DriverManager.getConnection("jdbc:mysql://dev-ws.bajajfinservsecurities.in:8444/SESSION_DATABASE", "platformwrite", "bfslwrite");
		PreparedStatement ps= con.prepareStatement("SELECT * FROM SESSION_DATABASE.SESSION_TABLE where userName=? and password=? ");  
		ps.setString(1,userName); 
		ps.setString(2,password); 
		ResultSet rs=ps.executeQuery();  

if(rs.next()){
	status= true;
}
	   ps.close();
	   con.close();
	   }catch(Exception e)
		{e.printStackTrace();}
		return status;
}
}