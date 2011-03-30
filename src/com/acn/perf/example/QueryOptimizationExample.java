package com.acn.perf.example;

import java.sql.ResultSet;

import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;
import com.acn.perf.db.dbutil.DBUtil;

public class QueryOptimizationExample {
	
	private static final String QUERY = "select hashWords.word, hashWords.hash, hashWordSize.wordsize from hashWords " +
										"inner join hashWordSize on " +
										"hashWords.hash = hashWordSize.hash " +
										"where hashWordSize.wordsize = ?;";
	
	private static final String OPTIMIZED_QUERY = "Select hashWords.word, hashWords.hash, hashWordSize.wordsize from hashWords " +
										"inner join hashWordSize on " +
										"hashWords.hash = hashWordSize.hash " +
										"and hashWordSize.wordsize = ?;";
	
	private static final String DEFAULT_CRITERIA = "7";
	
	public static void clearCache()
	{
		DBUtil.executeStatement("RESET QUERY CACHE;");
	}
	
	public static void executeStandardQuery(int i)
	{
		if(i < 1)
		{
			executeQuery(QUERY.replace("?", DEFAULT_CRITERIA));
		}
		else
		{
			executeQuery(QUERY.replace("?", "" + i));			
		}
	}
	
	public static void executeOptimizedQuery(int i)
	{
		if(i < 1)
		{
			executeQuery(OPTIMIZED_QUERY.replace("?", DEFAULT_CRITERIA));
		}
		else
		{
			executeQuery(OPTIMIZED_QUERY.replace("?", "" + i));			
		}
	}
	
	private static void executeQuery(String sql)
	{
		long start = System.nanoTime();
		ResultSet result = DBUtil.executeSelectStatement(sql);
		Log.logPerf(LogDictionary.QUERY_FOR_HASH_WITH_SOURCE_LENGTH, System.nanoTime() - start);
		displayResults(result);
	}
	
	private static void displayResults(ResultSet resultSet)
	{
		System.out.println("**** QUERY Results *****");
		int i = 0;
		try{
			while (resultSet.next()) {
				// Get the data from the row using the column index
				System.out.println("Word: " + resultSet.getString(1) + "\tHashValue: "+ resultSet.getString(2) + "\tWord Length :" + resultSet.getString(3));
				i++;
			}			
		}
		catch(Exception ex)
		{
			Log.log("Error printing Query Results", ex);
		}
		System.out.println("\n"+ i +" matches found");
	
	}
}
