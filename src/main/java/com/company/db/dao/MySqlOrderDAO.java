package com.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.company.db.JdbcHolder;
import com.company.exception.DataException;
import com.company.service.bean.OrderInfoBean;
import com.company.tymchenko.util.ClassNameUtil;

public class MySqlOrderDAO implements OrderDAO{
	
	private  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String INSERT_NEW_ORDER = "INSERT INTO orders VALUES(DEFAULT, ?, ?, ?, 1, 'added by user', ?);";
	
	public MySqlOrderDAO() {
	}

	public long add(OrderInfoBean bean) throws DataException{
		LOG.info("MySqlOrderDAO.add(OrderInfoBean bean)");
		
		Connection connection = JdbcHolder.getConnection();
		long id = -1;
		
		try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_ORDER)) {
			
			st.setLong(1, bean.getUserId());
			st.setLong(2, bean.getPaymentId());
			st.setLong(3, bean.getDeliveryId());
			st.setString(4, simpleDateFormat.format(new Date()));
			st.executeUpdate();
			
	        try(ResultSet rs = st.getGeneratedKeys()){
	        	if (rs.next()){
		            id = rs.getLong(1);
		        }
	        }

			connection.commit();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			
			e.printStackTrace();
			throw new DataException(e.getMessage());
		}
		
		LOG.info("Order id: "+id);
		return id;
	}
}
