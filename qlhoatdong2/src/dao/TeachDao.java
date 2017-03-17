/**
 * Copyright(C) 2017 Luvina software company
 * TeachDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.Teach;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface TeachDao {
	/**
	 * 
	 * @param now
	 * @param hocKy
	 * @param userId
	 * @return
	 */
	public List<Teach> getListTeach(Timestamp now, String hocKy, int userId);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Teach getTeachById(long id);
	
	/**
	 * 
	 * @param teach
	 * @return
	 */
	public boolean update(Teach teach);
}
