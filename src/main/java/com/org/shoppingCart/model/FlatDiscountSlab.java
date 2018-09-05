package com.org.shoppingCart.model;
/**

 * This class is used to initialize the flatdiscount on different slabs while
 * calculating the net billed amount
 */


/**
 * @author Sneha Gujarathi
 *
 */
public class FlatDiscountSlab {
	private int minRange;
	private int maxRange;
	private int discPer;
	
	public FlatDiscountSlab(int minRange, int maxRange, int discPer) {
		super();
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.discPer = discPer;
	}
	public int getMinRange() {
		return minRange;
	}
	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	public int getDiscPer() {
		return discPer;
	}
	public void setDiscPer(int discPer) {
		this.discPer = discPer;
	}
}
