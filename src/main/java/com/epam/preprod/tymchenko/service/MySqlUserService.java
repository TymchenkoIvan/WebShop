package com.epam.preprod.tymchenko.service;

import com.epam.preprod.tymchenko.db.JdbcTransactionManager;
import com.epam.preprod.tymchenko.db.Operation;
import com.epam.preprod.tymchenko.db.TransactionManager;
import com.epam.preprod.tymchenko.db.dao.MySqlUserDAO;
import com.epam.preprod.tymchenko.db.dao.UserDAO;
import com.epam.preprod.tymchenko.entity.Role;
import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.service.bean.RegistrationFormBean;

/**
 * Service works with user.
 * 
 * @author Ivan_Tymchenko
 */
public class MySqlUserService implements UserService{

	private UserDAO userDao;
	private TransactionManager manager;

	public MySqlUserService() {
		userDao = new MySqlUserDAO();
		manager = new JdbcTransactionManager();
	}

	public boolean isLoginUnique(final String login) throws DataException {
		return manager.execute(new Operation<Boolean>(){
			
			@Override
			public Boolean execute() throws DataException{
				return userDao.isLoginUnique(login);
			}
		});
	}
	
	public User getUserFromBean(RegistrationFormBean bean) {
		User user = new User();

		user.setRoleId(Role.getKeyByName(Role.USER.getName()));
		user.setFirstName(bean.getFirstName());
		user.setLastName(bean.getLastName());
		user.setLogin(bean.getLogin());
		user.setEmail(bean.getMail());
		user.setPassword(bean.getPassword());
		user.setPassword(bean.getPassword());
		user.setNotifications(bean.getNotifications());

		return user;
	}

	public void addUser(final User user) throws DataException {
		manager.execute(new Operation<Void>(){
			@Override
			public Void execute() throws DataException{
				userDao.add(user);
				return null;
			}
		});
		
		if (user.getNotifications() != null && user.getNotifications().length > 0) {
			addNotifications(getUserByLogin(user.getLogin()), user.getNotifications());
		}
	}
	
	public User getUserByLogin(final String login) throws DataException {
		User user = manager.execute(new Operation<User>(){

			@Override
			public User execute() throws DataException{
				return userDao.getUserByLogin(login);
			}
		});
		
		return user;
	}
	
	public boolean verify(final String login, final String password) throws ValidationException, DataException {
		User user = manager.execute(new Operation<User>(){

			@Override
			public User execute() throws DataException{
				return userDao.getUserByLogin(login);
			}
		});

		return user != null && login.equals(user.getLogin()) && password.equals(user.getPassword());
	}
	
	private void addNotifications(final User user, final String[] nots) throws DataException{
		manager.execute(new Operation<Void>(){
			
			@Override
			public Void execute() throws DataException{
				userDao.addUsersNotifications(user, nots);
				
				return null;
			}
		});
	}
}
