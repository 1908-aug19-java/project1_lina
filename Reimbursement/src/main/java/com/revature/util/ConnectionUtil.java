package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;

	public static Connection getHardCodeConnection() {
 	   
  	  // register a JDBC driver 
  	   try{
  		   Class.forName("org.postgresql.Driver");
  	   }catch(ClassNotFoundException e) {
  		   e.printStackTrace();
  	   }
  	   
  	   String url= "jdbc:postgresql://database-project0.c9q9s87ff5s5.us-east-2.rds.amazonaws.com:5432/postgres";
  	   String user = "lina_project0";
  	   String password = "milina7447";
  	   
  	   try {
			if(connection == null || connection.isClosed()) {
				   connection = DriverManager.getConnection(url,  user, password);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
  	   
  	   return connection;
     }
     

}
