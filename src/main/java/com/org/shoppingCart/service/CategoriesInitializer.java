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

import com.org.shoppingCart.exception.BusinessValidationException;
import com.org.shoppingCart.exception.ExceptionMessageResource;
import com.org.shoppingCart.model.Category;

public class CategoriesInitializer implements Initializer{

	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void initialize (String fileName) throws BusinessValidationException{
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray categoryArray = (JSONArray) parser.parse(
					new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/"+fileName))));
			if(categoryArray != null && categoryArray.size() != 0){
		
				this.categories = new ArrayList<Category>();
				for (Object categoryObj : categoryArray) {
					JSONObject jsonObject = (JSONObject)categoryObj;
					
					if(jsonObject.get("categoryId") == null || 
							jsonObject.get("categoryName") == null || 
							jsonObject.get("discPer") == null){
						throw new BusinessValidationException(ExceptionMessageResource.CATEGORY_FIELD_PARSE_EXCEPTION);
					} 
						
					String catgoryId = ((String)jsonObject.get("categoryId"));
					String categoryName = ((String)jsonObject.get("categoryName"));
					int discPer = ((Long) jsonObject.get("discPer")).intValue();
					
					this.categories.add(new Category(catgoryId, categoryName, discPer));
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Went wrong while initializing categories.." );
			e.printStackTrace();		
			throw new BusinessValidationException(ExceptionMessageResource.CATEGORY_FIELD_PARSE_EXCEPTION);
		}
	}
}
