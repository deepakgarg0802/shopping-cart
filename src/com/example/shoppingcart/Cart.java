package com.example.shoppingcart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Product, Integer> cartItems;
	
	public Cart() {
		this.cartItems= new HashMap<Product, Integer>();
	}
	
	public void addItem(Product product, Integer amount) throws CartException{
		
		if(amount<=0) {
			throw new CartException("Quantity not proper");
		}
		Integer oldCount = this.cartItems.getOrDefault(product,0);
		this.cartItems.put(product, amount+oldCount);
	}
	
	public void removeItem(Product product) throws CartException{
		
		if(cartItems.containsKey(product)) {
			cartItems.remove(product);
		}
		else {
			throw new CartException("Product not present in Cart");
		}
	}
	
	public void generateInvoice() {
		
		double totalPrice=0;
		
		for(Map.Entry<Product, Integer> m:this.cartItems.entrySet()){
			
			String productName= m.getKey().getName();
			int qty= m.getValue();
			double price= m.getKey().getPrice() * qty;
			
			totalPrice += price;
			
			System.out.println(productName + " " + qty + " " + String.format("%.2f",price));
		}
		
		System.out.println("Total price: " + String.format("%.2f",totalPrice));
	}
	
	public void checkOut() throws Exception{
		Inventory inventory= Inventory.getInstance();
		inventory.removeItems(cartItems);
		this.cartItems= new HashMap<Product,Integer>();
	}
	
	public void emptyCart() {
		this.cartItems= new HashMap<Product,Integer>();
	}
}
