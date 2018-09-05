package com.tonyvu.sc.model;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;
import com.org.shoppingCart.model.Item;
import com.org.shoppingCart.service.CartService;
import com.org.shoppingCart.utils.ShoppingCartUtils;
public class ShoppingCartTest {
	
	@Before
	public void setUp() throws Exception {}
	
	@Test
	public void testItemizedBill() {
		CartService cartService = new CartService();
		List<Item> items = cartService.fetchCartItems("CartItems.json");
		List<Category> categories = cartService.fetchCategories("Categories.json");
		List<FlatDiscountSlab> slabs = cartService.fetchFlatDiscountSlabs("FlatDiscSlabs.json");
		assertEquals("error while fetching items size",items.size(), 3);
		assertEquals("error while fetching category size",categories.size(), 5);
		assertEquals("error while fetching slabs size", slabs.size(), 3);
		
		Item shirtItem = items.get(0);
		Category catgory = ShoppingCartUtils.getCategoryById(categories, shirtItem.getItemCategory());
		assertEquals("Wrong category selected : ",catgory.getCategoryName(), "Apperrel");
		assertEquals("Wrong category Id ", shirtItem.getItemCategory(), catgory.getCategoryId());
		
		BigDecimal itemTotal = ShoppingCartUtils.getTotalPricePerItem(shirtItem.getUnitPrice(),catgory.getDiscPer(),shirtItem.getQuantity());
		assertEquals("wrong purchase price calculated", new BigDecimal(5400), itemTotal);
		
		cartService.displayItemizedBill();
		
	}	
}