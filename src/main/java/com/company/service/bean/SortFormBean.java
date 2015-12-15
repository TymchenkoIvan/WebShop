package com.company.service.bean;

import java.util.List;

/**
 * Bean object contains fields from sort form.
 * 
 * @author Ivan_Tymchenko
 */
public class SortFormBean implements Bean{
	
	private static final long serialVersionUID = 2267055308179934053L;
	
	private String search;
	private String minPrice;
	private String maxPrice;
	private String onPage;
	private String sorting;
	private String pageNumber;
	private List<String> types;
	private List<String> brands;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getOnPage() {
		return onPage;
	}
	public void setOnPage(String onPage) {
		this.onPage = onPage;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public List<String> getBrands() {
		return brands;
	}
	public void setBrands(List<String> brands) {
		this.brands = brands;
	}
	public String getSorting() {
		return sorting;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
}
