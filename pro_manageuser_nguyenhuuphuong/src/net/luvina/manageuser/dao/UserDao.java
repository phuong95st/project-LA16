/**
 * Copyright(C) 2016 Luvina Software Company
 * UserDao.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.dao;

import net.luvina.manageuser.entities.TblUser;

/**
 * @author PhuongNH
 *
 */
public interface UserDao extends BaseDao{
	/**
     * getUserByLoginId
     *
     * @param loginId
     *            String loginId
     * @param password
     *            String password
     * @return TblUser
     */
    public TblUser getUserByLoginId(String loginId, String password) ;

    /**
     * getTotalListUsers
     *
     * @param user
     *            UserBean
     * @return int total users
     */
    public int getTotalUsers(TblUser user) ;
}
