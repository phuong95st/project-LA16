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
	
	public List<Onl> getListOnl(Timestamp now, int weekId, int userId);
	
	public Onl getOnlById(long id);
	
	public boolean update(Onl onl, boolean isUpdateStatus);
	
	public boolean setReason(String reason, long id);
}
