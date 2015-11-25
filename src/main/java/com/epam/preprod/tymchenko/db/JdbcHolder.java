package com.epam.preprod.tymchenko.db;

import java.sql.Connection;

/**
 * Holds connection in ThreadLocal
 * 
 * @author Ivan_Tymchenko
 */
public final class JdbcHolder {
	
	private static ThreadLocal<Connection> holder = new ThreadLocal<>();

	public static void setConnection(Connection connection) {
		holder.set(connection);
	}

	public static Connection getConnection() {
		return holder.get();
	}
}
