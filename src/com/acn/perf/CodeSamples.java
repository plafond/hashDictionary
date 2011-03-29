package com.acn.perf;

import java.util.Scanner;

import com.acn.perf.example.StringConcatExample;
import com.acn.perf.log.Log;
import com.acn.perf.normal.HashDictionaryNormal;
import com.acn.perf.optimized.HashDictionaryOptimized;
import com.acn.perf.test.TestHarness;

public class CodeSamples {

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
		System.out.println("1. HashDictionary");
		System.out.println("2. HashDictionary Optimized");
		System.out.println("3. String Concat Example");
		System.out.println("4. String Concat Optimized Example");
		System.out.println("5. Query Optimization");
		System.out.println("Q. Quit");
		Scanner in = new Scanner(System.in);

	    String option = in.nextLine();
	    
	    if("1".equals(option))
		{
	    	Log.log("########## Normal Mode ##########");
	    	int mode = getMode();
	    	switch (mode)
	    	{
	    		case 1: HashDictionaryNormal.runTerminal(); break;
	    		case 2: TestHarness.testNormalSetup(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    				runTerminal();
	    	}
		}
		else if("2".equals(option))
		{
			Log.log("########## Optimized Mode ##########");
			int mode = getMode();
	    	switch (mode)
	    	{
	    		case 1: HashDictionaryOptimized.runTerminal(); break;
	    		case 2: TestHarness.testOptimizedSetup(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    				runTerminal();
	    	}
		}
	    if("3".equals(option))
		{
	    	Log.log("########## Normal Mode ##########");
			int mode = getMode();
	    	switch (mode)
	    	{
	    		case 1: StringConcatExample.executeStringConcat(); break;
	    		case 2: TestHarness.testStringConcatExample(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    				runTerminal();
	    	}
		}
		else if("4".equals(option))
		{
			Log.log("########## Optimized Mode ##########");
			int mode = getMode();
	    	switch (mode)
	    	{
	    		case 1: StringConcatExample.executeStringConcatOptimized(); break;
	    		case 2: TestHarness.testStringConcatOptimizedExample(null); break;
	    		default: System.out.println("\nError:" + option + " is not a valid option");
	    				runTerminal();
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
	
	private static int getMode()
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("********************");
		System.out.println("********************");
		System.out.println("");
		System.out.println("1. Single Execution");
		System.out.println("2. TestHarness");
		
		int mode = 0;
		String input = null;
		try{
			input = in.nextLine();
			mode = Integer.valueOf(in.nextLine());
		}
		catch(NumberFormatException ex)
		{
			System.out.println("********************");
			System.out.println("\nError:" + input + " is not a valid option");
			getMode();
		}
		
		return mode;
	}
}
