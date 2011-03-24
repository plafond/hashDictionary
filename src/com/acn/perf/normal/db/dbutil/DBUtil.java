package com.acn.perf.normal.db.dbutil;

import java.sql.Connection;
import java.sql.Statement;

public class DBUtil {
	
	public static void setupDBTables(Connection con)
	{
		try{
			Statement stm = con.createStatement();
			//hashWords
			//hashWordSize			
		}
		catch(Exception ex){}
	}
	
	public static void dropDBTables(Connection con)
	{
		try{
			Statement stm = con.createStatement();
			
			
		}
		catch(Exception ex){}
	}
	
	public static void createDatabase(Connection con)
	{
		try{
			Statement stm = con.createStatement();
			stm.executeUpdate("CREATE DATABASE hashDictionary");
		}
		catch(Exception ex){}
	}
	
	public static void dropDatabase(Connection con)
	{
		try{
			Statement stm = con.createStatement();
			stm.executeUpdate("DROP DATABASE hashDictionary");
		}
		catch(Exception ex){}
	}


}
