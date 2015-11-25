package com.epam.preprod.tymchenko.util.querygenerator.select;

import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public class EndGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		string.append(QueryConstants.END);
		
		return string;
	}

}
