package com.epam.preprod.tymchenko.service;

import com.epam.preprod.tymchenko.entity.Order;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;

public interface OrderService extends Service<Order>{
	
	long addAllOrderInfo(final OrderInfoBean bean) throws DataException;

}
