package com.acn.perf.normal.db.dbutil;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.acn.perf.db.factory.DBFactory;
import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

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
			
			Statement stm = con.createStatement();
			List<String> wordSQL = new ArrayList<String>();
			List<String> wordSizeSQL = new ArrayList<String>();
			
			//TODO replace with prepared statements
			long start = System.nanoTime();
			for(Map.Entry<String, Long> entry : hashWords.entrySet())
			{
				String hashWordSQL = "INSERT INTO " + HASH_WORD_TABLE + " (word, hash) VALUES ('" + escapeApostropheChar(entry.getKey()) + "', '" + entry.getValue() +"')";
				wordSQL.add(hashWordSQL);
				
				String hashWordSizeSQL = "INSERT INTO " + HASH_WORD_SIZE_TABLE + " (wordsize, hash) VALUES ('" + entry.getKey().length() + "', '" + entry.getValue() +"')";
				wordSizeSQL.add(hashWordSizeSQL);
			}
			Log.logPerf(LogDictionary.GENERATE_INSERT_STMS, (System.nanoTime() - start));
			
			//TODO replace with BATCH
			start = System.nanoTime();
			for(String sql : wordSQL)
			{
				stm.execute(sql);
			}
			
			Log.logPerf(LogDictionary.INSERT_INTO_TABLE.replace("?", String.valueOf(wordSQL.size())).replace("@", HASH_WORD_TABLE), (System.nanoTime() - start));
			
			//TODO replace with BATCH
			start = System.nanoTime();
			for(String sql : wordSizeSQL)
			{
				stm.execute(sql);
			}
			;
			Log.logPerf(LogDictionary.INSERT_INTO_TABLE.replace("?", String.valueOf(wordSizeSQL.size())).replace("@", HASH_WORD_SIZE_TABLE), (System.nanoTime() - start));
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
