/**
 * Copyright(C) 2017 Luvina software company
 * TeachDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
	
	public double getHourTeach(String hocKy, String weekId, int userId);
	
	public List<Teach> getListTeach(int weekId, int userId);
	
	public double getAllHourTeach(String hocKy, String weekId, int userId);
	
	public double getHourLateTeach(String hocKy, String weekId, int userId);
	
	public boolean checkTeachByUserId(int userId);
	
	public long addTeach(Teach teach);
	
	public List<Teach> getListTeach(int userId, long teachId);
	
	public boolean editTeach(Teach teach);
	
	public boolean deleteTeach(long teachId);
	
	public List<Teach> getListTeach(Map<String, Object> listValue);
}
