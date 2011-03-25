package com.acn.perf.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

public class TestAnalyzer {

	private static String[] LOG_ENTRIES =  new String[] {LogDictionary.READ_DICTIONARY, LogDictionary.GENERATE_INSERT_STMS, LogDictionary.INSERT_INTO_TABLE.replace("@", "hashWords"), LogDictionary.INSERT_INTO_TABLE.replace("@", "hashWordSize")}; 
	
	public static Map<String, Long> getResultsFromLog(String path)
	{
		Map<String, Long> results = new HashMap<String, Long>();
		File f1 = new File(path);
		FileReader fr = null;
		BufferedReader br = null;
	
		try{
			fr = new FileReader(f1);
			br = new BufferedReader(fr);
			
			String line = null;
		    while ((line = br.readLine()) != null) {
		    	for(String entry : LOG_ENTRIES)
		    	{
		    		entry = entry.replaceAll("\\?", ".*").trim();
		    		Pattern p = Pattern.compile(entry);
		    		String[] lineSplit = line.split(":");
		    		Matcher m = p.matcher(lineSplit[0] + ":");
		    		if(m.matches())
		    		{
		    			Long val = Long.valueOf(lineSplit[1].trim());
		    			results.put(entry, val);
		    			break;
		    		}
		    	}
		    }
		}
		catch(Exception ex)
		{
			Log.log("Error reading results from log file", ex);
		}
		finally
		{
			try{
				br.close();
				fr.close();
			}
			catch(Exception ex)
			{
				Log.log("Error closing fr/br connections of test analyzer", ex);
			}
		}
		
		return results;
	}
	
	public static void writeTestResults(List<Map<String, Long>> results)
	{
		Map<String, List<Long>> aggregateResults = new HashMap<String, List<Long>>(); 
		
		if(results.size() > 0)
		{
			Map<String, Long> temp = results.get(0);
			for(Map.Entry<String, Long> entry : temp.entrySet())
			{
				for(Map<String, Long> currentResult : results)
				{
					List<Long> times = aggregateResults.get(entry.getKey());
					if(times == null)
					{
						times = new ArrayList<Long>();
					}
					times.add(currentResult.get(entry.getKey()));
					aggregateResults.put(entry.getKey(), times);					
				}
			}
		}
		
		for(Map.Entry<String, List<Long>> temp : aggregateResults.entrySet())
		{
			Long totalTime = 0L;
			List<Long> times = temp.getValue();
			int size = times.size();
			for(Long time : times)
			{
				totalTime += time;
			}
			
			System.out.println(temp.getKey() + " \t was executed:" + size + " times \t Avg execution time (in secs) was:" + (totalTime/(size * 1.0)/1000000000));
		}
	}
}
