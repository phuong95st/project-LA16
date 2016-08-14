/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroup.java, Aug 9, 2016 PhuongNH
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;

/**
 * Java bean chứa các thông tin về group
 *
 * @author PhuongNH
 *
 */
public class MstGroup implements Serializable {
	private int id;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
