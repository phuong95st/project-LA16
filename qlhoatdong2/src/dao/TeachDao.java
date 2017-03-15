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
	 * Lấy danh sách lịch giảng trong khoảng thời gian
	 * 
	 * @param timeStart
	 *            thời gian bắt đầu
	 * @param timeEnd
	 *            thời gian kết thúc
	 * @param userId
	 *            id nhân viên
	 * @return List các đối tượng Teach
	 */
	public List<Teach> getListTeach(Timestamp timeStart, Timestamp timeEnd,
			int userId);
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
