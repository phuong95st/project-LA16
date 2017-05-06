/**
 * Copyright(C) 2017 Luvina software company
 * ScheStuDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import entity.ScheStu;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface ScheStuDao {
	/**
	 * Lấy danh sách lịch gặp sinh viên trong 1 khoảng thời gian
	 * 
	 * @param timeStart
	 *            thời gian bắt đầu
	 * @param timeEnd
	 *            thời gian kết thúc
	 * @param userId
	 *            id nhân viên
	 * @return List các đối tượng ScheStu
	 */
	public List<ScheStu> getListScheStu(Timestamp timeStart,int userId);
	
	public List<ScheStu> getListScheStu(Map<String, Object> listValue, int userId);
	
	public boolean addScheStu(ScheStu scheStu);
	
	public ScheStu getScheStuById(int id);
	
	public boolean updateScheStu(ScheStu scheStu);
	
	public boolean delScheStu(ScheStu scheStu);
	
	public boolean checkByUserId(int userId);
	
	public List<ScheStu> getListScheStu(Map<String, Object> listValue);
}
