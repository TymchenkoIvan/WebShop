package com.epam.preprod.tymchenko.service;

import java.util.List;

import com.epam.preprod.tymchenko.entity.Product;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.ProductBean;
import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public interface ProductService extends Service<Product>{
	
	String getSelectQuery();
	
	String getCountQuery();
	
	ProductBean getProductById(final long id) throws DataException;
	
	List<ProductBean> getAllProducts() throws DataException;
	
	void generateSelectQuery(SortFormBean bean, int page, int onPage);
	
	List<ProductBean> getProductsByQuery() throws DataException;
	
	int getProductsCountByQuery() throws DataException;

}
