/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupLogic.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.MstGroup;

/**
 * Định nghĩa hàm getAllGroup() để lấy tất cả group trong db thông qua dao
 *
 * @author nhphuong
 *
 */
public interface MstGroupLogic {
	/**
	 * get all group
	 *
	 * @return List MstGroup
	 */
	public List<MstGroup> getAllGroups();
}
