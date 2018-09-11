package com.org.shoppingCart;

import com.org.shoppingCart.exception.BusinessValidationException;
import com.org.shoppingCart.service.CartService;

public class App 
{
	public static void main( String[] args )
	{

		CartService cartService = new CartService();
		try {
			cartService.fetchCartItems("CartItems.json");
			cartService.fetchCategories("Categories.json");
			cartService.fetchFlatDiscountSlabs("FlatDiscSlabs.json");
		} catch (BusinessValidationException e) {
			System.err.println(e.getMessage());
		}
		cartService.displayItemizedBill();
	}
}
