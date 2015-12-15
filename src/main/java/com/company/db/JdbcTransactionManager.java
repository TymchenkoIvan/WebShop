package com.company.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.exception.DataException;
import com.company.exception.Messages;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.tymchenko.util.DbUtils;

import org.apache.log4j.Logger;

/**
 * TransactionManager realization for JDBC
 * 
 * @author Ivan_Tymchenko
 *
 */
public class JdbcTransactionManager implements TransactionManager {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private MySqlConnector connector;

	public JdbcTransactionManager() {
		connector = new MySqlConnector();
	}

	@Override
	public <T> T execute(Operation<T> operation, int transactionLevel) throws DataException {
		Connection connection = null;
		T res = null;
		try{
			connection = connector.getConnection();
			
			if(connection == null){
				throw new DataException(Messages.DATA_CONN_NULL);
			}
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(transactionLevel);
			
			JdbcHolder.setConnection(connection);
			
			res = operation.execute();
			
			connection.commit();
		} catch(SQLException e) {
			DbUtils.rollback(connection);
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		} finally{
			JdbcHolder.setConnection(null);
			DbUtils.close(connection);
		}
		return res;
	}
	
	@Override
	public <T> T execute(Operation<T> operation) throws DataException {
		Connection connection = null;
		T res = null;
		try{
			connection = connector.getConnection();
			
			if(connection == null){
				throw new DataException(Messages.DATA_CONN_NULL);
			}
			JdbcHolder.setConnection(connection);
			
			res = operation.execute();
			
			connection.commit();
		} catch(SQLException e) {
			DbUtils.rollback(connection);
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		} finally{
			JdbcHolder.setConnection(null);
			DbUtils.close(connection);
		}
		return res;
	}
}
