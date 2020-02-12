package com.epam.preprod.veremiev.task11.modules.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.epam.preprod.veremiev.task11.model.entities.InputDevice;

/**
 * Cart representation. Contains methods for work with cart
 * 
 * @author Illia_Veremiev
 *
 */
public class Cart {

	private Map<InputDevice, Long> map;

	public Cart() {
		map = new HashMap<InputDevice, Long>();
	}

	/**
	 * Adds {@link InputDevice} to cart
	 * 
	 * @param device - device to add
	 * @param count  - devices count
	 * @return new device count
	 */
	public long add(InputDevice device, long count) {
		if (!map.containsKey(device)) {
			map.put(device, Long.valueOf(0));
		}
		long newCount = map.get(device) + count;
		map.put(device, newCount);
		return newCount;
	}

	/**
	 * Sets product count
	 * 
	 * @param device - device to set
	 * @param count  - items count
	 */
	public void setProductCount(InputDevice device, long count) {
		map.put(device, count);
	}

	/**
	 * Remove device from cart
	 * 
	 * @param device - device to remove
	 */
	public void remove(InputDevice device) {
		map.remove(device);
	}

	/**
	 * Remove all devices from cart
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * Returns true if cart is empty
	 * 
	 * @return true if cart is empty
	 */
	public boolean isEmpty() {
		return getAllCount() == 0;
	}

	/**
	 * Returns {@link Map} of all devices
	 * 
	 * @return {@link Map} of all devices
	 */
	public Map<InputDevice, Long> getAll() {
		return map;
	}

	/**
	 * Returns count of device in cart
	 * 
	 * @param device - to find count
	 * @return count of device in cart
	 */
	public long getCount(InputDevice device) {
		return map.get(device);
	}

	/**
	 * Returns count of all devices in cart
	 * 
	 * @return count of all devices in cart
	 */
	public long getAllCount() {
		return map.entrySet().stream().mapToLong(i -> i.getValue()).sum();
	}

	/**
	 * Returns price of all items in cart
	 * 
	 * @return price of all items in cart
	 */
	public BigDecimal getFullPrice() {
		return map.entrySet().stream().map(i -> i.getKey().getPrice().multiply(new BigDecimal(i.getValue())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
