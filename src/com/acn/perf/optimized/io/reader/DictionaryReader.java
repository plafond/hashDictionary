package com.acn.perf.optimized.io.reader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.acn.perf.log.Log;

public class DictionaryReader {

		public static List<String> getWordsFromDictionary(String path)
		{
			//TODO changed to used predefine initial size of arraylist - reduce # of collection grows
			List<String> words = new ArrayList<String>(50000);
			File f1 = new File(path);
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			try{
				fis = new FileInputStream(f1);
				//TODO - changed to use Buffered IO
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
				
				// dis.available() returns 0 if the file does not have more lines.
				long start = System.nanoTime();
			    while (dis.available() != 0) {
			    	words.add(dis.readLine());
			    }
				Log.logPerf("Time taken read " + words.size() + " words:", (System.nanoTime() - start));
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
