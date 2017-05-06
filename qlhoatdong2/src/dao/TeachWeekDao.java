/**
 * Copyright(C) 2017 Luvina software company
 * TeachWeekDao.java, Mar 17, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;

import entity.Teach;
import entity.TeachWeek;

/**
 * 
 * @author nguyenhuuphuong
 *
 */
public interface TeachWeekDao {
	public TeachWeek getTeachDaoByTeachId(Teach teach, Timestamp now); 
	
	public boolean update(TeachWeek teachWeek);
	
	public boolean setReason(String reason, long id);

	public TeachWeek getTeachDaoByTeachId(Teach teach, int weekId);
	
	public boolean insertTeachWeek(long teachId, int weekId);
	
	public boolean deleteTeachWeek(long teachId, int weekId);
}
