package com.epam.preprod.tymchenko.service;

import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.service.bean.RegistrationFormBean;

public interface UserService extends Service<User>{
	
	boolean isLoginUnique(final String login) throws DataException;
	
	User getUserFromBean(RegistrationFormBean bean);
	
	void addUser(final User user) throws DataException;
	
	User getUserByLogin(final String login) throws DataException;
	
	boolean verify(final String login, final String password) throws ValidationException, DataException;
}
