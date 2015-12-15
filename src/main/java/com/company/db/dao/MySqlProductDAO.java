package com.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.constant.DBConstants;
import com.company.db.JdbcHolder;
import com.company.entity.Product;
import com.company.exception.DataException;
import com.company.util.ClassNameUtil;

public class MySqlProductDAO implements ProductDAO{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
	private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";

	@Override
	public Product getProductById(long id) throws DataException {
		Connection connection = JdbcHolder.getConnection();
		Product product = new Product();
		
		try (PreparedStatement st = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
			st.setLong(1, id);
			
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return executeProduct(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return product;
	}

	@Override
	public List<Product> getProductsByQuery(String query) throws DataException {
		Connection connection = JdbcHolder.getConnection();
		List<Product> products = new ArrayList<>();
		
		try (PreparedStatement st = connection.prepareStatement(query)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					products.add(executeProduct(rs));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return products;
	}

	@Override
	public List<Product> getAllProducts() throws DataException {
		Connection connection = JdbcHolder.getConnection();
		List<Product> products = new ArrayList<>();
		
		try (PreparedStatement st = connection.prepareStatement(GET_ALL_PRODUCTS)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					products.add(executeProduct(rs));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return products;
	}
	

	@Override
	public Integer getProductsCountByQuery(String query) throws DataException {
		Connection connection = JdbcHolder.getConnection();
		Integer count = 0;
		
		try (PreparedStatement st = connection.prepareStatement(query)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					count = Integer.parseInt(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return count;
	}

	private Product executeProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		
		product.setId(rs.getLong(DBConstants.ID));
		product.setTypeId(rs.getLong(DBConstants.PRODUCT_TYPE_ID));
		product.setBrandId(rs.getLong(DBConstants.PRODUCT_BRAND_ID));
		product.setPrice(rs.getBigDecimal(DBConstants.PRODUCT_PRICE));
		product.setName(rs.getString(DBConstants.PRODUCT_NAME));
		product.setText(rs.getString(DBConstants.PRODUCT_TEXT));
		
		return product;
	}
}