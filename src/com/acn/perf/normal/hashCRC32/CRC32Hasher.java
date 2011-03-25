package com.acn.perf.normal.hashCRC32;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import com.acn.perf.normal.log.Log;

public class CRC32Hasher {

	public static Map<String, Long> hashWords(List<String> words)
	{
		//TODO - predefine size
		Map<String, Long> wordHash = new HashMap<String, Long>();
		long start = System.nanoTime();		
		for(String word : words)
		{
			wordHash.put(word, hashString(word));
		}
		Log.log("Time taken to hash in crc32:" + (System.nanoTime() - start));			
		
		return wordHash;
	}
	
	//TODO - c
	private static Long hashString(String input)
	{
		CRC32 newCRC = new CRC32();
		try
		{
			newCRC.update(input.getBytes("UTF-8"));
		}
		catch(Exception ex)
		{
			Log.log("Error generating hash value", ex);
		}
	
		return newCRC.getValue();
	}
}
