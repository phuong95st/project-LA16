/**
 * Copyright(C) 2017 Luvina software company
 * PositionDao.java, 02-04-2017 Tran
 */
package dao;

import java.util.List;

import entity.Position;

/**
 * 
 * @author Tran
 *
 */
public interface PositionDao {
	/**
	 * lấy tất cả danh sách các phòng bắt đầu bằng 1 kí hiệu
	 * @param shorcut
	 * @return
	 */
	public List<Position> getListPosition(String shorcut);
	
	public Position getPositionById(String key);
}
