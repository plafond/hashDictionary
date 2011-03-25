package com.acn.perf.normal.db.factory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.acn.perf.normal.db.conn.impl.MySQLDBConnection;

public class DBFactory {

	private static final int POOL_SIZE = 6;
	public static final String DATABASE_NAME = "hashDictionary";
	
	private static List<Connection> conPool = new ArrayList<Connection>();
	
	static{
		populatePool();
	}
	
	public static Connection getDBConnection()
	{
		return MySQLDBConnection.getDBConnection();
	}
	
	public static Connection getTopLevelConnection()
	{
		return MySQLDBConnection.getDBConnection();
	}
	
	public static Connection getDBConnectionFromPool()
	{
		try{
			if(conPool != null && conPool.size() > 0)
			{
				return conPool.remove(0);
			}
			else{
				populatePool();
				return conPool.remove(0);
			}
		}
		catch(Exception ex){}
		finally
		{
			populatePool();
		}
		
		return null;
	}
	
	private static void populatePool()
	{
		while (conPool.size() < POOL_SIZE)
		{
			conPool.add(MySQLDBConnection.getDBConnection());
		}
	}
}
