package com.acn.perf.normal.db.conn.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import com.acn.perf.normal.log.Log;

public class MySQLDBConnection {
	
	static{
		 //Register the JDBC driver for MySQL.
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception ex){}	
	}
	
	public static Connection getDBConnection()
	{
		return getDBConnection(null);
	}
	
	public static Connection getDBConnection(String db)
	{
		try{
			//Define URL of database server for
			// database named mysql on the localhost
			// with the default port number 3306.
			String url =
				"jdbc:mysql://localhost:3306/";
			
			if(db != null)
			{
				url += db;
			}
			
			//Get a connection to the database for a
			// user named root with a blank password.
			// This user is the default administrator
			// having full privileges to do anything.
			return  DriverManager.getConnection(
					url,"root", "hash");			
			
			//TODO for optimize - turn off autocommit
		}
		catch(Exception ex) 
		{
			Log.log("Error establishing MySQL connection", ex);
		}
		
		return null;
	}
}
