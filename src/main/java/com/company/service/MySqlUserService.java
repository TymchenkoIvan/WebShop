package com.company.service;

import com.company.db.JdbcTransactionManager;
import com.company.db.Operation;
import com.company.db.TransactionManager;
import com.company.db.dao.MySqlUserDAO;
import com.company.db.dao.UserDAO;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.exception.DataException;
import com.company.exception.ValidationException;
import com.company.service.bean.RegistrationFormBean;

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
