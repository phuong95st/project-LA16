/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUserLogicImpl.java, Aug 9, 2016 PhuongNH
 */
package net.luvina.manageuser.logics.impl;

import java.util.List;

import net.luvina.manageuser.dao.impl.TblUserDaoImpl;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.TblUserLogic;

/**
 * @author PhuongNH
 *
 */
public class TblUserLogicImpl implements TblUserLogic {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * manageuser.logics.UserLogic#getListUsers(manageuser.entities.TblUser,
	 * int, int)
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate) {
		TblUserDaoImpl userDao = new TblUserDaoImpl();
        List<UserInfor> lsUsers = userDao.getListUsers(offset,limit,groupId,fullName,sortType, sortByFullName,sortByCodeLevel,sortByEndDate);
        return lsUsers;
	}

}
