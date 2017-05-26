/**
 * Copyright(C) 2017 Luvina software company
 * BaseDao.java, Feb 7, 2017 nguyenhuuphuong
 */
package dao;

/**
 * 
 * @author nguyenhuuphuong
 *
 */
public interface BaseDao {
	/**
	 * 
	 * @return
	 */
	public boolean connectToDB();
	
	/**
	 * 
	 */
	public void close();
}
