package com.epam.preprod.tymchenko.db;

import com.epam.preprod.tymchenko.exception.DataException;

/**
 * 
 * @author Ivan_Tymchenko
 */
public interface TransactionManager {
	
	<T> T execute(Operation<T> operation, int transactionLevel) throws DataException;
	
	<T> T execute(Operation<T> operation) throws DataException;
}
