package com.epam.preprod.tymchenko.db.dao;

import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.exception.DataException;

/**
 * Contains methods for working with User entity in database.
 * 
 * @author Ivan_Tymchenko
 */
public interface UserDAO {
	
	public void add(User user) throws DataException;

	public void addUsersNotifications(User user, String[] nots) throws DataException;
	
	public boolean isLoginUnique(String login) throws DataException;

	public User getUserByLogin(String login) throws DataException;
}