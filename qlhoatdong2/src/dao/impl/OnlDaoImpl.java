/**
 * Copyright(C) 2017 Luvina software company
 * OnlDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.OnlDao;
import entity.Onl;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class OnlDaoImpl extends BaseDaoImpl implements OnlDao {

	/*
	 * (non-Javadoc)
	 * @see dao.OnlDao#getListOnl(java.sql.Timestamp, java.sql.Timestamp, int)
	 */
	@Override
	public List<Onl> getListOnl(Timestamp timeStart, Timestamp timeEnd, int id) {
		List<Onl> list = new ArrayList<>();
		
		return list;
	}
	
}
