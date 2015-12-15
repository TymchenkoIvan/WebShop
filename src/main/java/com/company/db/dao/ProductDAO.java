package com.company.db.dao;

import java.util.List;

import com.company.entity.Product;
import com.company.exception.DataException;

public interface ProductDAO {
	
	public Product getProductById(long id) throws DataException;
	
	public List<Product> getProductsByQuery(String query) throws DataException;
	
	public Integer getProductsCountByQuery(String query) throws DataException;
	
	public List<Product> getAllProducts() throws DataException;
}
