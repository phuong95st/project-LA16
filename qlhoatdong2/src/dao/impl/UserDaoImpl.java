/**
 * Copyright(C) 2017 Luvina software company
 * UserDaoImpl.java, Mar 14, 2017 nguyenhuuphuong
 */
package dao.impl;

import dao.UserDao;
import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/* (non-Javadoc)
	 * @see dao.UserDao#checkLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkLogin(String email, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.UserDao#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
