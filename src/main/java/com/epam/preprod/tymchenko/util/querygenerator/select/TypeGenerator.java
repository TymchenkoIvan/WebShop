package com.epam.preprod.tymchenko.util.querygenerator.select;

import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public class TypeGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		if(bean.getTypes() == null || bean.getTypes().size() == 0){
			return next.append(bean, string);
		}

		string = setQuery(bean, string);
		
		return next.append(bean, string);
	}
	
	private StringBuilder setQuery(SortFormBean bean, StringBuilder string){
		string.append(" AND (");
		
		for(int i=0; i<bean.getTypes().size(); i++){
			if(i == 0){
				string.append("types.name='" + bean.getTypes().get(i) + "'");
			} else{
				string.append(" OR types.name='" + bean.getTypes().get(i) + "'");
			}
		}
		
		return string.append(")");
	}
}
