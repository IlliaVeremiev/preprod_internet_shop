package com.epam.preprod.veremiev.task11.model.entities;

import java.math.BigDecimal;

/**
 * Relation between {@link InputDevice} and {@link Order}
 * 
 * @author Illia_Veremiev
 *
 */
public class OrderedInputDevice extends AbstractEntity {

	private long orderId;

	private long deviceId;

	private long orderedCount;

	private BigDecimal oldPrice;

	public OrderedInputDevice() {
	}

	public OrderedInputDevice(long deviceId, long orderId, BigDecimal oldPrice, long orderedCount) {
		this.deviceId = deviceId;
		this.orderId = orderId;
		this.oldPrice = oldPrice;
		this.orderedCount = orderedCount;
	}

	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the deviceId
	 */
	public long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the orderedCount
	 */
	public long getOrderedCount() {
		return orderedCount;
	}

	/**
	 * @param orderedCount the orderedCount to set
	 */
	public void setOrderedCount(long orderedCount) {
		this.orderedCount = orderedCount;
	}

	/**
	 * @return the oldPrice
	 */
	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	/**
	 * @param oldPrice the oldPrice to set
	 */
	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (deviceId ^ (deviceId >>> 32));
		result = prime * result + ((oldPrice == null) ? 0 : oldPrice.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (orderedCount ^ (orderedCount >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OrderedInputDevice other = (OrderedInputDevice) obj;
		if (deviceId != other.deviceId) {
			return false;
		}
		if (oldPrice == null) {
			if (other.oldPrice != null) {
				return false;
			}
		} else if (!oldPrice.equals(other.oldPrice)) {
			return false;
		}
		if (orderId != other.orderId) {
			return false;
		}
		if (orderedCount != other.orderedCount) {
			return false;
		}
		return true;
	}
}