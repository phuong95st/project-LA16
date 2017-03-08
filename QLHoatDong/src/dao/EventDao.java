/**
 * Copyright(C) 2017 Luvina software company
 * EventDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.Event;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface EventDao {
	/**
	 * Lấy danh sách Các sự kiện trong khoảng thời gian
	 * 
	 * @param timeStart
	 *            Thời gian bắt đầu
	 * @param timeEnd
	 *            Thời gian kết thúc
	 * @return List các đối tượng Event
	 */
	public List<Event> getListEvent(Timestamp timeStart, Timestamp timeEnd);
}
