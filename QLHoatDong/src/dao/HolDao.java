/**
 * Copyright(C) 2017 Luvina software company
 * HolDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.util.List;

import entity.Hol;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface HolDao {
	/**
	 * Lấy danh sách các ngày nghỉ của nhân viên
	 * 
	 * @param userId
	 *            userId
	 * @return List các đối tượng Hol
	 */
	public List<Hol> getListHol(int userId);

	/**
	 * Update thông tin lịch nghỉ
	 * 
	 * @param hol
	 *            đối tượng lịch nghỉ
	 * @param status
	 *            nếu update trường status thì hol = null. Nếu update cả đối
	 *            tượng hol thì hol != null
	 * @return true: update thành công. false: update thất bại
	 */
	public boolean update(Hol hol, boolean status);
}
