package com.company.db;

import com.company.exception.DataException;

/**
 * 
 * @author Ivan_Tymchenko
 */
public interface TransactionManager {
	
	<T> T execute(Operation<T> operation, int transactionLevel) throws DataException;
	
	<T> T execute(Operation<T> operation) throws DataException;
}
