/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupLogicImpl.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.logics.impl;

import java.util.List;

import net.luvina.manageuser.dao.impl.MstGroupDaoImpl;
import net.luvina.manageuser.entities.MstGroup;
import net.luvina.manageuser.logics.MstGroupLogic;

/**
 * Kế thừa interface MstGroup
 *
 * @author nhphuong
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.MstGroupDao#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllGroups() {
		MstGroupDaoImpl groupDao = new MstGroupDaoImpl();
		List<MstGroup> lsGroups = groupDao.getAllGroups();
		return lsGroups;
	}
}
