package com.org.shoppingCart.utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;

public class ShoppingCartUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal calculateDiscount(BigDecimal base, BigDecimal pct){
		return base.multiply(pct).divide(ONE_HUNDRED);
	}

	public static Category getCategoryById(List<Category> categories, String categoryId){
		Optional<Category> optionalCategory = categories.stream()
				.filter(c -> c.getCategoryId().equals(categoryId)).findFirst();
		if(optionalCategory.isPresent()){
			return optionalCategory.get();
		}
		return null;
	}
	public static FlatDiscountSlab getFlatDiscByAmount(List<FlatDiscountSlab> slabs, BigDecimal grandTotal){
		for (FlatDiscountSlab flatDiscountSlab : slabs) {
			int total = grandTotal.intValue();
			if(total >= flatDiscountSlab.getMinRange() && total <= flatDiscountSlab.getMaxRange()){
				return flatDiscountSlab;
			}else if (total > flatDiscountSlab.getMaxRange()){
				return flatDiscountSlab;
			}
		}
		return null;
	}

	public static BigDecimal getTotalPricePerItem(int unitPrice, int discount, int quantity) {
		
		BigDecimal discountOnItem = (discount != 0) ? 
				ShoppingCartUtils.calculateDiscount(new BigDecimal(unitPrice) , new BigDecimal(discount)) : new BigDecimal(0);
		BigDecimal discountedItemPrice = new BigDecimal(unitPrice).subtract(discountOnItem);
		return discountedItemPrice.multiply(new BigDecimal(quantity));
	}

	public static BigDecimal calculateNetBillAmount(BigDecimal grandTotal, int slabDiscount) {
		BigDecimal discountOnNetAmt =ShoppingCartUtils.calculateDiscount(grandTotal, new BigDecimal(slabDiscount)); 
		return grandTotal.subtract(discountOnNetAmt);
	}
}
