package com.epam.preprod.tymchenko.service.bean.factory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.service.bean.Bean;
import com.epam.preprod.tymchenko.service.bean.SortFormBean;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.Constants;

public class SortFormBeanCreator extends RequestBeanCreator{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	public Bean create(HttpServletRequest request) {
		LOG.info("SortFormBeanCreator.create(HttpServletRequest request)");

		SortFormBean bean = new SortFormBean();

		bean.setSearch(request.getParameter(Constants.SORT_FORM_SEARCH));
		bean.setMinPrice(request.getParameter(Constants.SORT_FORM_MIN_PRICE));
		bean.setMaxPrice(request.getParameter(Constants.SORT_FORM_MAX_PRICE));
		bean.setOnPage(request.getParameter(Constants.SORT_FORM_ON_PAGE));
		bean.setTypes(fillListWithArray(request.getParameterValues(Constants.SORT_FORM_TYPES)));
		bean.setBrands(fillListWithArray(request.getParameterValues(Constants.SORT_FORM_BRANDS)));
		bean.setSorting(request.getParameter(Constants.SORT_FORM_SORT));
		bean.setPageNumber(request.getParameter(Constants.SORT_FORM_PAGE_NUMBER));
		
		return bean;
	}
	
	private static List<String> fillListWithArray(String[] array){
		List<String> list = new ArrayList<>();
		
		if (array != null && array.length > 0) {
			for (String line : array) {
				list.add(line);
			}
		}
		return list;
	}
}
