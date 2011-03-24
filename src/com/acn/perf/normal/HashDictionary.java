package com.acn.perf.normal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import com.acn.perf.normal.db.dbutil.DBUtil;
import com.acn.perf.normal.db.factory.DBFactory;
import com.acn.perf.normal.reader.DictionaryReader;

public class HashDictionary {

	public static final String DICTIONARY_PATH = "/home/plafond/workspace/hashDictionary/dictionary";
	
	public static void main(String args[])
	{
		long start = System.nanoTime();
		List<String> words = DictionaryReader.getWordsFromDictionary(DICTIONARY_PATH);
		Map<String, Long> wordHash = new HashMap<String, Long>();
		try{
			for(String word : words)
			{
				CRC32 newCRC = new CRC32();
				newCRC.update(word.getBytes("UTF-8"));			
				
				wordHash.put(word,newCRC.getValue());
				
			}
		}
		catch(Exception e){}
		
		System.out.println(System.nanoTime() - start);
		System.out.println(wordHash.size());
		
		
		DBUtil.createDatabase(DBFactory.getDBConnection());
		DBUtil.dropDatabase(DBFactory.getDBConnection());
	}
}
