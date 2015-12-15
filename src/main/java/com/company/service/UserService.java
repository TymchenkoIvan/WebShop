package com.company.service;

import com.company.entity.User;
import com.company.exception.DataException;
import com.company.exception.ValidationException;
import com.company.service.bean.RegistrationFormBean;

public interface UserService extends Service<User>{
	
	boolean isLoginUnique(final String login) throws DataException;
	
	User getUserFromBean(RegistrationFormBean bean);
	
	void addUser(final User user) throws DataException;
	
	User getUserByLogin(final String login) throws DataException;
	
	boolean verify(final String login, final String password) throws ValidationException, DataException;
}
