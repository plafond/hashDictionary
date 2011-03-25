package com.acn.perf.optimized.db.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

import com.acn.perf.db.factory.DBFactory;
import com.acn.perf.log.Log;

public class DBUtil {
	
	private static final String HASH_WORD_TABLE = "hashWords";
	private static final String HASH_WORD_SIZE_TABLE = "hashWordSize";
	
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
	
	public static void populateTables(Connection con, Map<String, Long> hashWords)
	{
		try
		{
			if(con == null)
			{
				createDatabase();
				con = DBFactory.getDBConnection();
			}
			
			
			PreparedStatement stmWord = con.prepareStatement("INSERT INTO " + HASH_WORD_TABLE + " (word, hash) VALUES (?, ?)");
			PreparedStatement stmWordSize = con.prepareStatement("INSERT INTO " + HASH_WORD_SIZE_TABLE + " (wordsize, hash) VALUES (?, ?)");

			//TODO replace with prepared statements and batch
			long start = System.nanoTime();
			for(Map.Entry<String, Long> entry : hashWords.entrySet())
			{
				stmWord.setString(1, escapeApostropheChar(entry.getKey()));
				stmWord.setString(2, String.valueOf(entry.getValue()));
				stmWord.addBatch();
				
				stmWordSize.setString(1, String.valueOf(entry.getKey().length()));
				stmWordSize.setString(2, String.valueOf(entry.getValue()));
				stmWordSize.addBatch();
			}
			Log.logPerf("Time taken to generate INSERT SQL statements:", (System.nanoTime() - start));
			
			//TODO replaced with BATCH
			start = System.nanoTime();
			stmWord.execute();
			Log.logPerf("Time taken to INSERT " + hashWords.size() + " records into " + HASH_WORD_TABLE + ": ", (System.nanoTime() - start));
			stmWord.close();
			
			//TODO replaced with BATCH
			start = System.nanoTime();
			stmWordSize.execute();
			Log.logPerf("Time taken to INSERT " + hashWords.size() + " records into " + HASH_WORD_SIZE_TABLE + ": ", (System.nanoTime() - start));
			stmWordSize.close();
		}
		catch(Exception ex)
		{
			Log.log("Error populating tables", ex);
		}
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
	
	private static void createDatabase()
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

	private static String escapeApostropheChar(String input)
	{
		return input.replaceAll("'", "\\\\'");
	}

}
