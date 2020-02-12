package com.epam.preprod.veremiev.task11.model.entities;

import java.sql.Timestamp;
import java.util.List;

import com.epam.preprod.veremiev.task11.model.entities.utils.Status;

/**
 * Order entity class
 * 
 * @author Illia_Veremiev
 *
 */
public class Order extends AbstractEntity {

	private long id;
	private Status status;
	private String detalis;
	private Timestamp date;
	private long userId;
	private List<OrderedInputDevice> products;
	private String payType;
	private String payRequisites;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the detalis
	 */
	public String getDetalis() {
		return detalis;
	}

	/**
	 * @param detalis the detalis to set
	 */
	public void setDetalis(String detalis) {
		this.detalis = detalis;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the products
	 */
	public List<OrderedInputDevice> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<OrderedInputDevice> products) {
		this.products = products;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the payRequisites
	 */
	public String getPayRequisites() {
		return payRequisites;
	}

	/**
	 * @param payRequisites the payRequisites to set
	 */
	public void setPayRequisites(String payRequisites) {
		this.payRequisites = payRequisites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((detalis == null) ? 0 : detalis.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((payRequisites == null) ? 0 : payRequisites.hashCode());
		result = prime * result + ((payType == null) ? 0 : payType.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (detalis == null) {
			if (other.detalis != null) {
				return false;
			}
		} else if (!detalis.equals(other.detalis)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (payRequisites == null) {
			if (other.payRequisites != null) {
				return false;
			}
		} else if (!payRequisites.equals(other.payRequisites)) {
			return false;
		}
		if (payType == null) {
			if (other.payType != null) {
				return false;
			}
		} else if (!payType.equals(other.payType)) {
			return false;
		}
		if (products == null) {
			if (other.products != null) {
				return false;
			}
		} else if (!products.equals(other.products)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}
}