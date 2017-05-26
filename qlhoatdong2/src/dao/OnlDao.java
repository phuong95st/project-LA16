/**
 * Copyright(C) 2017 Luvina software company
 * OnlDao.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import entity.Onl;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface OnlDao {
	
	public List<Onl> getListOnl(Timestamp now, int weekId, int userId);
	
	public Onl getOnlById(long id);
	
	public boolean update(Onl onl, boolean isUpdateStatus);
	
	public boolean setReason(String reason, long id);
	
	public boolean checkOnlByUserId(int userId);
	
	public List<Onl> getListOnl(Map<String, Object> searchValue);
	
	public List<Onl> getListOnl (int userId, int weekId, long onlId);
	
	public boolean insert(Onl onl);
	
	public boolean delete(long onlId);
	
	public List<Onl> getListOnlBySearch(Map<String, Object> listValue);
	
	public List<String> getUserFreeOnl(Time timeStart, Time timeEnd, Date date);
	
}
