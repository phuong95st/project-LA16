/**
 * Copyright(C) 2016 Luvina Software Company
 * UserLogicImpl.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.logics.impl;

import java.util.List;

import net.luvina.manageuser.dao.impl.UserDaoImpl;
import net.luvina.manageuser.entities.TblGroup;
import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.logics.UserLogic;

/**
 * @author PhuongNH
 *
 */
public class UserLogicImpl implements UserLogic{
	/* (non-Javadoc)
     * @see manageuser.logics.UserLogic#existLoginId(java.lang.String, java.lang.String)
     */
    public Boolean existLoginId(String loginId, String password) {
    	UserDaoImpl userDao = new UserDaoImpl();
        TblUser user = userDao.getUserByLoginId(loginId, password);
        Boolean result = false;
        if(user != null) {
        	result = true;
        }
        return result;
    }


    /* (non-Javadoc)
     * @see manageuser.logics.UserLogic#getAllGroups()
     */
    public List<TblGroup> getAllGroups() {
        UserDaoImpl userDao = new UserDaoImpl();
        List<TblGroup> lsGroups = userDao.getAllGroups();
        return lsGroups;
    }


    /* (non-Javadoc)
     * @see manageuser.logics.UserLogic#getListUsers(manageuser.entities.TblUser, int, int)
     */
    public List<TblUser> getListUsers(TblUser tblUser, int offset, int limit) {
        UserDaoImpl userDao = new UserDaoImpl();
        List<TblUser> lsUsers = userDao.getListUsers(tblUser, offset, limit);
        return lsUsers;
    }


    /* (non-Javadoc)
     * @see manageuser.logics.UserLogic#getTotalUsers(manageuser.entities.TblUser)
     */
    public int getTotalUsers(TblUser tblUser) {
        UserDaoImpl userDao = new UserDaoImpl();
        int totalUsers = userDao.getTotalUsers(tblUser);
        return totalUsers;
    }
}
