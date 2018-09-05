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

import com.org.shoppingCart.model.Category;

public class CategoriesInitializer implements Initializer{

	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void initialize (String fileName){
		JSONParser parser = new JSONParser();
		JSONArray categoryArray;
		
		try {
			categoryArray = (JSONArray) parser.parse(
					new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/"+fileName))));
			if(categoryArray != null && categoryArray.size() != 0){
				this.categories = new ArrayList<Category>();
				for (Object categoryObj : categoryArray) {
					JSONObject jsonObject = (JSONObject)categoryObj;
					this.categories.add(new Category(((String)jsonObject.get("categoryId")), 
							((String)jsonObject.get("categoryName")), ((Long) jsonObject.get("discPer")).intValue()));
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Went wrong while initializing categories.." );
			e.printStackTrace();		
		}
	}
}
