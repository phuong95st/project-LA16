/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupDao.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.dao;

import java.util.List;

import net.luvina.manageuser.entities.MstGroup;

/**
 * Interface định nghĩa phương thức getAllGroups để lấy tất cả group trong db
 * @author nhphuong
 *
 */
public interface MstGroupDao extends BaseDao {
	/**
	 * Lấy tất cả data trong bảng mst_group
	 * @return List Groups
	 */
	public List<MstGroup> getAllGroups();
}
