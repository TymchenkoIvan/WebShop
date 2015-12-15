package com.company.util.querygenerator.select;

import com.company.service.bean.SortFormBean;

public class PriceGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		
		string = setMinRange(bean, string);
		string = setMaxRange(bean, string);
		
		return next.append(bean, string);
	}
	
	private StringBuilder setMinRange(SortFormBean bean, StringBuilder string){
		if(bean.getMinPrice() == null || bean.getMinPrice().isEmpty()){
			return string.append(" AND products.price>=0");
		} else{
			return string.append(" AND products.price>="+bean.getMinPrice());
		}
	}
	
	private StringBuilder setMaxRange(SortFormBean bean, StringBuilder string){
		if(bean.getMaxPrice() == null || bean.getMaxPrice().isEmpty()){
			return string.append(" AND products.price<="+Integer.MAX_VALUE);
		} else{
			return string.append(" AND products.price<="+bean.getMaxPrice());
		}
	}
}
