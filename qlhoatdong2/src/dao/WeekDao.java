/**
 * Copyright(C) 2017 Luvina software company
 * WeekDao.java, 01-04-2017 Tran
 */
package dao;

import java.util.List;

import entity.Week;

/**
 * 
 * @author Tran
 *
 */
public interface WeekDao {
	public List<Week> getListWeek(String hocKy);
}
