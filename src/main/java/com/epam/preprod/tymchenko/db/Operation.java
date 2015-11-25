package com.epam.preprod.tymchenko.db;

import com.epam.preprod.tymchenko.exception.DataException;

/**
 * Database operation interface
 * 
 * @author Ivan_Tymchenko
 * @param <E> return object type
 */
public interface Operation<E> {
	E execute() throws DataException;
}
