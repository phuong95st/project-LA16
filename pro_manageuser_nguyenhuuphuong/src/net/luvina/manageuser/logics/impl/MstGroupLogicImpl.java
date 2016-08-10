/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupLogicImpl.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.logics.impl;

import java.util.List;

import net.luvina.manageuser.dao.MstGroupDao;
import net.luvina.manageuser.dao.impl.MstGroupDaoImpl;
import net.luvina.manageuser.entities.MstGroup;

/**
 * @author nhphuong
 *
 */
public class MstGroupLogicImpl implements MstGroupDao {

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.MstGroupDao#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllGroups() {
		MstGroupDaoImpl groupDao = new MstGroupDaoImpl();
        List<MstGroup> lsGroups = groupDao.getAllGroups();
        return lsGroups;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.BaseDao#closeConnect()
	 */
	@Override
	public void closeConnect() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.BaseDao#connectToDB()
	 */
	@Override
	public boolean connectToDB() {
		// TODO Auto-generated method stub
		return false;
	}

}
