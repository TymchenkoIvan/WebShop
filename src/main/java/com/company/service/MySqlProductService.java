package com.company.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.company.db.JdbcTransactionManager;
import com.company.db.Operation;
import com.company.db.TransactionManager;
import com.company.db.dao.MySqlProductDAO;
import com.company.db.dao.ProductDAO;
import com.company.entity.Product;
import com.company.exception.DataException;
import com.company.service.bean.ProductBean;
import com.company.service.bean.SortFormBean;
import com.company.tymchenko.util.BeanUtil;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.util.count.CountQueryGenerator;
import com.company.util.querygenerator.select.ChainGenerator;

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