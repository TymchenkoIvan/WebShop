package com.epam.preprod.tymchenko.util.querygenerator.select;

import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public class SearchGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		string.append(" WHERE products.name LIKE '%");
		
		if(bean.getSearch() == null || bean.getSearch().isEmpty()){
			string.append("%' ");
			
			return next.append(bean, string);
		}
		string.append(bean.getSearch() + "%' ");
		
		return next.append(bean, string);
	}
}