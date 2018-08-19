package com.example.shoppingcart;

import java.util.HashMap;
import java.util.Map;

public class Site {

	public Site() {
	}
	
	public static void main(String[] args) {
		Product product= new Product("salt", 10);
		Product product1= new Product("sugar", 50);
		Map<Product, Integer> map= new HashMap<>();
		map.put(product, 2);
		map.put(product1, 12);
		try {
			Inventory.getInstance().addItems(map);
			Cart cart = new Cart();
			cart.addItem(product, 1);
			cart.addItem(product1, 1);
			//cart.emptyCart();
			//cart.removeItem(product);
			cart.generateInvoice();
			cart.checkOut();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
