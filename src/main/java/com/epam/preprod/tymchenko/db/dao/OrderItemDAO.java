package com.epam.preprod.tymchenko.db.dao;

import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;

public interface OrderItemDAO {
	
	void add(OrderInfoBean bean, long orderId) throws DataException;

}
