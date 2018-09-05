package com.org.shoppingCart.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;
import com.org.shoppingCart.model.Item;
import com.org.shoppingCart.utils.ShoppingCartUtils;

public class CartService {

	private List<Category> categories;
	private List<FlatDiscountSlab> slabs;
	private List<Item> items;
	
	public List<Category> fetchCategories(String fileName){
		Initializer initializer = new CategoriesInitializer();
		initializer.initialize(fileName);
		this.categories = ((CategoriesInitializer) initializer).getCategories();
		return this.categories;
	}
	
	public List<FlatDiscountSlab> fetchFlatDiscountSlabs(String fileName){
		Initializer initializer = new FlatDiscountSlabsInitializer();
		initializer.initialize(fileName);
		this.slabs = ((FlatDiscountSlabsInitializer) initializer).getSlabs();
		return this.slabs;
	}

	public List<Item> fetchCartItems(String fileName){
		Initializer initializer = new CartItemsInitializer();
		initializer.initialize(fileName);
		this.items = ((CartItemsInitializer) initializer).getItems();
		return this.items;
	}

	public void displayItemizedBill(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		if(items != null && items.size() != 0 ){

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
			for (Item item : items) {

				Category catgory = ShoppingCartUtils.getCategoryById(categories, item.getItemCategory());
				BigDecimal itemTotal = ShoppingCartUtils.getTotalPricePerItem(item.getUnitPrice(),catgory.getDiscPer(),item.getQuantity());
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

			int slabDiscount = ShoppingCartUtils.getFlatDiscByAmount(slabs, grandTotal).getDiscPer();
			BigDecimal total = ShoppingCartUtils.calculateNetBillAmount(grandTotal, slabDiscount);
			System.out.println("Grand Total: " + grandTotal);
			System.out.println("Applicable Discount : " + slabDiscount + "%");
			System.out.println("Net Bill Amount : " + total);
			System.out.println("*********************************************************************");
		}
	}
}