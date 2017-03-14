/**
 * Copyright(C) 2017 Luvina software company
 * OnlDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.Onl;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface OnlDao {
	/**
	 * Lấy danh sách lịch gặp sinh viên
	 * 
	 * @param timeStart
	 *            Thời gian bắt đầu
	 * @param timeEnd
	 *            Thời gian kết thúc
	 * @param id
	 *            id nhân viên
	 * @return List các đối tượng ScheStu
	 */
	public List<Onl> getListOnl(Timestamp timeStart, Timestamp timeEnd, int id);
}
