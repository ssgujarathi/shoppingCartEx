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

import com.org.shoppingCart.model.FlatDiscountSlab;

public class FlatDiscountSlabsInitializer implements Initializer{
	private List<FlatDiscountSlab> slabs;

	public List<FlatDiscountSlab> getSlabs() {
		return slabs;
	}

	public void setSlabs(List<FlatDiscountSlab> slabs) {
		this.slabs = slabs;
	}

	public void initialize (String fileName){
		JSONParser parser = new JSONParser();
		JSONArray slabArray;
		try {
			slabArray = (JSONArray) parser.parse(
					new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName))));
			if(slabArray != null && slabArray.size() != 0){
				this.slabs = new ArrayList<FlatDiscountSlab>();
				for (Object slabObj : slabArray) {
					JSONObject jsonObject = (JSONObject)slabObj;
					this.slabs.add(new FlatDiscountSlab(
							((Long)jsonObject.get("minRange")).intValue(),
							((Long) jsonObject.get("maxRange")).intValue(),
							((Long) jsonObject.get("discPer")).intValue()));
				}
			}
		} catch (IOException | ParseException e) {
			System.err.println("Went wrong while initializing slabs.." );
			e.printStackTrace();		
		}
	}


}
