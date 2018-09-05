package com.org.shoppingCart.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;
import com.org.shoppingCart.model.Item;
import com.org.shoppingCart.service.CartService;
import com.org.shoppingCart.utils.ShoppingCartUtils;
public class ShoppingCartTest {

	List<Item> items;
	List<Category> categories; 
	List<FlatDiscountSlab> slabs;

	@Before
	public void setUp() throws Exception {
		CartService cartService = new CartService();
		items = cartService.fetchCartItems("CartItems.json");
		categories = cartService.fetchCategories("Categories.json");
		slabs = cartService.fetchFlatDiscountSlabs("FlatDiscSlabs.json");
	}

	@Test 
	public void testItemsCount(){
		assertEquals("There are only 3 items in the cart.",items.size(), 3);
	}

	@Test
	public void testCategoryCount(){
		assertNotNull("Categories should not be null", categories);
	}

	@Test
	public void testFlatDiscountSlab(){
		assertNotNull("Flat Discount Slabs should not be null", slabs);
	}
	@Test
	public void testCategoryForGivenItemPresent(){
		Item shirtItem = items.get(0);
		Category catgory = ShoppingCartUtils.getCategoryById(categories, shirtItem.getItemCategory());
		assertEquals("Wrong category selected : ",catgory.getCategoryName(), "Apperrel");
		assertEquals("Wrong category Id ", shirtItem.getItemCategory(), catgory.getCategoryId());
	}
	@Test
	public void testCategoryForGivenItemCategoryIDNotPresent(){
		String categoryID = items.get(items.size()-1).getItemCategory();
		Category catgory = ShoppingCartUtils.getCategoryById(categories, categoryID);
		assertNull("category present with ID " + categoryID, catgory);
	}

	@Test
	public void testItemPurchasePrice() {
		Item shirtItem = items.get(0);
		Category catgory = ShoppingCartUtils.getCategoryById(categories, shirtItem.getItemCategory());
		BigDecimal itemTotal = ShoppingCartUtils.getTotalPricePerItem(shirtItem.getUnitPrice(),catgory.getDiscPer(),shirtItem.getQuantity());
		assertEquals("wrong purchase price calculated", new BigDecimal(5400), itemTotal);
	}	

	@Test
	public void testNetBillAmount(){
		BigDecimal grandTotal = new BigDecimal(0);

		for (Item item : items) {

			Category catgory = ShoppingCartUtils.getCategoryById(categories, item.getItemCategory());
			int discount = (catgory != null) ?	catgory.getDiscPer(): 0;
			BigDecimal itemTotal = ShoppingCartUtils.getTotalPricePerItem(item.getUnitPrice(),discount,item.getQuantity());
			grandTotal = grandTotal.add(itemTotal);
		}
		int slabDiscount = ShoppingCartUtils.getFlatDiscByAmount(slabs, grandTotal).getDiscPer();
		BigDecimal total = ShoppingCartUtils.calculateNetBillAmount(grandTotal, slabDiscount);
		assertEquals("wrong Net Bill Amount calculated", 5515.2F, total.floatValue(), 0.001);
	}
}