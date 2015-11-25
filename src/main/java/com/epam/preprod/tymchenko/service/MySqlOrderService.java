package com.epam.preprod.tymchenko.service;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.db.JdbcTransactionManager;
import com.epam.preprod.tymchenko.db.Operation;
import com.epam.preprod.tymchenko.db.TransactionManager;
import com.epam.preprod.tymchenko.db.dao.MySqlOrderDAO;
import com.epam.preprod.tymchenko.db.dao.MySqlOrderItemDAO;
import com.epam.preprod.tymchenko.db.dao.OrderDAO;
import com.epam.preprod.tymchenko.db.dao.OrderItemDAO;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;
import com.epam.preprod.tymchenko.util.ClassNameUtil;

public class MySqlOrderService implements OrderService{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private OrderDAO orderDao;
	private OrderItemDAO orderItemDao;
	private TransactionManager manager;

	public MySqlOrderService() {
		orderDao = new MySqlOrderDAO();
		orderItemDao = new MySqlOrderItemDAO();
		manager = new JdbcTransactionManager();
	}

	public long addAllOrderInfo(final OrderInfoBean bean) throws DataException{
		LOG.info("ProductService.addAllOrderInfo(OrderInfoBean bean)");
		
		return manager.execute(new Operation<Long>(){
			
			@Override
			public Long execute() throws DataException{
				long orderId = orderDao.add(bean);
				
				orderItemDao.add(bean, orderId);
				
				return orderId;
			}
		});
	}
}
