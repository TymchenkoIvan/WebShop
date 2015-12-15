package com.company.util.querygenerator.select;

import com.company.service.bean.SortFormBean;

public class ChainGenerator{
	
	private BeginGenerator begin;
	private SearchGenerator search;
	private PriceGenerator price;
	private TypeGenerator type;
	private BrandGenerator brand;
	private SortGenerator sort;
	private LimitGenerator limit;
	private EndGenerator end;
	
	public ChainGenerator(int page, int onPage){
		
		begin = new BeginGenerator();
		search = new SearchGenerator();
		price = new PriceGenerator();
		type = new TypeGenerator();
		brand = new BrandGenerator();
		sort = new SortGenerator();
		limit = new LimitGenerator(page, onPage);
		end = new EndGenerator();
		
		begin.setNext(search);
		search.setNext(price);
		price.setNext(type);
		type.setNext(brand);
		brand.setNext(sort);
		sort.setNext(limit);
		limit.setNext(end);
	} 

	public StringBuilder append(SortFormBean bean) {
		return begin.append(bean, new StringBuilder());
	}
}
