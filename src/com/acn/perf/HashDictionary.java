package com.acn.perf;

import java.util.Scanner;

import com.acn.perf.log.Log;
import com.acn.perf.normal.HashDictionaryNormal;
import com.acn.perf.optimized.HashDictionaryOptimized;
import com.acn.perf.test.TestHarness;

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
		System.out.println("1. Normal");
		System.out.println("2. Optimized");
		System.out.println("3. String Concat Example");
		System.out.println("4. String Concat Optimized Example");
		System.out.println("5. Query Optimization");
		System.out.println("Q. Quit");
		Scanner in = new Scanner(System.in);

	    String option = in.nextLine();
	    
	    if("1".equals(option))
		{
	    	Log.log("########## Normal Mode ##########");
			HashDictionaryNormal.runTerminal();
		}
		else if("2".equals(option))
		{
			Log.log("########## Optimized Mode ##########");
			HashDictionaryOptimized.runTerminal();
		}
	    if("3".equals(option))
		{
	    	Log.log("########## Normal Mode ##########");
			//StringConcatExample.executeStringConcat();
	    	TestHarness.testStringConcatExample(null);
		}
		else if("4".equals(option))
		{
			Log.log("########## Optimized Mode ##########");
			//StringConcatExample.executeStringConcatOptimized();
			TestHarness.testStringConcatExample(null);
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
}
