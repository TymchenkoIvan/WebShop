package com.company.db.dao;

import com.company.entity.User;
import com.company.exception.DataException;

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