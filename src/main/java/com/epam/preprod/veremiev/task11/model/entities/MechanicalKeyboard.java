package com.epam.preprod.veremiev.task11.model.entities;

import java.util.Objects;

import com.epam.preprod.veremiev.task11.model.entities.utils.KeySwitches;

/**
 * MechanicalKeyboard entity class
 * 
 * @author Illia_Veremiev
 *
 */
public class MechanicalKeyboard extends Keyboard {

	/**
	 * Keys pressing dept
	 */
	private float pressingDepth;

	/**
	 * Keys switches type
	 */
	private KeySwitches keySwitches;

	/**
	 * Pressing strength on button in grams
	 */
	private float pressingStrength;

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		if(! super.equals(o)) return false;
		MechanicalKeyboard that = (MechanicalKeyboard)o;
		return Float.compare(that.pressingDepth, pressingDepth) == 0 &&
				Float.compare(that.pressingStrength, pressingStrength) == 0 &&
				keySwitches == that.keySwitches;
	}

	@Override
	public int hashCode(){
		return Objects.hash(super.hashCode(), pressingDepth, keySwitches, pressingStrength);
	}

	/**
	 * @return the pressingDepth
	 */
	public float getPressingDepth() {
		return pressingDepth;
	}

	/**
	 * @param pressingDepth the pressingDepth to set
	 */
	public void setPressingDepth(float pressingDepth) {
		this.pressingDepth = pressingDepth;
	}

	/**
	 * @return the keySwitches
	 */
	public KeySwitches getKeySwitches() {
		return keySwitches;
	}

	/**
	 * @param keySwitches the keySwitches to set
	 */
	public void setKeySwitches(KeySwitches keySwitches) {
		this.keySwitches = keySwitches;
	}

	/**
	 * @return the pressingStrength
	 */
	public float getPressingStrength() {
		return pressingStrength;
	}

	/**
	 * @param pressingStrength the pressingStrength to set
	 */
	public void setPressingStrength(float pressingStrength) {
		this.pressingStrength = pressingStrength;
	}

}