package com.acn.perf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.acn.perf.normal.HashDictionaryNormal;
import com.acn.perf.optimized.HashDictionaryOptimized;

public class TestHarness {

	public static final int EXECUTIONS = 10;
	public static final String LOG_PATH = "log.log";
	
	public static void main(String args[])
	{
		testNormalSetup(null);
		testOptimizedSetup(null);
	}
	
	public static void testNormalSetup(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		
		for(int i=0; i < loopCount; i++)
		{
			HashDictionaryNormal.testHookSetup();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH));
		}
		
		System.out.println("### Normal Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
	
	public static void testOptimizedSetup(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		for(int i=0; i < loopCount; i++)
		{
			HashDictionaryOptimized.testHookSetup();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH));
		}
		
		System.out.println("### Optimized Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
}
