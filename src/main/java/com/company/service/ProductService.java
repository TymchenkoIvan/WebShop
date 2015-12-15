package com.company.service;

import java.util.List;

import com.company.entity.Product;
import com.company.exception.DataException;
import com.company.service.bean.ProductBean;
import com.company.service.bean.SortFormBean;

public interface ProductService extends Service<Product>{
	
	String getSelectQuery();
	
	String getCountQuery();
	
	ProductBean getProductById(final long id) throws DataException;
	
	List<ProductBean> getAllProducts() throws DataException;
	
	void generateSelectQuery(SortFormBean bean, int page, int onPage);
	
	List<ProductBean> getProductsByQuery() throws DataException;
	
	int getProductsCountByQuery() throws DataException;

}
