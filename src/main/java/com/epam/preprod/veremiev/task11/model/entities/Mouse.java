package com.epam.preprod.veremiev.task11.model.entities;

import java.util.Objects;

import com.epam.preprod.veremiev.task11.model.entities.utils.MouseSize;

/**
 * Mouse entity class
 * 
 * @author Illia_Veremiev
 *
 */
public class Mouse extends InputDevice {

	/**
	 * Count of buttons
	 */
	private int buttonsCount;

	/**
	 * Is keyboard has legs
	 */
	private MouseSize size;

	/**
	 * Mouse scrolls count
	 */
	private int scrollsCount;

	/**
	 * @return the buttonsCount
	 */
	public int getButtonsCount() {
		return buttonsCount;
	}

	/**
	 * @param buttonsCount the buttonsCount to set
	 */
	public void setButtonsCount(int buttonsCount) {
		this.buttonsCount = buttonsCount;
	}

	/**
	 * @return the scrollsCount
	 */
	public int getScrollsCount() {
		return scrollsCount;
	}

	/**
	 * @param scrollsCount the scrollsCount to set
	 */
	public void setScrollsCount(int scrollsCount) {
		this.scrollsCount = scrollsCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Mouse mouse = (Mouse) o;
		return buttonsCount == mouse.buttonsCount && scrollsCount == mouse.scrollsCount && size == mouse.size;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), buttonsCount, size, scrollsCount);
	}

	/**
	 * @return the size
	 */
	public MouseSize getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(MouseSize size) {
		this.size = size;
	}
}