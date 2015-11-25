package com.epam.preprod.tymchenko.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.db.JdbcTransactionManager;
import com.epam.preprod.tymchenko.db.Operation;
import com.epam.preprod.tymchenko.db.TransactionManager;
import com.epam.preprod.tymchenko.db.dao.MySqlProductDAO;
import com.epam.preprod.tymchenko.db.dao.ProductDAO;
import com.epam.preprod.tymchenko.entity.Product;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.ProductBean;
import com.epam.preprod.tymchenko.service.bean.SortFormBean;
import com.epam.preprod.tymchenko.util.BeanUtil;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.util.count.CountQueryGenerator;
import com.epam.preprod.tymchenko.util.querygenerator.select.ChainGenerator;

public class MySqlProductService implements ProductService{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private ProductDAO productDao;
	private TransactionManager manager;
	
	private String selectQuery;
	private String countQuery;

	public MySqlProductService() {
		productDao = new MySqlProductDAO();
		manager = new JdbcTransactionManager();
	}
	
	public String getSelectQuery() {
		return selectQuery;
	}
	
	public String getCountQuery() {
		return countQuery;
	}

	public ProductBean getProductById(final long id) throws DataException{
		LOG.info("ProductService.getProductById(final long id)");
		
		Product product = manager.execute(new Operation<Product>(){
			
			@Override
			public Product execute() throws DataException{
				return productDao.getProductById(id);
			}
		});
		
		return BeanUtil.createProductBeanFromProduct(product);
	}
	
	public List<ProductBean> getAllProducts() throws DataException{
		LOG.info("ProductService.getAllProducts()");
		
		List<Product> products = manager.execute(new Operation<List<Product>>(){
			
			@Override
			public List<Product> execute() throws DataException{
				return productDao.getAllProducts();
			}
		});
		
		return BeanUtil.createProductBeanList(products);
	}
	
	public void generateSelectQuery(SortFormBean bean, int page, int onPage){
		selectQuery = new ChainGenerator(page, onPage).append(bean).toString();
	}
	
	public List<ProductBean> getProductsByQuery() throws DataException{
		LOG.info("getProductsByQuery()");
		LOG.info(selectQuery);
		
		List<Product> products = manager.execute(new Operation<List<Product>>(){
			
			@Override
			public List<Product> execute() throws DataException{
				return productDao.getProductsByQuery(selectQuery);
			}
		});
		
		return BeanUtil.createProductBeanList(products);
	}
	
	public int getProductsCountByQuery() throws DataException{
		LOG.info("getProductsCountByQuery()");
		
		countQuery = CountQueryGenerator.generate(selectQuery);
		LOG.info(countQuery);
		
		int count = manager.execute(new Operation<Integer>(){
			
			@Override
			public Integer execute() throws DataException{
				return productDao.getProductsCountByQuery(countQuery);
			}
		});
		
		return count;
	}
}