package com.acn.perf.optimized.db.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import com.acn.perf.db.factory.DBFactory;
import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

public class DBUtil extends com.acn.perf.db.dbutil.DBUtil {
	
	
	public void populateTables(Connection con, Map<String, Long> hashWords)
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
			Log.logPerf(LogDictionary.GENERATE_INSERT_STMS, (System.nanoTime() - start));
			
			//TODO replaced with BATCH
			start = System.nanoTime();
			stmWord.executeBatch();
			Log.logPerf(LogDictionary.INSERT_INTO_TABLE.replace("?", String.valueOf(hashWords.size())).replace("@", HASH_WORD_TABLE), (System.nanoTime() - start));
			stmWord.close();
			
			//TODO replaced with BATCH
			start = System.nanoTime();
			stmWordSize.executeBatch();
			Log.logPerf(LogDictionary.INSERT_INTO_TABLE.replace("?", String.valueOf(hashWords.size())).replace("@", HASH_WORD_SIZE_TABLE), (System.nanoTime() - start));
			stmWordSize.close();
		}
		catch(Exception ex)
		{
			Log.log("Error populating tables", ex);
		}
	}
}
