package com.acn.perf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.acn.perf.example.QueryOptimizationExample;
import com.acn.perf.example.StringConcatExample;
import com.acn.perf.log.LogDictionary;
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
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, LogDictionary.HASH_DICTIONARY_LOG_ENTRIES));
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
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, LogDictionary.HASH_DICTIONARY_LOG_ENTRIES));
		}
		
		System.out.println("### Optimized Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
	
	public static void testStringConcatExample(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		for(int i=0; i < loopCount; i++)
		{
			StringConcatExample.executeStringConcat();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, LogDictionary.STRING_CONCAT_LOG_ENTRIES));
		}
		
		System.out.println("### Normal Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
	
	public static void testStringConcatOptimizedExample(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		for(int i=0; i < loopCount; i++)
		{
			StringConcatExample.executeStringConcatOptimized();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, LogDictionary.STRING_CONCAT_LOG_ENTRIES));
		}
		
		System.out.println("### Optimized Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
	
	public static void testNormalQueryExample(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		for(int i=0; i < loopCount; i++)
		{
			QueryOptimizationExample.executeStandardQuery(i+1);
			QueryOptimizationExample.clearCache();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, new String[]{LogDictionary.QUERY_FOR_HASH_WITH_SOURCE_LENGTH}));
		}
		
		System.out.println("### Normal Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
	
	public static void testOptimizedQueryExample(Integer exec)
	{
		int loopCount = EXECUTIONS;
		if(exec != null)
		{
			loopCount = exec;
		}
		
		List<Map<String, Long>> results = new ArrayList<Map<String, Long>>();
		for(int i=0; i < loopCount; i++)
		{
			QueryOptimizationExample.executeOptimizedQuery(i+1);
			QueryOptimizationExample.clearCache();
			results.add(TestAnalyzer.getResultsFromLog(LOG_PATH, new String[]{LogDictionary.QUERY_FOR_HASH_WITH_SOURCE_LENGTH}));
		}
		
		System.out.println("### Optimized Execution Results ###");
		TestAnalyzer.writeTestResults(results);
	}
}
