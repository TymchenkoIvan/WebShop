package com.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.company.constant.DBConstants;
import com.company.db.JdbcHolder;
import com.company.db.dao.UserDAO;
import com.company.entity.User;
import com.company.exception.DataException;
import com.company.tymchenko.util.ClassNameUtil;

/**
 * Implements UserDAO interface and works with MySql database.
 * 
 * @author Ivan_Tymchenko
 */
public class MySqlUserDAO implements UserDAO {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String INSERT_NEW_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_NEW_NOTIFACATION = "INSERT INTO subs VALUES(DEFAULT, ?, ?);";

	public MySqlUserDAO() {
	}

	public void add(User user) throws DataException{
		Connection connection = JdbcHolder.getConnection();
		
		try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_USER)) {
			st.setString(1, user.getLogin());
			st.setLong(2, user.getRoleId());
			st.setString(3, user.getFirstName());
			st.setString(4, user.getLastName());
			st.setString(5, user.getEmail());
			st.setString(6, user.getPassword());
			st.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
	}

	@Override
	public void addUsersNotifications(User user, String[] nots) throws DataException{
		Connection connection = JdbcHolder.getConnection();
		
		try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_NOTIFACATION)) {
			for (String line : nots) {
				st.setLong(1, user.getId());
				st.setString(2, line);
				st.executeUpdate();
				connection.commit();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
	}

	public boolean isLoginUnique(String login) throws DataException{
		Connection connection = JdbcHolder.getConnection();
		
		try (PreparedStatement st = connection.prepareStatement(GET_USER_BY_LOGIN)) {
			st.setString(1, login);
			try (ResultSet rs = st.executeQuery()) {
				return !rs.next();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
	}

	public User getUserByLogin(String login) throws DataException{
		Connection connection = JdbcHolder.getConnection();
		
		try (PreparedStatement st = connection.prepareStatement(GET_USER_BY_LOGIN)) {
			st.setString(1, login);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return executeUser(rs);
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return null;
	}

	private User executeUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(DBConstants.ID));
		user.setRoleId(rs.getLong(DBConstants.USER_ROLE_ID));
		user.setLogin(rs.getString(DBConstants.USER_LOGIN));
		user.setFirstName(rs.getString(DBConstants.USER_FIRST_NAME));
		user.setLastName(rs.getString(DBConstants.USER_LAST_NAME));
		user.setEmail(rs.getString(DBConstants.USER_EMAIL));
		user.setPassword(rs.getString(DBConstants.USER_PASS));
		return user;
	}
}