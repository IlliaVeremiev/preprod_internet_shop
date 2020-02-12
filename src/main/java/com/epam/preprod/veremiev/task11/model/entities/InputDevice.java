package com.epam.preprod.veremiev.task11.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

import com.epam.preprod.veremiev.task11.model.entities.utils.Category;
import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;

/**
 * Input device entity class
 * 
 * @author Illia_Veremiev
 *
 */
public abstract class InputDevice extends Product {

	/**
	 * Device price
	 */
	private BigDecimal price;

	/**
	 * Device title
	 */
	private String title;

	/**
	 * Devices count
	 */
	private long count;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		InputDevice that = (InputDevice) o;
		return count == that.count && Objects.equals(price, that.price) && Objects.equals(title, that.title)
				&& Objects.equals(category, that.category) && Objects.equals(photo, that.photo)
				&& Objects.equals(manufacturer, that.manufacturer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, title, count, category, photo, manufacturer);
	}

	/**
	 * Device category
	 */
	private Category category;

	/**
	 * Device photo
	 */
	private String photo;

	/**
	 * Device manufacturer
	 */
	private Manufacturer manufacturer;

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}