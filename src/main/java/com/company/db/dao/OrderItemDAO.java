package com.company.db.dao;

import com.company.exception.DataException;
import com.company.service.bean.OrderInfoBean;

public interface OrderItemDAO {
	
	void add(OrderInfoBean bean, long orderId) throws DataException;

}
