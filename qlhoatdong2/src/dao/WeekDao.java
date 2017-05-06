/**
 * Copyright(C) 2017 Luvina software company
 * WeekDao.java, 01-04-2017 Tran
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.Week;

/**
 * 
 * @author Tran
 *
 */
public interface WeekDao {
	public List<Week> getListWeek(String hocKy);
	
	public Week getCurrentWeek(Timestamp now);
	
	public Week getWeekById(int weekId);
	
	public boolean addWeek(Week week);
}
