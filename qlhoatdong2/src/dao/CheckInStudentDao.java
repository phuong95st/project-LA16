/**
 * Copyright(C) 2017 Luvina software company
 * CheckInStudentDao.java, 02-04-2017 Tran
 */
package dao;

import java.util.List;

import entity.CheckInStudent;
import entity.ScheStu;

/**
 * 
 * @author Tran
 *
 */
public interface CheckInStudentDao {
	public List<CheckInStudent> getListCheckInByScheStu(ScheStu scheStu);
	
	public CheckInStudent getCheckInStudentById(long id);
	
	public boolean edit(CheckInStudent check);
}
