/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupLogic.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.MstGroup;

/**
 * @author nhphuong
 *
 */
public interface MstGroupLogic {
	/**
	 *
	 * @return List MstGroup
	 */
	public List<MstGroup> getAllGroups();
}
