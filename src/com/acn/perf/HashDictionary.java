package com.acn.perf;

import java.util.Scanner;

import com.acn.perf.log.Log;
import com.acn.perf.normal.HashDictionaryNormal;
import com.acn.perf.optimized.HashDictionaryOptimized;

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
