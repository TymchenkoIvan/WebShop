package com.epam.preprod.tymchenko.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.epam.preprod.tymchenko.constant.DeployConstants;

/**
 * Contains connections for MySql database.
 * 
 * @author Ivan_Tymchenko
 */
public class MySqlConnector {
	
	private DataSource dataSource;
	
	public MySqlConnector(){
		Context initContext;
		try {
			initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup(DeployConstants.CONTEXT_XML_DATA_SOURCE );
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
