/**
 * Copyright(C) 2017 Luvina software company
 * LateDao.java, Mar 10, 2017 nguyenhuuphuong
 */
package dao;

import entity.Late;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface LateDao {
	/**
	 * Lưu ngày đi muộn vào db
	 * 
	 * @param late
	 *            đối tượng late
	 * @return 0: không thành công , != 0 thành công
	 */
	public long insertLate(Late late);

	/**
	 * Thay đổi lý do đi muộn
	 * 
	 * @param reason
	 *            lý do
	 * @param id
	 *            ngày đi muộn trong database
	 * @return true: cập nhật thành công. false: cập nhật không thành công
	 */
	public boolean updateLate(String reason, long id);

}
