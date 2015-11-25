package com.epam.preprod.tymchenko.db.dao;

import java.util.List;

import com.epam.preprod.tymchenko.entity.Product;
import com.epam.preprod.tymchenko.exception.DataException;

public interface ProductDAO {
	
	public Product getProductById(long id) throws DataException;
	
	public List<Product> getProductsByQuery(String query) throws DataException;
	
	public Integer getProductsCountByQuery(String query) throws DataException;
	
	public List<Product> getAllProducts() throws DataException;
}
