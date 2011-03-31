package com.acn.perf.optimized;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.acn.perf.CodeSamples;
import com.acn.perf.db.factory.DBFactory;
import com.acn.perf.example.QueryOptimizationExample;
import com.acn.perf.log.Log;
import com.acn.perf.optimized.db.dbutil.DBUtil;
import com.acn.perf.optimized.hashCRC32.CRC32Hasher;
import com.acn.perf.optimized.io.reader.DictionaryReader;
import com.acn.perf.test.TestHarness;

public class HashDictionaryOptimized {

	public static final String DICTIONARY_PATH = "dictionary";
	
	public static void runTerminal()
	{
		System.out.println("********************");
		System.out.println("********MENU********");
		System.out.println("********************");
		System.out.println("");
		System.out.println("1. Setup");
		System.out.println("2. Search");
		System.out.println("Q. Quit");
		Scanner in = new Scanner(System.in);

	    String option = in.nextLine();
	    
	    if("1".equals(option))
		{
	    	int mode = CodeSamples.getMode();
	    	switch (mode)
	    	{
	    		case 1: setup(); break;
	    		case 2: TestHarness.testOptimizedSetup(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    	}
			
			System.out.println("\nSetup Complete - Check the log file for benchmarks!");
		}
		else if("2".equals(option))
		{
			int mode = CodeSamples.getMode();
	    	switch (mode)
	    	{
	    		case 1: QueryOptimizationExample.executeOptimizedQuery(-1); break;
	    		case 2: TestHarness.testOptimizedQueryExample(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    	}
		}
		else if("Q".equals(option))
		{
			System.out.println("\nDon't forget to check the log file for benchmarks!!");
			System.exit(0);
		}
		else
		{
			System.out.println("\nError:" + option + " is not a valid option");
			runTerminal();
		}
	}
	
	public static void setup()
	{
		List<String> words = DictionaryReader.getWordsFromDictionary(DICTIONARY_PATH);
		Map<String, Long> hashWords = CRC32Hasher.hashWords(words);
		
		Connection con = DBFactory.getDBConnectionFromPool();
		try
		{
			//clean first
			DBUtil.dropTables(con);		
			DBUtil.createTables(con);
			DBUtil dbUtil = new DBUtil();
			dbUtil.populateTables(con, hashWords);						
		}
		catch(Exception ex)
		{		
			Log.log("Error while doing DB setup operations", ex);
		}
		finally
		{
			try{ con.close(); }
			catch(Exception ex){};
		}
	}	
}
