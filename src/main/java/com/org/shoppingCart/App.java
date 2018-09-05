package com.org.shoppingCart;

import com.org.shoppingCart.service.CartService;
/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{

		CartService cartService = new CartService();
		cartService.fetchCartItems("CartItems.json");
		cartService.fetchCategories("Categories.json");
		cartService.fetchFlatDiscountSlabs("FlatDiscSlabs.json");
		cartService.displayItemizedBill();
	}
}
