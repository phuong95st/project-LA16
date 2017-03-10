/**
 * Copyright(C) 2017 Luvina software company
 * HolDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.Hol;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface HolDao {
	/**
	 * Lấy danh sách các ngày nghỉ của nhân viên sau thời điểm xác định
	 * 
	 * @param time
	 *            Thời điểm
	 * @param userId
	 *            userId
	 * @return List các đối tượng Hol
	 */
	public List<Hol> getListHol(Timestamp time, int userId);

	/**
	 * Update thông tin lịch nghỉ
	 * 
	 * @param hol
	 *            hol
	 * @param changeStatus
	 *            có thay đổi trạng thái hay không
	 * @return true: update thành công. flase update không thành công
	 */
	public boolean update(Hol hol, boolean changeStatus);

	public Hol getHolByTime(Timestamp time, int userId);
	
	public boolean insert(Hol hol);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(long id);

}
