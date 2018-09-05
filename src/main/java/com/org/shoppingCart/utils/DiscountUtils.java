package com.org.shoppingCart.utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.org.shoppingCart.model.Category;
import com.org.shoppingCart.model.FlatDiscountSlab;

public class DiscountUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal calculateDiscount(BigDecimal base, BigDecimal pct){
		return base.multiply(pct).divide(ONE_HUNDRED);
	}

	public static Category getCategoryById(List<Category> categories, String categoryId){
		Optional<Category> optionalCategory = categories.stream()
				.filter(c -> c.getCategoryId().equals(categoryId)).findFirst();

		Category catgory = optionalCategory.get();
		return catgory;
	}
	public static FlatDiscountSlab getFlatDiscByAmount(List<FlatDiscountSlab> slabs, BigDecimal grandTotal){
		for (FlatDiscountSlab flatDiscountSlab : slabs) {
			int total = grandTotal.intValue();
			if(total >= flatDiscountSlab.getMinRange() && total <= flatDiscountSlab.getMaxRange()){
				return flatDiscountSlab;
			}
		}
		return null;
	}
}
