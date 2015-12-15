package com.company.util.querygenerator.select;

import com.company.service.bean.SortFormBean;

public class BrandGenerator extends Generator{

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		if(bean.getBrands() == null || bean.getBrands().size() == 0){
			return next.append(bean, string);
		}

		string = setQuery(bean, string);
		
		return next.append(bean, string);
	}
	
	private StringBuilder setQuery(SortFormBean bean, StringBuilder string){
		string.append(" AND (");
		
		for(int i=0; i<bean.getBrands().size(); i++){
			if(i == 0){
				string.append("brands.name='" + bean.getBrands().get(i) + "'");
			} else{
				string.append(" OR brands.name='" + bean.getBrands().get(i) + "'");
			}
		}
		
		return string.append(")");
	}
}