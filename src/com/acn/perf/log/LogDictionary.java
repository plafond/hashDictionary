package com.acn.perf.log;

public class LogDictionary {
	
	public static final String GENERATE_INSERT_STMS = "Time taken to generate INSERT SQL statements : ";
	public static final String INSERT_INTO_TABLE = "Time taken to INSERT ? records into @ : ";
	public static final String READ_DICTIONARY = "Time taken read ? words : ";
	public static final String HASH_TIME = "Time taken to hash in crc32 :";
	
	public static final String STRING_CONCAT = "Time taken to contatenate ? strings :";
	public static final String STRING_BUFFER = "Time taken to contatenate ? strings with StringBuffer :";
	public static final String STRING_BUILDER = "Time taken to contatenate ? strings with StringBuilder :";
	
	
	public static String[] HASH_DICTIONARY_LOG_ENTRIES =  new String[] {READ_DICTIONARY, HASH_TIME, GENERATE_INSERT_STMS, INSERT_INTO_TABLE.replace("@", "hashWords"), INSERT_INTO_TABLE.replace("@", "hashWordSize")};
	public static String[] STRING_CONCAT_LOG_ENTRIES =  new String[] {STRING_CONCAT, STRING_BUFFER, STRING_BUILDER};
	
}
