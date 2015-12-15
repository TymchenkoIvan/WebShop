package com.company.service;

import org.apache.log4j.Logger;

import com.company.db.JdbcTransactionManager;
import com.company.db.Operation;
import com.company.db.TransactionManager;
import com.company.db.dao.MySqlOrderDAO;
import com.company.db.dao.MySqlOrderItemDAO;
import com.company.db.dao.OrderDAO;
import com.company.db.dao.OrderItemDAO;
import com.company.exception.DataException;
import com.company.service.bean.OrderInfoBean;
import com.company.tymchenko.util.ClassNameUtil;

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
