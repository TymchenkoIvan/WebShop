package com.epam.preprod.tymchenko.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtils {

	public static void close(Connection connection) {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void rollback(Connection connection) {
		if (connection == null) {
			return;
		}
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}