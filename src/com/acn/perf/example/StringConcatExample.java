package com.acn.perf.example;

import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

public class StringConcatExample {

	private static final int LOOP_MAX_VAL = 20000;
	
	//string buffer vs string builder vs string concat
	public static void executeStringConcat()
	{
		String var = "";
		long start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			var += i;
		}
		Log.logPerf(LogDictionary.STRING_CONCAT.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);		
		
		StringBuffer sbuf = new StringBuffer();
		start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			sbuf.append(i);
		}
		var = sbuf.toString();
		Log.logPerf(LogDictionary.STRING_BUFFER.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);
		
		StringBuilder sbuild = new StringBuilder();
		start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			sbuild.append(i);
		}
		var = sbuild.toString();
		Log.logPerf(LogDictionary.STRING_BUILDER.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);	
	}
	
	//string buffer(size) vs string builder(size) vs string concat
	public static void executeStringConcatOptimized()
	{
		String var = "";
		long start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			var += i;
		}
		Log.logPerf(LogDictionary.STRING_CONCAT.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);		
		
		StringBuffer sbuf = new StringBuffer(10000);
		start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			sbuf.append(i);
		}
		var = sbuf.toString();
		Log.logPerf(LogDictionary.STRING_BUFFER.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);		
		
		StringBuilder sbuild = new StringBuilder(10000);
		start = System.nanoTime();
		for(int i = 0; i < LOOP_MAX_VAL; i++)
		{
			sbuild.append(i);
		}
		var = sbuild.toString();
		Log.logPerf(LogDictionary.STRING_BUILDER.replace("?", String.valueOf(LOOP_MAX_VAL)), System.nanoTime() - start);	
	}
}
