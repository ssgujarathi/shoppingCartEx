package com.org.shoppingCart.model;
/**
 * 
This is model class for Item
 * 
 */

/**
 * @author Sneha Gujarathi
 *
 */
public class Item {
	private String itemId;
	private String itemName;
	private String itemCategory;
	private int unitPrice;
	private int quantity;
	
	public Item(String itemId, String itemName, String itemCategory,
			int unitPrice, int quantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Overriding equals method should help us comparing two objects in the cart
	 */
	@Override
	public boolean equals(Object itemObject) {
		if (itemObject == null) return false;
		if (itemObject instanceof Item) {
			return this.getItemName().equals(((Item)itemObject).getItemName());
		}else{
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getItemName().hashCode();
	}

	@Override
	public String toString() {
		return this.getItemId() + " " + this.getItemName() + " " + this.getItemCategory() + " " 
				+ this.getUnitPrice() + " " + this.getQuantity();
	}


}
