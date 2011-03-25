package com.acn.perf.optimized.io.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

public class DictionaryReader {

		public static List<String> getWordsFromDictionary(String path)
		{
			//TODO changed to used predefine initial size of arraylist - reduce # of collection grows
			List<String> words = new ArrayList<String>(50000);
			File f1 = new File(path);
			FileReader fr = null;
			BufferedReader br = null;
		
			try{
				fr = new FileReader(f1);
				//TODO - changed to use Buffered IO
				br = new BufferedReader(fr);
				
				String line = null;
				long start = System.nanoTime();
			    while ((line = br.readLine()) != null) {
			    	words.add(line);
			    }
			    Log.logPerf(LogDictionary.READ_DICTIONARY.replace("?", String.valueOf(words.size())), (System.nanoTime() - start));
			}
			catch(Exception ex)
			{
				Log.log("Error reading words from Dictionary", ex);
			}
			finally
			{
				try{
					br.close();
					fr.close();
				}
				catch(Exception ex)
				{
					Log.log("Error closing dis/fis connections of dictionary reader", ex);
				}
			}
			
			return words;
		}
}
