package com.example.shoppingcart;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

	private Map<Product, Integer> items;
	
	private static Inventory instance;
	
	private Inventory() {
		items= new HashMap<>();
	}
	
	public static Inventory getInstance() {//synchronized
		if(instance == null){
            instance = new Inventory();
        }
        return instance;
	}
	
	public Map<Product, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Product, Integer> items) {
		this.items = items;
	}
	
	
	/**
	 * add the items
	 */
	public void addItems(Map<Product, Integer> items) throws CartException {
		
		for(Map.Entry<Product, Integer> m:items.entrySet()){ 
			Integer newCount= m.getValue();
			Product product = m.getKey();
			Integer oldCount = this.items.getOrDefault(m.getKey(),0);
			
			if(newCount<0) {
				throw new CartException("Count of products removed from inventory should be more than 0");
			}
			newCount += oldCount;
			this.items.put(product, newCount );
		} 
	}

	/**
	 * remove the items
	 */
	public void removeItems(Map<Product, Integer> items) throws CartException{
		
		for(Map.Entry<Product, Integer> m:items.entrySet()){ 
			Integer newCount= m.getValue();
			Product product = m.getKey();
			Integer oldCount = this.items.getOrDefault(m.getKey(),0);
			
			if(newCount<0 || newCount>oldCount) {
				throw new CartException("Count of products removed from inventory should be more");
			}
			oldCount -= newCount;
			this.items.put(product, oldCount);
		} 
	}
	
}
