package com.acn.perf.normal.io.reader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.acn.perf.log.Log;
import com.acn.perf.log.LogDictionary;

public class DictionaryReader {

		public static List<String> getWordsFromDictionary(String path)
		{
			//TODO for optimize - predefine initial size of arraylist
			List<String> words = new ArrayList<String>();
			File f1 = new File(path);
			FileInputStream fis = null;
			DataInputStream dis = null;
			try{
				fis = new FileInputStream(f1);
				//TODO for optimize - use Buffered IO
				dis = new DataInputStream(fis);
				
				// dis.available() returns 0 if the file does not have more lines.
				long start = System.nanoTime();
			    while (dis.available() != 0) {
			    	words.add(dis.readLine());
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
					dis.close();
					fis.close();
				}
				catch(Exception ex)
				{
					Log.log("Error closing dis/fis connections of dictionary reader", ex);
				}
			}
			
			return words;
		}
}
