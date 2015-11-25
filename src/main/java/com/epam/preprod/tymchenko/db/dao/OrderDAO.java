package com.epam.preprod.tymchenko.db.dao;

import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;

public interface OrderDAO {
	
	long add(OrderInfoBean bean) throws DataException;

}
