package com.acn.perf.normal.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Log {
	
	private static boolean logPerf = true;
	private static File LOG_FILE = new File("log.log");
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	
	static{
		try{
			fw = new FileWriter(LOG_FILE);
			bw = new BufferedWriter(fw);
		}
		catch(Exception ex)
		{
			System.err.println("failed to create filewriter/bufferedwriter for log!");
		}
	}
	
	public static void log(String output)
	{
		try
		{
			Scanner scanner = new Scanner(output);
			while(scanner.hasNext())
			{
				bw.write(scanner.next() + " ");
			}
		}
		catch(Exception ex)
		{
			Log.log("Failed writing to log file!");
		}
		finally
		{
			try{
				bw.write("\n");
				bw.flush();
			}
			catch(Exception ex){}
		}
	}
	
	public static void log(String output, Exception ex)
	{
		log(output + "\n"+ ex.getMessage());
	}
	
	public static void logPerf(String output)
	{
		if(logPerf)
		{
			log(output);
		}
	}
}
