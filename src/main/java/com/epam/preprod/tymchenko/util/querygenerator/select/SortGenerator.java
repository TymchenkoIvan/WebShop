package com.epam.preprod.tymchenko.util.querygenerator.select;

import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public class SortGenerator extends Generator {

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {

		String sort = bean.getSorting();

		if (sort == null || sort.isEmpty()) {
			return next.append(bean, string);
		} else {
			return next.append(bean, setString(sort, string));
		}
	}

	private StringBuilder setString(String sort, StringBuilder string) {
		
		switch (sort) {
			case "min-max":
				string.append(" ORDER BY price");
				break;
			case "max-min":
				string.append(" ORDER BY price DESC");
		}
		
		return string;
	}
}