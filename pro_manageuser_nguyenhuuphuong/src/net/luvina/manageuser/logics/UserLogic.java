/**
 * Copyright(C) 2016 Luvina Software Company
 * UserLogic.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.logics;

import net.luvina.manageuser.entities.TblUser;

/**
 * @author PhuongNH
 *
 */
public interface UserLogic {
	/**
     * Check existLoginId
     *
     * @param loginId
     *            String loginId
     * @param password
     *            String password
     * @return true: existed
     *  false: not exist
     */
    public Boolean existLoginId(String loginId, String password);

    /**
     * getTotalListUsers
     *
     * @param tblUser
     *            TblUser
     * @return int total users
     */
    public int getTotalUsers(TblUser tblUser);
}
