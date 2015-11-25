package com.epam.preprod.tymchenko.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.epam.preprod.tymchenko.service.bean.ProductBean;

public class Basket implements Entity{
	
	private Map<ProductBean, Integer> map;
	
	public Basket(){
		map = new HashMap<>();
	}
	
	public void addProduct(ProductBean bean){
		
		if(map.containsKey(bean)){
			int temp = map.get(bean);
			
			map.put(bean, ++temp);
		} else {
			map.put(bean, 1);
		}
	}
	
	public void minusProduct(long id){
		
		for (Map.Entry<ProductBean, Integer> entry : map.entrySet()) {
        	if(entry.getKey().getId() == id){
        		if(entry.getValue() == 1){
        			deleteProduct(id);
        		} else{
        			entry.setValue(entry.getValue() - 1);
        		}
        		return;
        	}
        }
	}
	
	public void deleteProduct(long id){
		for (Map.Entry<ProductBean, Integer> entry : map.entrySet()) {
        	if(entry.getKey().getId() == id){
        		map.remove(entry.getKey());
        		return;
        	}
        }
	}
	
	public int getProductsCount(){
		int count = 0;
		
        for (Map.Entry<ProductBean, Integer> entry : map.entrySet()) {
        	count += entry.getValue();
        }
		return count;
	}

	public BigDecimal getTotalPrice(){
		BigDecimal total = new BigDecimal(0);
		
        for (Map.Entry<ProductBean, Integer> entry : map.entrySet()) {
        	BigDecimal product = entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
        	
        	total = total.add(product);
        }
		return total;
	}

	public void print() {
        for (Map.Entry<ProductBean, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey().getId() + ": " + entry.getValue());
        }
	}

	public Map<ProductBean, Integer> getMap() {
		return map;
	}
}
