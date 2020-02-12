package com.epam.preprod.veremiev.task11.services;

import java.math.BigDecimal;
import java.util.Map;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.modules.cart.Cart;

/**
 * Cart service
 * 
 * @author Illia_Veremiev
 *
 */
public class CartService {

	/**
	 * Adds product to cart
	 * 
	 * @param device - product
	 * @param cart   - cart
	 * @return current device count
	 */
	public long addProductToCart(InputDevice device, Cart cart) {
		return cart.add(device, 1);
	}

	/**
	 * Removes product from cart
	 * 
	 * @param device - product to remove
	 * @param cart   - cart where remove
	 */
	public void removeProductFromCart(InputDevice device, Cart cart) {
		cart.remove(device);
	}

	/**
	 * Remove all products from cart
	 * 
	 * @param cart - cart to clear
	 */
	public void clearCart(Cart cart) {
		cart.clear();
	}

	/**
	 * Returns total price of all products in cart
	 * 
	 * @param cart - cart to calculate
	 * @return total price of all products in cart
	 */
	public BigDecimal getTotalPrice(Cart cart) {
		return cart.getAll().entrySet().stream()
				.map((i) -> i.getKey().getPrice().multiply(new BigDecimal(i.getValue())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Map<InputDevice, Long> getCartProducts(Cart cart) {
		return cart.getAll();
	}

	/**
	 * Sets product count in cart
	 * 
	 * @param cart   - cart where set
	 * @param device - products to set
	 * @param scount - product count
	 * @throws ServiceException if errors occurs
	 */
	public void setProductsCount(Cart cart, InputDevice device, String scount) throws ServiceException {
		try {
			long count = Long.parseLong(scount);
			if (count < 1) {
				throw new ServiceException(ExceptionMessages.COUNT_CANT_BE_NEGATIVE);
			}
			cart.setProductCount(device, count);
		} catch (NumberFormatException e) {
			throw new ServiceException(ExceptionMessages.UNABLE_TO_CHANGE_ITEMS_COUNT);
		}
	}

	/**
	 * Returns total device price in cart
	 * 
	 * @param cart   - cart to calculate price
	 * @param device - to calculate price
	 * @return total device price in cart
	 */
	public BigDecimal getPrice(Cart cart, InputDevice device) {
		return device.getPrice().multiply(new BigDecimal(cart.getCount(device)));
	}
}
