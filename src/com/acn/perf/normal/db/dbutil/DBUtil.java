package com.acn.perf.normal.db.dbutil;

import java.sql.Connection;
import java.sql.Statement;

import com.acn.perf.normal.db.factory.DBFactory;

public class DBUtil {
	
	public static void setupDBTables()
	{
		try{		
			Statement stm = DBFactory.getDBConnection().createStatement();
			
			//hashWords
			//hashWordSize			
		}
		catch(Exception ex){}
	}
	
	public static void dropDBTables()
	{
		try{
			Statement stm = DBFactory.getDBConnection().createStatement();
			
			
		}
		catch(Exception ex){}
	}
	
	public static void createDatabase()
	{
		try{
			Statement stm = DBFactory.getDBConnection().createStatement();
			stm.executeUpdate("CREATE DATABASE hashDictionary");
		}
		catch(Exception ex){}
	}
	
	public static void dropDatabase()
	{
		try{
			Statement stm = DBFactory.getDBConnection().createStatement();
			stm.executeUpdate("DROP DATABASE hashDictionary");
		}
		catch(Exception ex){}
	}


}
