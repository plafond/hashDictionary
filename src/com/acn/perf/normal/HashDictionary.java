package com.acn.perf.normal;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.acn.perf.normal.db.dbutil.DBUtil;
import com.acn.perf.normal.db.factory.DBFactory;
import com.acn.perf.normal.hashCRC32.CRC32Hasher;
import com.acn.perf.normal.io.reader.DictionaryReader;
import com.acn.perf.normal.log.Log;

public class HashDictionary {

	public static final String DICTIONARY_PATH = "dictionary";
	
	
	public static void main(String args[])
	{
		runTerminal();
	}
	
	private static void runTerminal()
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
	    if(option != null)
	    {
	    	
	    }
	    
	    if("1".equals(option))
		{
			setup();
			runTerminal();
		}
		else if("2".equals(option))
		{
			System.out.println("\nNot Yet");
			runTerminal();
		}
		else if("Q".equals(option))
		{
			System.out.println("\nCheck the log file for benchmarks!");
			System.exit(0);
		}
		else
		{
			System.out.println("\nError:" + option + " is not a valid option");
			runTerminal();
		}
	}
	
	private static void setup()
	{
		List<String> words = DictionaryReader.getWordsFromDictionary(DICTIONARY_PATH);
		Map<String, Long> hashWords = CRC32Hasher.hashWords(words);
		
		Connection con = DBFactory.getDBConnection();
		try
		{
			//clean first
			DBUtil.dropTables(con);		
			DBUtil.createTables(con);
			DBUtil.populateTables(con, hashWords);						
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
		
		runTerminal();
	}
}
