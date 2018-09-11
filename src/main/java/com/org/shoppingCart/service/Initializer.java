/**

 * This class is used to initialize the items, categories from the files and cart items 
 */
package com.org.shoppingCart.service;

import com.org.shoppingCart.exception.BusinessValidationException;

/**
 * @author Sneha Gujarathi
 *
 */
public interface Initializer {
	public void initialize (String fileName) throws BusinessValidationException;
}
