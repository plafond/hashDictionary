package com.acn.perf.db.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import com.acn.perf.db.factory.DBFactory;
import com.acn.perf.log.Log;

public abstract class DBUtil {
	
	public static final String HASH_WORD_TABLE = "hashWords";
	public static final String HASH_WORD_SIZE_TABLE = "hashWordSize";
	
	public abstract void populateTables(Connection con, Map<String, Long> hashWords);
	
	public static void createTables(Connection con)
	{
		try{
			if(con == null)
			{
				createDatabase();
				con = DBFactory.getDBConnection();
			}
			Statement stm = con.createStatement();
			stm.execute("CREATE TABLE " + HASH_WORD_TABLE + " (word CHAR(32), hash CHAR(32));");
			stm.execute("CREATE TABLE " + HASH_WORD_SIZE_TABLE + " (wordsize CHAR(32), hash CHAR(32));");
		}
		catch(Exception ex)
		{
			Log.log("Error creating tables", ex);
		}
	}
		
	public static Statement executeStatement(String sql)
	{
		try
		{
			Connection con = DBFactory.getDBConnection();
			Statement stm = con.createStatement();
			stm.execute(sql);
			return stm;
		}
		catch(Exception ex)
		{
			Log.log("Error executing DB statement", ex);
		}
		
		return null;
	}
	
	public static ResultSet executeSelectStatement(String sql)
	{
		Statement stm = executeStatement(sql);
		if(stm != null)
		{
			try{				
				return stm.getResultSet();
			}
			catch(Exception ex)
			{
				Log.log("Error getting DB result set", ex);
			}
		}
		
		return null;
	}
	
	public static void dropTables(Connection con)
	{
		try{
			if(con == null)
			{
				createDatabase();
				con = DBFactory.getDBConnection();
			}
			
			Statement stm = con.createStatement();
			
			stm.executeUpdate("DROP TABLE " + HASH_WORD_TABLE);
			stm.executeUpdate("DROP TABLE " + HASH_WORD_SIZE_TABLE);

			stm.close();
		}
		catch(Exception ex)
		{
			Log.log("Error dropping tables", ex);
		}
	}
	
	public static void createDatabase()
	{
		try{
			Statement stm = DBFactory.getTopLevelConnection().createStatement();
			stm.executeUpdate("CREATE DATABASE " + DBFactory.DATABASE_NAME);
			stm.close();
		}
		catch(Exception ex)
		{
			Log.log("Error creating Database", ex);
		}
	}
	
	private static void dropDatabase()
	{
		try{
			Statement stm = DBFactory.getTopLevelConnection().createStatement();
			stm.executeUpdate("DROP DATABASE " + DBFactory.DATABASE_NAME);
			stm.close();
		}
		catch(Exception ex)
		{
			Log.log("Error droping Database", ex);
		}
	}

	public static String escapeApostropheChar(String input)
	{
		return input.replaceAll("'", "\\\\'");
	}

}
