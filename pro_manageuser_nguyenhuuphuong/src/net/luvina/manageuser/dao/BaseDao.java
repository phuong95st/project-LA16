/**
 * Copyright(C) 2016 Luvina Software Company
 * BaseDao.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.dao;

/**
 * @author PhuongNH
 *
 */
public interface BaseDao {
	/**
     * connectToDB
     *
     * @return true: connectToDB success
     *  false: connectToDB unsuccess
     */
    public boolean connectToDB();

    /**
     * closeConnect
     */
    public void closeConnect();
}
