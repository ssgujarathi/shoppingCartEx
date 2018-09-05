package com.org.shoppingCart.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;
import com.org.shoppingCart.model.Item;
import com.org.shoppingCart.utils.DiscountUtils;

public class CartService {

	public void displayItemizedBill(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Initializer initializer = new CategoriesInitializer();
		initializer.initialize("Categories.json");
		List<Category> categories = ((CategoriesInitializer) initializer).getCategories();

		initializer = new FlatDiscountSlabsInitializer();
		initializer.initialize("FlatDiscSlabs.json");
		List<FlatDiscountSlab> slabs= ((FlatDiscountSlabsInitializer) initializer).getSlabs();

		initializer = new CartItemsInitializer();
		initializer.initialize("CartItems.json");
		List<Item> cartItems = ((CartItemsInitializer) initializer).getItems();

		if(cartItems != null && cartItems.size() != 0 ){

			System.out.println("**************************DMart Store********************************");
			System.out.println("Date :" + dateFormat.format(date));
			System.out.println("*********************************************************************");
			System.out.printf("%22s %7s %7s %10s %17s","Item Name",
					"Quantity",
					"Unit Price",
					"Discount" ,
					"Purchase Price");
			System.out.println();
			System.out.println("----------------------------------------------------------------------");
			System.out.println();
			BigDecimal grandTotal = new BigDecimal(0);
			for (Item item : cartItems) {

				Category catgory = DiscountUtils.getCategoryById(categories, item.getItemCategory());
				BigDecimal discountOnItem = DiscountUtils.calculateDiscount(new BigDecimal(item.getUnitPrice()) , new BigDecimal(catgory.getDiscPer()));

				BigDecimal discountedItemPrice = new BigDecimal(item.getUnitPrice()).subtract(discountOnItem);

				BigDecimal itemTotal = discountedItemPrice.multiply(new BigDecimal(item.getQuantity()));

				System.out.printf("%22s %7s %7s %10s %17s",item.getItemName(),
						item.getQuantity(),
						item.getUnitPrice(),
						catgory.getDiscPer(),
						itemTotal
						);
				System.out.println();
				grandTotal = grandTotal.add(itemTotal);
			}
			System.out.println("*********************************************************************");

			int slabDiscount = DiscountUtils.getFlatDiscByAmount(slabs, grandTotal).getDiscPer();

			BigDecimal discountOnNetAmt =DiscountUtils.calculateDiscount(grandTotal, new BigDecimal(slabDiscount)); 
			BigDecimal total = grandTotal.subtract(discountOnNetAmt);
			System.out.println("Grand Total: " + grandTotal);
			System.out.println("Applicable Discount : " + slabDiscount + "%");
			System.out.println("Net Bill Amount : " + total);
			System.out.println("*********************************************************************");
		}
	}
}