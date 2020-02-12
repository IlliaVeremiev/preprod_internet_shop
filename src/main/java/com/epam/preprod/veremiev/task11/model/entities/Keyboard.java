package com.epam.preprod.veremiev.task11.model.entities;

import java.util.Objects;

/**
 * Keyboard entity class
 * 
 * @author Illia_Veremiev
 *
 */
public class Keyboard extends InputDevice {

	/**
	 * Count of keys
	 */
	private int keyCount;

	/**
	 * Is keyboard has legs
	 */
	private boolean withLegs;

	/**
	 * Is keyboard has extra buttons
	 */
	private boolean hasExtraButtons;

	/**
	 * @return the keyCount
	 */
	public int getKeyCount() {
		return keyCount;
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		if(! super.equals(o)) return false;
		Keyboard keyboard = (Keyboard)o;
		return keyCount == keyboard.keyCount &&
				withLegs == keyboard.withLegs &&
				hasExtraButtons == keyboard.hasExtraButtons;
	}

	@Override
	public int hashCode(){
		return Objects.hash(super.hashCode(), keyCount, withLegs, hasExtraButtons);
	}

	/**
	 * @param keyCount the keyCount to set
	 */
	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}

	/**
	 * @return the withLegs
	 */
	public boolean isWithLegs() {
		return withLegs;
	}

	/**
	 * @param withLegs the withLegs to set
	 */
	public void setWithLegs(boolean withLegs) {
		this.withLegs = withLegs;
	}

	/**
	 * @return the hasExtraButtons
	 */
	public boolean isHasExtraButtons() {
		return hasExtraButtons;
	}

	/**
	 * @param hasExtraButtons the hasExtraButtons to set
	 */
	public void setHasExtraButtons(boolean hasExtraButtons) {
		this.hasExtraButtons = hasExtraButtons;
	}

}