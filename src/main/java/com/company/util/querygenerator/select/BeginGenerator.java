package com.company.util.querygenerator.select;

import com.company.service.bean.SortFormBean;

public class BeginGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		string.append(QueryConstants.BEGIN);
		
		return next.append(bean, string);
	}
}
