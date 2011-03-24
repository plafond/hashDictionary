package com.acn.perf.normal.reader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
			    while (dis.available() != 0) {

			    	// this statement reads the line from the file and print it to
			    	// the console.
			    	words.add(dis.readLine());
			    }
			}
			catch(Exception ex)
			{
				
			}
			finally{
				try{
				dis.close();
				fis.close();
				}
				catch(Exception ex){}
			}
			
			return words;
		}
}
