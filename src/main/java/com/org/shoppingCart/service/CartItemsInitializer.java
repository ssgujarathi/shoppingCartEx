package com.org.shoppingCart.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.org.shoppingCart.model.Item;

public class CartItemsInitializer implements Initializer{
	private List<Item> items; // we are using composition to include the item details in the cart

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void initialize (String fileName){
		JSONParser parser = new JSONParser();
		JSONArray cartItemArray;
		try {
			cartItemArray = (JSONArray) parser.parse(
					new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/"+fileName))));
			if(cartItemArray != null && cartItemArray.size() != 0){
				this.items = new ArrayList<Item>();
				for (Object itemObj : cartItemArray) {
					JSONObject jsonObject = (JSONObject) itemObj;
					this.items.add(new Item(
							((String)jsonObject.get("itemId")), 
							((String)jsonObject.get("itemName")), 
							((String)jsonObject.get("itemCategory")), 
							((Long)jsonObject.get("unitPrice")).intValue(), 
							((Long)jsonObject.get("quantity")).intValue()));
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Went wrong while initializing cartItems.." );
			e.printStackTrace();		
		}
	}
}
