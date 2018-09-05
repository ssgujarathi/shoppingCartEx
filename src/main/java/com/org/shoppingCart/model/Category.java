package com.org.shoppingCart.model;
/**

 * This class is the model class for Category
 */


/**
 * @author Sneha Gujarathi
 *
 */
public class Category {
	private String categoryId;
	private String categoryName;
	private int discPer;
	
	public Category(String categoryId, String categoryName, int discPer) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.discPer = discPer;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getDiscPer() {
		return discPer;
	}
	public void setDiscPer(int discPer) {
		this.discPer = discPer;
	}
}
